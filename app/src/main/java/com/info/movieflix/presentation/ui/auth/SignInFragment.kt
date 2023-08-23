package com.info.movieflix.presentation.ui.auth

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.info.movieflix.R
import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.common.util.RegisterValidation
import com.info.movieflix.common.util.Resource
import com.info.movieflix.common.util.User
import com.info.movieflix.common.util.setupBottomSheetDialog
import com.info.movieflix.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {
    private val viewModel: SignInViewModel by viewModels<SignInViewModel>()

    override fun onViewCreateFinish() {
        binding.textViewSignUp.setOnClickListener {
            findNavController().navigate(SignInFragmentDirections.toSignUpFragment())
        }


        lifecycleScope.launch {
            viewModel.validation.collect{validation ->
                if (validation.email is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.editTextEmail.apply {
                            requestFocus()
                            error = validation.email.message
                        }
                    }
                }else if (validation.password is RegisterValidation.Failed){
                    withContext(Dispatchers.Main){
                        binding.editTextPassword.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }

            }
        }



        binding.apply {
            buttonSignIn.setOnClickListener {
                val user = User(
                    editTextEmail.text.toString().trim()
                )
                val password = editTextPassword.text.toString().trim()

               viewModel.loginUser(user,password)
            }
        }

        binding.textViewForgotPassword.setOnClickListener {
            setupBottomSheetDialog { email ->
                viewModel.resetPassword(email)
            }
        }

        var isChecked = false

        binding.imageButtonChecked.setOnClickListener {
            val imageRes = if(isChecked){
                R.drawable.check_box_outline
            }else{
                R.drawable.check_box
            }
            binding.imageButtonChecked.setImageResource(imageRes)
            isChecked = !isChecked
        }

        onBackPress()
        binding.imageViewBack.setOnClickListener {
            findNavController().navigateUp()
        }

    }


    override fun observeEvents() {
        with(viewModel){
            authResult.observe(viewLifecycleOwner){
                when (it) {
                    is Resource.Loading -> {
                        binding.buttonSignIn.startAnimation()
                    }

                    is Resource.Success -> {
                        binding.buttonSignIn.revertAnimation()
                        findNavController().navigate(SignInFragmentDirections.toHomeFragment())
                    }

                    is Resource.Error -> {
                        binding.buttonSignIn.revertAnimation()
                        Toast.makeText(context,it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }

            resetPassword.observe(viewLifecycleOwner){
                when (it) {
                    is Resource.Loading -> {

                    }

                    is Resource.Success -> {
                        Snackbar.make(
                            requireView(),
                            "Reset link was sent to your email",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }

                    is Resource.Error -> {
                        Snackbar.make(requireView(), "Error ${it.exception}", Snackbar.LENGTH_LONG)
                            .show()
                    }

                }
            }

        }
    }

    fun onBackPress(){
        requireActivity().onBackPressedDispatcher.addCallback(this,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
               findNavController().navigate(SignInFragmentDirections.tosplash())
            }
        })
    }

}