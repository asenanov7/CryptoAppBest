package com.example.di

import com.example.data.workers.ChildWorkerFactory
import com.example.data.workers.LoadDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @WorkerClassKey(LoadDataWorker::class)
    @IntoMap
    @Binds
    fun bindLoadDataWorkerFactory(loadDataWorker: LoadDataWorker.Factory): ChildWorkerFactory
}