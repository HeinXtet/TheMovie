package com.deevvdd.domain.type.exception
/**
 * Created by heinhtet deevvdd@gmail.com on 05,June,2021
 */
sealed class DataException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

    object Network : DataException()

    object Conversion : DataException()

    object SessionExpired : DataException()

    data class Api(
        override val message: String,
        val title: String = "",
        val errorCode: Int = -1
    ) : DataException(message)
}
