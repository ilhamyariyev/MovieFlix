package com.info.movieflix.presentation.ui.detail

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.common.util.gone
import com.info.movieflix.common.util.visible
import com.info.movieflix.databinding.FragmentCommentsBinding
import com.info.movieflix.domain.model.state.ReviewUiState
import com.info.movieflix.presentation.ui.detail.adapter.ReviewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment(private val id: Int) : BaseFragment<FragmentCommentsBinding>(FragmentCommentsBinding::inflate) {

    private val viewModel by viewModels<CommentsViewModel>()
    private val reviewAdapter = ReviewAdapter()


    override fun onViewCreateFinish() {
        setRecycler()
        getData(id)
        observeEvents()
    }

    override fun observeEvents() {
        viewModel.reviews.observe(viewLifecycleOwner) {
            when (it) {
                is ReviewUiState.Success -> {
                    reviewAdapter.differ.submitList(it.data.results)
                    binding.progressBar.gone()
                }

                is ReviewUiState.Error -> {
                    binding.progressBar.gone()
                    binding.rvReview.gone()
                    binding.trailerEmpty.visible()
                    Toast.makeText(context, "Error download trailers", Toast.LENGTH_SHORT).show()
                }

                is ReviewUiState.Loading -> {
                    binding.progressBar.visible()
                }
            }
        }

    }

    private fun setRecycler() {
        binding.rvReview.adapter = reviewAdapter
    }

    private fun getData(id: Int) {
        viewModel.getReviews(id)
    }


}