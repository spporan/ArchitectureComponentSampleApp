package com.poran.architecturecomponentsampleapp.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.poran.architecturecomponentsampleapp.R
import com.poran.architecturecomponentsampleapp.data.db.entities.User
import com.poran.architecturecomponentsampleapp.databinding.ActivityLoginBinding
import com.poran.architecturecomponentsampleapp.utils.hide
import com.poran.architecturecomponentsampleapp.utils.show
import com.poran.architecturecomponentsampleapp.utils.toast
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(),AuthListener {
    lateinit var viewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding:ActivityLoginBinding=DataBindingUtil.setContentView(this,R.layout.activity_login)
         viewModel=createViewModel()
        binding.viewModel=viewModel

        viewModel.authListener=this


    }

    private fun createViewModel()=ViewModelProviders.of(this,LoginViewModelFactory()).get(LoginViewModel::class.java)


    override fun onStarted() {
        toast("started login")
        loader_progress.show()
    }

    override fun onSuccess(user:User) {
        loader_progress.hide()
        toast("${user.name} Success login")


    }

    override fun onFailure(msg: String) {
        loader_progress.hide()

        toast(msg)
    }

    override fun onForgot(msg: String) {
        toast(msg)
    }


}