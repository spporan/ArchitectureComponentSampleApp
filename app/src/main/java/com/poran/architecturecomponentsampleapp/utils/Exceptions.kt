package com.poran.architecturecomponentsampleapp.utils

import java.io.IOException

class ApiException( message:String):IOException(message) {
}
class NoNetworkException( message:String):IOException(message) {
}