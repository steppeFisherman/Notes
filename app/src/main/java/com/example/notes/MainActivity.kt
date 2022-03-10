package com.example.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.notes.databinding.ActivityMainBinding
import com.example.notes.utils.ConnectionLiveData
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NetWorkConnection {

    @Inject
    lateinit var connectionLiveData: ConnectionLiveData
    private lateinit var mToolbar: Toolbar
    private lateinit var navController: NavController
    private lateinit var mSnack: Snackbar
    private var _binding: ActivityMainBinding? = null
    private val mBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        initialise()
    }

    private fun initialise() {
        Firebase
        mToolbar = mBinding.toolbar
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        mSnack = Snackbar
            .make(mBinding.root, "Проверьте наличие интернета", Snackbar.LENGTH_INDEFINITE)
        setSupportActionBar(mToolbar)
        setupWithNavController(mToolbar, navController)
        title = getString(R.string.title)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun checkConnection() {
        connectionLiveData.checkValidNetworks()
        connectionLiveData.observe(this, { isNetWorkAvailable ->
            when (isNetWorkAvailable) {
                false -> mSnack.show()
                true -> mSnack.dismiss()
            }
        })
    }
}