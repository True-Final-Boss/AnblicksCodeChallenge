package com.example.anblickscodechallenge.view

import android.content.Intent
import android.os.Bundle
import android.view.ContextThemeWrapper
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.anblickscodechallenge.R
import com.example.anblickscodechallenge.di.RadioApplication
import com.example.anblickscodechallenge.model.Channel
import com.example.anblickscodechallenge.model.VMData
import com.example.anblickscodechallenge.model.repository.Repository
import com.example.anblickscodechallenge.view.adapters.RadioAdapter
import com.example.anblickscodechallenge.viewmodel.RadioViewModel
import com.example.anblickscodechallenge.viewmodel.RadioViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var repository: Repository
    private lateinit var radioRV: RecyclerView
    private lateinit var radioSearch: SearchView

    private val radioAdapter: RadioAdapter = RadioAdapter(object: RadioAdapter.OnChannelClick{
        override fun openDetails(channel: Channel) {
            val titleData: String = channel.title
            val djData: String = channel.dj
            val djMailData: String = channel.djmail
            val listenersData: String = channel.listeners
            val genreData: String = channel.genre
            val imageData: String? = channel.largeimage

            Intent(this@MainActivity, ChannelActivity::class.java).also {
                it.putExtra("titleData", titleData)
                it.putExtra("djData", djData)
                it.putExtra("djMailData", djMailData)
                it.putExtra("listenersData", listenersData)
                it.putExtra("genreData", genreData)
                it.putExtra("imageData", imageData)
                startActivity(it)
            }


        }

    })

    private val viewModelFactory by lazy {
        RadioViewModelFactory(repository)
    }

    private val viewmodel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(RadioViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (applicationContext as RadioApplication).applicationComponent.inject(this)

        radioRV = findViewById(R.id.radio_rv)
        radioRV.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }

        radioRV.adapter = radioAdapter

        viewmodel.channelLiveData.observe(this,
            {

                when (it) {
                    is VMData.Response -> {
                        radioAdapter.channels = it.response.channels
                    }
                    is VMData.Error -> {
                        notifyOfError(it.error)
                    }
                }
            })

        viewmodel.getChannelResponse()
        radioSearch = findViewById(R.id.radio_search)
        radioSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                radioAdapter.search(newText)
                return true
            }

        })
    }

    private fun notifyOfError(error: String) {
        AlertDialog.Builder(ContextThemeWrapper(this, R.style.ThemeOverlay_AppCompat))
            .setTitle(getString(R.string.error_title))
            .setMessage(error)
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }.create().show()
    }
}