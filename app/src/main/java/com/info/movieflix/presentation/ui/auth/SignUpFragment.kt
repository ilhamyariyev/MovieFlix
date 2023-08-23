package com.info.movieflix.presentation.ui.auth

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.info.movieflix.R
import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.common.util.RegisterValidation
import com.info.movieflix.common.util.User
import com.info.movieflix.databinding.FragmentSignUpBinding
import com.info.movieflix.domain.model.state.AuthUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {
    private val viewModel: SignUpViewModel by viewModels<SignUpViewModel>()

    override fun onViewCreateFinish() {

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
                    kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.Main) {
                        binding.editTextPassword.apply {
                            requestFocus()
                            error = validation.password.message
                        }
                    }
                }

            }
        }

        binding.apply {
            buttonSignUp.setOnClickListener {
                val user = User(
                    editTextEmail.text.toString().trim()
                )
                val password = editTextPassword.text.toString().trim()
                viewModel.createAccountWithEmailAndPassword(user,password)
                viewModel.saveEmail(user.email)
                viewModel.savePassword(password)
            }
        }

        binding.textViewLogin.setOnClickListener {
            findNavController().navigate(SignUpFragmentDirections.toSignInFragment())
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
        binding.imageViewBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun observeEvents() {
        with(viewModel) {
            authResult.observe(viewLifecycleOwner) {
                with(binding){
                    when (it) {
                        is AuthUiState.Success -> {
                            binding.buttonSignUp.revertAnimation()
                            Toast.makeText(context, "Account created", Toast.LENGTH_SHORT).show()
                            findNavController().navigate(SignUpFragmentDirections.toSignInFragment())
                        }

                        is AuthUiState.Error -> {
                            Toast.makeText(context,"", Toast.LENGTH_SHORT).show()
                            binding.buttonSignUp.revertAnimation()
                        }

                        is AuthUiState.Loading -> {
                            binding.buttonSignUp.startAnimation()
                        }
                    }
                }
            }
        }
    }

}