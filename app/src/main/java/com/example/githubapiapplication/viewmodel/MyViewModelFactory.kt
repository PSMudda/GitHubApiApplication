package com.example.githubapiapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubapiapplication.model.repo.CommitsRepository
import javax.inject.Inject

class MyViewModelFactory @Inject constructor(val repository: CommitsRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CommitViewModel::class.java)) {
            CommitViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}