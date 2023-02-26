package com.example.m18_permissions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.m18_permissions.ui.main.MainFragment
import com.example.m18_permissions.ui.main.MainViewModel
import kotlinx.coroutines.flow.collect

class MainActivity : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val wordDao = (application as App).db.photoDao()
                return MainViewModel(photoDao = wordDao) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment, MainFragment.newInstance())
                .commitNow()
        }

        // val photoDao = (application as App).db.photoDao()
        //  val photoDao = (application as App).db.photoDao()

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect {
              it.photo
            }
        }

    }
}