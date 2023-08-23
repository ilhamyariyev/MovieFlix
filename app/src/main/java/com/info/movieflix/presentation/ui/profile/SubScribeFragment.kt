package com.info.movieflix.presentation.ui.profile

import androidx.navigation.fragment.findNavController
import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.databinding.FragmentSubScribeBinding


class SubScribeFragment : BaseFragment<FragmentSubScribeBinding>(FragmentSubScribeBinding::inflate) {
    override fun onViewCreateFinish() {
        binding.constraintLayoutmonth.setOnClickListener {
            findNavController().navigate(SubScribeFragmentDirections.actionSubScribeFragmentToPaymentFragment())
        }

        binding.constraintLayoutyear.setOnClickListener {
            findNavController().navigate(SubScribeFragmentDirections.actionSubScribeFragmentToPaymentFragment())
        }
    }

    override fun observeEvents() {

    }

}