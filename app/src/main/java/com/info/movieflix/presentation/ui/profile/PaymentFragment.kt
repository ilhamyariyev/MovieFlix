package com.info.movieflix.presentation.ui.profile

import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.databinding.FragmentPaymentBinding


class PaymentFragment : BaseFragment<FragmentPaymentBinding>(FragmentPaymentBinding::inflate) {
    override fun onViewCreateFinish() {
        binding.buttonConfirm.setOnClickListener {
            val dialogFragment = CongratulationDialogFragment()
            dialogFragment.show(requireActivity().supportFragmentManager, "My Fragment")
        }
    }

    override fun observeEvents() {

    }

}