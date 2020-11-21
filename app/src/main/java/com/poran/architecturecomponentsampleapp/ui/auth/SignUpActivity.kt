package com.poran.architecturecomponentsampleapp.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.poran.architecturecomponentsampleapp.R
import com.poran.architecturecomponentsampleapp.data.db.entities.User
import com.poran.architecturecomponentsampleapp.databinding.ActivitySignUpBinding
import com.poran.architecturecomponentsampleapp.utils.hide
import com.poran.architecturecomponentsampleapp.utils.show
import com.poran.architecturecomponentsampleapp.utils.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class SignUpActivity : AppCompatActivity(),KodeinAware,AuthListener {
    override val kodein by closestKodein()
   private val factory:AuthViewModelFactory by instance()
    private lateinit var signUpViewModel:AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpViewModel=createViewModel()
        signUpViewModel.authListener=this
        val binding:ActivitySignUpBinding=DataBindingUtil.setContentView(this,R.layout.activity_sign_up)
        binding.signUpViewModel=signUpViewModel

        signUpViewModel.getLoggedInUser().observe(this, Observer {user->
            toast("already sign in")
            if(user!=null){
                Intent(this,LoginActivity::class.java).also {
                    it.flags=Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(it)
                }
            }

        })


    }

    private fun createViewModel()=ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)
    override fun onStarted() {
        loader_progress.show()

    }

    override fun onSuccess(user: User) {
        loader_progress.hide()
        toast("${user.name} Success Sign Up")
    }

    override fun onFailure(msg: String) {
        loader_progress.hide()

        toast(msg)
    }

    override fun onForgot(msg: String) {
        toast(msg)
    }


}