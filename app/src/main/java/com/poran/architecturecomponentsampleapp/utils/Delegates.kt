package com.poran.architecturecomponentsampleapp.utils

import kotlinx.coroutines.*


fun <T>lazyDeferred(blocK:suspend CoroutineScope.()->T):Lazy<Deferred<T>>{
    return lazy{
        GlobalScope.async(start = CoroutineStart.LAZY){
            blocK.invoke(this)
        }
    }
}