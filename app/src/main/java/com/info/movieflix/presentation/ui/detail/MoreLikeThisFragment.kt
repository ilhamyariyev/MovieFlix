package com.info.movieflix.presentation.ui.detail

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.common.util.gone
import com.info.movieflix.common.util.visible
import com.info.movieflix.databinding.FragmentMoreLikeThisBinding
import com.info.movieflix.domain.model.state.RecommendationUiState
import com.info.movieflix.presentation.ui.detail.adapter.RecommendationAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoreLikeThisFragment(private val id: Int) : BaseFragment<FragmentMoreLikeThisBinding>(FragmentMoreLikeThisBinding::inflate) {

    private val viewModel by viewModels<MoreLikeThisViewModel>()
    private val recommendationAdapter = RecommendationAdapter()


    override fun onViewCreateFinish() {
        setRecycler()
        getData(id)
        observeEvents()
    }

    override fun observeEvents() {
        viewModel.recommendation.observe(viewLifecycleOwner) {
            when (it) {
                is RecommendationUiState.Success -> {
                    recommendationAdapter.differ.submitList(it.data.results)
                    binding.progressBar2.gone()
                }

                is RecommendationUiState.Error -> {
                    binding.progressBar2.gone()
                    binding.rvRecommendation.gone()
                    binding.trailerEmpty.visible()
                    Toast.makeText(context, "Error download trailers", Toast.LENGTH_SHORT).show()
                }

                is RecommendationUiState.Loading -> {
                    binding.progressBar2.visible()
                }
            }
        }
    }

    private fun setRecycler() {
        binding.rvRecommendation.adapter = recommendationAdapter
    }

    private fun getData(id: Int) {
        viewModel.getMoreLikeThis(id)
    }

}