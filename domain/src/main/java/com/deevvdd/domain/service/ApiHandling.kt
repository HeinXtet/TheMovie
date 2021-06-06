package com.deevvdd.domain.service


import android.util.Log
import com.deevvdd.domain.type.Either
import com.deevvdd.domain.type.exception.CommonErrorMessage
import com.deevvdd.domain.type.exception.DataException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
abstract class BaseRemoteRepository {
    companion object {
        private const val TAG = "BaseRemoteRepository"
        private const val MESSAGE_KEY = "message"
        private const val ERROR_KEY = "error"
    }

    /**
     * Function that executes the given function on Dispatchers.IO context and switch to Dispatchers.Main context when an error occurs
     * @param callFunction is the function that is returning the wanted object. It must be a suspend function. Eg:
     * override suspend fun loginUser(body: LoginUserBody, emitter: RemoteErrorEmitter): LoginUserResponse?  = safeApiCall( { authApi.loginUser(body)} , emitter)
     * @param emitter is the interface that handles the error messages. The error messages must be displayed on the MainThread, or else they would throw an Exception.
     */
    protected suspend inline fun <T, R> safeApiCall(
        apiCall: suspend () -> Response<T>,
        mapper: suspend (T) -> R
    ): Either<DataException, R> {
        return try {
            apiCall().let {
                val body = it.body()
                if (body != null) {
                    Either.Right(mapper(body))
                } else {
                    Either.Left(
                        DataException.Api(message = CommonErrorMessage.UNKNOWN_ERROR_MESSAGE)
                    )
                }
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                e.printStackTrace()
                Log.e("BaseRemoteRepo", "Call error: ${e.localizedMessage}", e.cause)
                when (e) {
                    is HttpException -> {
                        if (e.code() == 401) Either.Left(
                            DataException.SessionExpired
                        )
                        else {
                            val body = e.response()?.errorBody()
                            //  dataException(ApiException(getErrorMessage(body)))
                            Either.Left(
                                DataException.Api(
                                    message = getErrorMessage(body)
                                )
                            )
                        }
                    }
                    is SocketTimeoutException -> Either.Left(
                        DataException.Network
                    )

                    else -> Either.Left(
                        DataException.Api(message = CommonErrorMessage.UNKNOWN_ERROR_MESSAGE)
                    )
                }
            }
        }
    }


    fun getErrorMessage(responseBody: ResponseBody?): String {
        return try {
            val jsonObject = JSONObject(responseBody!!.string())
            when {
                jsonObject.has(MESSAGE_KEY) -> jsonObject.getString(
                    MESSAGE_KEY
                )
                jsonObject.has(ERROR_KEY) -> jsonObject.getString(
                    ERROR_KEY
                )
                else -> "Something wrong happened"
            }
        } catch (e: Exception) {
            "Something wrong happened"
        }
    }
}