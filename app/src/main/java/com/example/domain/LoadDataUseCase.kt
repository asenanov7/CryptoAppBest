package com.example.domain

class LoadDataUseCase(private val repository: Repository) {
    suspend operator fun invoke(){
        repository.loadData()
    }
}