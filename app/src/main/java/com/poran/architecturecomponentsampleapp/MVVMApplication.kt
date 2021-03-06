package com.poran.architecturecomponentsampleapp

import android.app.Application
import com.poran.architecturecomponentsampleapp.data.api.ApiService
import com.poran.architecturecomponentsampleapp.data.api.NetworkConnectionInterceptor
import com.poran.architecturecomponentsampleapp.data.db.AppDatabase
import com.poran.architecturecomponentsampleapp.data.repository.QuoteRepository
import com.poran.architecturecomponentsampleapp.data.repository.UserRepository
import com.poran.architecturecomponentsampleapp.preferences.PreferenceProvider
import com.poran.architecturecomponentsampleapp.ui.auth.AuthViewModelFactory
import com.poran.architecturecomponentsampleapp.ui.home.profile.ProfileViewModelFactory
import com.poran.architecturecomponentsampleapp.ui.home.quotes.QuoteViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class MVVMApplication :Application(),KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind<NetworkConnectionInterceptor>() with  singleton {
            NetworkConnectionInterceptor(instance())
        }
        bind<ApiService>() with  singleton {
            ApiService(instance())
        }
        bind<AppDatabase>() with singleton {
            AppDatabase(instance())
        }
        bind<UserRepository>() with  singleton {
            UserRepository(instance(),instance())
        }
        bind<PreferenceProvider>() with singleton {
            PreferenceProvider(instance())
        }

        bind<QuoteRepository>() with singleton {
            QuoteRepository(instance(),instance(),instance())
        }
        bind() from provider {
            AuthViewModelFactory(instance())
        }
        bind() from provider {
            ProfileViewModelFactory(instance())
        }
        bind() from provider {
            QuoteViewModelFactory(instance())
        }
    }
}