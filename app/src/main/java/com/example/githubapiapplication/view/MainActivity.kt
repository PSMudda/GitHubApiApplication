package com.example.githubapiapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubapiapplication.R
import com.example.githubapiapplication.databinding.ActivityMainBinding
import com.example.githubapiapplication.model.data.Commit
import com.example.githubapiapplication.model.network.GithubApiService
import com.example.githubapiapplication.model.repo.CommitsRepository
import com.example.githubapiapplication.view.adapters.CommitAdapter
import com.example.githubapiapplication.viewmodel.CommitViewModel
import com.example.githubapiapplication.viewmodel.MyViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CommitViewModel
    private val retrofitService = GithubApiService.getInstance()
    private val adapter = CommitAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel =
            ViewModelProvider(this, MyViewModelFactory(CommitsRepository(retrofitService))).get(
                CommitViewModel::class.java
            )
        binding.commitsList.adapter = adapter
        viewModel._commits.observe(this, Observer {
            adapter.setCommitList(it)

        })
        viewModel.errorMessage.observe(this, Observer {
        })

        viewModel.getRepoCommits()
    }
}