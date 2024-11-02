package com.viktoriagavrosh.interview.data

/**
 * Data source query result
 *
 */
sealed class RequestResult<T : Any> {

    /**
     * request not completed yet.
     *
     */
    class Loading<T : Any> : RequestResult<T>()

    /**
     * request completed success. Data received.
     *
     * @param data get from data source
     */
    class Success<T : Any>(val data: T) : RequestResult<T>()

    /**
     * request completed with error.
     *
     * @param error what exception was thrown
     */
    class Error<T : Any>(val error: Throwable? = null) : RequestResult<T>()
}
