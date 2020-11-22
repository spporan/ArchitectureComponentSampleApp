package com.poran.architecturecomponentsampleapp.ui.home.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.poran.architecturecomponentsampleapp.R
import com.poran.architecturecomponentsampleapp.databinding.ProfileFragmentBinding
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class ProfileFragment : Fragment() ,KodeinAware{

    override val kodein by closestKodein()
    private val factory:ProfileViewModelFactory by instance()
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding:ProfileFragmentBinding=DataBindingUtil.inflate(inflater,R.layout.profile_fragment,container,false)
        viewModel = ViewModelProvider(this,factory).get(ProfileViewModel::class.java)
        binding.profileViewModel=viewModel
        binding.lifecycleOwner=this

        return binding.root

    }




}