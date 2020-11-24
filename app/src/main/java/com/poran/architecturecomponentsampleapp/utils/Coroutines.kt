package com.poran.architecturecomponentsampleapp.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

object Coroutines {
    fun main(work:suspend (()->Unit))=
        CoroutineScope(Dispatchers.Main).launch {
            work()
        }
    fun oi(work:suspend (()->Unit))=
            CoroutineScope(Dispatchers.IO).launch {
                work()
            }
}