package com.withintent.network.operation

import com.withintent.network.NetworkErrorHandler
import com.withintent.network.call.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun <Local> networkFlow(call: suspend () -> Resource<Local>): Flow<Resource<Local>> {
    return flow {
        try {
            emit(Resource.loading())
            val result = call()
            emit(result)
        } catch (thr: Throwable) {
            NetworkErrorHandler.handleThrowable<Local>(thr)
        }
    }
}