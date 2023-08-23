package com.info.movieflix.presentation.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.info.movieflix.databinding.FragmentFilterBinding


class FilterFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentFilterBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        with(binding) {
            btnApply.setOnClickListener { dismiss() }
            btnReset.setOnClickListener { clearChip() }
        }
    }

    private fun clearChip() {
        binding.apply {
            cgCategories.clearCheck()
            cgGenre.clearCheck()
            cgRegion.clearCheck()
            cgSort.clearCheck()
            cgTime.clearCheck()
        }
    }

}