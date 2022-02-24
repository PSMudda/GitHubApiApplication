package com.example.githubapiapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.githubapiapplication.R
import com.example.githubapiapplication.databinding.ActivityMainBinding
import com.example.githubapiapplication.model.data.Commit
import com.example.githubapiapplication.view.adapters.CommitAdapter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    val adapter = CommitAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.commitsList.adapter = adapter
    }
}