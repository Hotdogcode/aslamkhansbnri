package com.hotdogcode.aslamkhansbnri.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hotdogcode.aslamkhansbnri.R
import com.hotdogcode.aslamkhansbnri.adapters.UserAdapter
import com.hotdogcode.aslamkhansbnri.data.AppDataManager
import com.hotdogcode.aslamkhansbnri.data.local.db.AppDatabase
import com.hotdogcode.aslamkhansbnri.networking.ApiClient
import com.hotdogcode.aslamkhansbnri.networking.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var adapter:UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        init()

    }

    fun initAdapter(){
        adapter = UserAdapter()
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        list.layoutManager = layoutManager
        list.adapter = adapter
    }
    fun init(){

        viewModel.userPaged.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.cacheData.observe(this, Observer {
            
        })
    }
}
