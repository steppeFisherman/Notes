package com.example.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.notes.data.storage.AppRoomDatabase
import com.example.notes.data.repository.NoteRepositoryImpl
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.utils.APP_ACTIVITY
import com.example.notes.utils.DAO
import com.example.notes.utils.REPOSITORY

class MainActivity : AppCompatActivity() {

    lateinit var mToolbar: Toolbar
    lateinit var navController: NavController
    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
        initialise()
    }

    private fun initialise() {

        DAO = AppRoomDatabase.getInstance(this).getAppRoomDao()
        REPOSITORY = NoteRepositoryImpl(DAO)
        APP_ACTIVITY = this
        mToolbar = mBinding.toolbar
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setSupportActionBar(mToolbar)
        title = getString(R.string.title)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}