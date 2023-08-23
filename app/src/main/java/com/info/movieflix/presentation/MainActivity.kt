package com.info.movieflix.presentation

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.info.movieflix.R
import com.info.movieflix.common.util.gone
import com.info.movieflix.common.util.visible
import com.info.movieflix.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window?.addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(binding.bottombar, navController)

        navController.addOnDestinationChangedListener(object :
            NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                when (destination.id) {
                    R.id.splashFragment -> binding.bottombar.gone()
                    R.id.signUpFragment -> binding.bottombar.gone()
                    R.id.signInFragment -> binding.bottombar.gone()
                    R.id.inputOptionFragment -> binding.bottombar.gone()
                    R.id.welcomeFragment -> binding.bottombar.gone()
                    R.id.detailFragment -> binding.bottombar.gone()
                    R.id.youtubePlayerFragment -> binding.bottombar.gone()
                    R.id.subScribeFragment -> binding.bottombar.gone()
                    R.id.editProfileFragment -> binding.bottombar.gone()
                    R.id.paymentFragment -> binding.bottombar.gone()


                    else -> {
                        if (binding.bottombar.visibility == View.GONE) {
                            binding.bottombar.visible()
                        }
                    }
                }

            }
        })
    }
}