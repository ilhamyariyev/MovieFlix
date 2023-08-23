package com.info.movieflix.presentation.ui.splash

import androidx.activity.addCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(FragmentSplashBinding::inflate) {
    private val viewModel : SplashViewModel by viewModels()

    override fun onViewCreateFinish() {
        binding.apply {
            lottiLoading.alpha = 0f
            lottiLoading.animate().setDuration(3500).alpha(1f).start()

        }

        finishActivity()
    }



    override fun observeEvents() {
        viewModel.auth.observe(viewLifecycleOwner){
            if (it){
                findNavController().navigate(SplashFragmentDirections.towelcome())
            }else{
                findNavController().navigate(SplashFragmentDirections.tohomeFragment())
            }
        }
    }

    fun finishActivity(){
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            requireActivity().finish()
        }
    }

}