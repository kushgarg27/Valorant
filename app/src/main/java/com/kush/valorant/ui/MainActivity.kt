package com.kush.valorant.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kush.valorant.R
import com.kush.valorant.data.response.Data
import com.kush.valorant.databinding.ActivityMainBinding
import com.kush.valorant.ui.adapter.AgentsAdapter
import com.kush.valorant.ui.viewModel.AgentViewModel
import com.kush.valorant.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    private val getAgentsViewModel: AgentViewModel by viewModels()
    private var binding: ActivityMainBinding? = null
    private var adapter: AgentsAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater, null, false)
        setContentView(binding?.root)
        allFunctions()
    }

    private fun allFunctions() {
        getAgentsViewModel.getAgents("en-us",true)
        agentsApi()
    }

    private fun agentsApi() {
        getAgentsViewModel.liveDataScope.observe(this@MainActivity) {
            when (it) {
               is NetworkResult.Success ->
               {
                   retriveData(it.data?.data)
                }
                is NetworkResult.Error -> Toast.makeText(this, it.message.toString(), Toast.LENGTH_SHORT).show()
                is NetworkResult.Loading -> Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show()
            }

        }
    }

    private fun retriveData(data: List<Data>?) {
      adapter = data?.let { AgentsAdapter(this@MainActivity, it)

      }
        binding?.recyclerView?.adapter = adapter
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this@MainActivity)

    }
}