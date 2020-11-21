package com.poran.architecturecomponentsampleapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.poran.architecturecomponentsampleapp.R
import com.poran.architecturecomponentsampleapp.data.api.ApiService
import com.poran.architecturecomponentsampleapp.data.db.AppDatabase
import com.poran.architecturecomponentsampleapp.data.db.entities.User
import com.poran.architecturecomponentsampleapp.data.repository.UserRepository
import com.poran.architecturecomponentsampleapp.databinding.ActivityLoginBinding
import com.poran.architecturecomponentsampleapp.ui.home.HomeActivity
import com.poran.architecturecomponentsampleapp.utils.hide
import com.poran.architecturecomponentsampleapp.utils.show
import com.poran.architecturecomponentsampleapp.utils.toast
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(),AuthListener {
    lateinit var viewModel: LoginViewModel
    lateinit var  repository:UserRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val api=ApiService()
        val appDatabase=AppDatabase(this)
        repository=UserRepository(api,appDatabase)

        val binding:ActivityLoginBinding=DataBindingUtil.setContentView(this,R.layout.activity_login)
         viewModel=createViewModel()
        binding.viewModel=viewModel

        viewModel.authListener=this

        viewModel.getLoggedInUser().observe(this, Observer { user->
            if(user!=null){
               Intent(this,HomeActivity::class.java).also {
                   it.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                   startActivity(it)
               }
            }
        })


    }

    private fun createViewModel()=ViewModelProviders.of(this,LoginViewModelFactory(repository)).get(LoginViewModel::class.java)


    override fun onStarted() {
        //toast("started login")
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