package com.poran.architecturecomponentsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.poran.architecturecomponentsampleapp.R
import com.poran.architecturecomponentsampleapp.utils.Coroutines
import com.poran.architecturecomponentsampleapp.utils.toast
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment() ,KodeinAware{
    override val kodein by closestKodein()
    private val factory:QuoteViewModelFactory by instance()

    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this,factory).get(QuotesViewModel::class.java)

        Coroutines.main {
           val quotes=viewModel.quote.await()
            quotes.observe(viewLifecycleOwner, Observer {
               context?.toast(it.size.toString())
           })

        }

    }


}