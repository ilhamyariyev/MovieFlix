package com.info.movieflix.presentation.ui.detail

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import com.info.movieflix.databinding.FragmentDownloadDialogBinding


class DownloadDialogFragment : DialogFragment() {

    private lateinit var binding: FragmentDownloadDialogBinding


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDownloadDialogBinding.inflate(LayoutInflater.from(context))

        val builder = AlertDialog.Builder(requireActivity())
        builder.setView(binding.root)

        val dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.button5.setOnClickListener {
            dialog.dismiss()
        }

        return dialog
    }


}