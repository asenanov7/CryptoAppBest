package com.example.domain

class LoadDataUseCase(private val repository: Repository) {
     operator fun invoke(){
        repository.loadData()
    }
}