package com.example.di

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import com.example.data.workers.WorkerFactory
import javax.inject.Inject

class AppClass: Application(), Configuration.Provider {

        @Inject
        lateinit var workerFactory: WorkerFactory

        lateinit var component: AppComponent

        override fun onCreate() {
            super.onCreate()
            component = DaggerAppComponent.factory().create(this)
            component.inject(this)
        }

    override fun getWorkManagerConfiguration(): Configuration {  //Настройка WorkManager на все приложение
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)        //Просим при создании воркеров, использовать фабрику
            .build()
    }
}

    val Context.component: AppComponent
        get() = when (this) {
            is AppClass -> component
            else -> this.applicationContext.component
        }

