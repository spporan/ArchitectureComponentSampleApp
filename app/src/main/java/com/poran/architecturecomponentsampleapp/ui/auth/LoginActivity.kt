package com.poran.architecturecomponentsampleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.poran.architecturecomponentsampleapp.R
import com.poran.architecturecomponentsampleapp.utils.toast

class LoginActivity : AppCompatActivity(),AuthListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStarted() {
        toast("started login")


    }

    override fun onSuccess() {
        toast("Success login")

    }

    override fun onFailure(msg: String) {
        toast(msg)
    }


}