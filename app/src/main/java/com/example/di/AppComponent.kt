package com.example.di

import android.app.Application
import com.example.presentation.fragments.CoinListFragment
import com.example.presentation.fragments.DetailInfoFragment
import dagger.BindsInstance
import dagger.Component
import dagger.Subcomponent

@ApplicationScope
@Component( modules = [DataModule::class, WorkerModule::class] )
interface AppComponent {

    fun inject(appClass: AppClass)

    fun getCoinListSubcomponentFactory(): CoinListSubcomponent.Factory

    fun getDetailInfoSubcomponentFactory(): DetailInfoSubcomponent.Factory

    @Component.Factory
    interface ComponentFactory{

            fun create(
                @BindsInstance application: Application
            ): AppComponent

        }

}

@Subcomponent(modules = [ViewModelModule::class])
interface CoinListSubcomponent {

    fun inject(fragment: CoinListFragment)

    @Subcomponent.Factory
    interface Factory{

        fun create(): CoinListSubcomponent

    }

}

@Subcomponent(modules = [ViewModelModule::class])
interface DetailInfoSubcomponent {

    fun inject(fragment: DetailInfoFragment)

    @Subcomponent.Factory
    interface Factory{

        fun create(): DetailInfoSubcomponent

    }


}




