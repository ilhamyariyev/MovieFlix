package com.info.movieflix.presentation.ui.auth

import androidx.navigation.fragment.findNavController
import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.databinding.FragmentInputOptionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputOptionFragment : BaseFragment<FragmentInputOptionBinding>(FragmentInputOptionBinding::inflate) {
    override fun onViewCreateFinish() {
        binding.textViewSignUp.setOnClickListener {
            findNavController().navigate(InputOptionFragmentDirections.toSignUpFragment())
        }

        binding.buttonEmail.setOnClickListener {
            findNavController().navigate(InputOptionFragmentDirections.tosignInFragment())
        }
        binding.imageViewBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun observeEvents() {

    }

}