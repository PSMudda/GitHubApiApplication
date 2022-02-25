package com.example.githubapiapplication.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.githubapiapplication.GitHubApiApplication
import com.example.githubapiapplication.databinding.ActivityMainBinding
import com.example.githubapiapplication.view.adapters.CommitAdapter
import com.example.githubapiapplication.viewmodel.CommitViewModel
import com.example.githubapiapplication.viewmodel.GithubApiDownloadStatus
import com.example.githubapiapplication.viewmodel.MyViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var viewModel: CommitViewModel

    @Inject
    lateinit var myViewModelFactory: MyViewModelFactory

    private val adapter = CommitAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GitHubApiApplication.apiComponent.inject(this)

        viewModel =
            ViewModelProvider(this, myViewModelFactory).get(
                CommitViewModel::class.java
            )
        binding.commitsList.adapter = adapter
        viewModel._commits.observe(this, Observer {
            if (it != null) {
                adapter.setCommitList(it)
            }
        })
        viewModel.errorMessage.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.status.observe(this, Observer {
            when (it) {
                GithubApiDownloadStatus.LOADING -> {
                    binding.GitHubApiLoading.visibility = View.VISIBLE
                }
                GithubApiDownloadStatus.DONE -> {
                    binding.GitHubApiLoading.visibility = View.GONE
                }
                GithubApiDownloadStatus.ERROR -> {
                    binding.GitHubApiLoading.visibility = View.GONE
                }
            }
        })

        viewModel.getRepoCommits()
    }
}