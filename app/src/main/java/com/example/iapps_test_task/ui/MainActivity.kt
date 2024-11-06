package com.example.iapps_test_task.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.iapps_test_task.databinding.ActivityMainBinding
import com.example.iapps_test_task.ui.adapter.FeedAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels()

    private val adapter: FeedAdapter by lazy {
        FeedAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setup()
        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            mainViewModel.uiState.collectLatest {
                binding.progress.isVisible = it is MainUiState.Loading

                if (it is MainUiState.FeedData) {
                    adapter.setFeed(it.feeds)
                    binding.tvNoDataFound.isVisible = it.feeds.isEmpty()
                } else if (it is MainUiState.FeedError) {
                    val snack = Snackbar.make(
                        binding.root,
                        it.message,
                        Snackbar.LENGTH_LONG
                    )
                    snack.show()
                }
            }
        }
    }

    private fun setup() {
        binding.rvList.adapter = adapter
    }
}