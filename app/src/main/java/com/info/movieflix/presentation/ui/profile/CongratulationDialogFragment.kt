package com.info.movieflix.presentation.ui.profile

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.info.movieflix.databinding.FragmentCongratulationDialogBinding


class CongratulationDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentCongratulationDialogBinding


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentCongratulationDialogBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.buttonOk.setOnClickListener {
            dialog.dismiss()
            findNavController().navigate(PaymentFragmentDirections.toprofile())
        }

        return dialog
    }

}