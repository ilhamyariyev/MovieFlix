package com.info.movieflix.presentation.ui.home

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.common.util.MovieTypeEnum
import com.info.movieflix.common.util.gone
import com.info.movieflix.common.util.visible
import com.info.movieflix.databinding.FragmentHomeBinding
import com.info.movieflix.domain.model.MovieType
import com.info.movieflix.domain.model.state.MovieUiState
import com.info.movieflix.presentation.ui.home.adapters.NewReleaseAdapter
import com.info.movieflix.presentation.ui.home.adapters.PagerAdapter
import com.info.movieflix.presentation.ui.home.adapters.Top10Adapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel: HomeViewModel by viewModels()
    private val top10Adapter = Top10Adapter()
    private val newReleaseAdapter = NewReleaseAdapter()
    private val nowPlayingAdapter = PagerAdapter()

    override fun onViewCreateFinish() {

        binding.tvTop10SeeAll.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.toSeeAll(
                    MovieType("Top 10 Movies This Week", MovieTypeEnum.TOP_10_MOVIES)
                )
            )
        }

        binding.tvNewReleasesSeeAll.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.toSeeAll(
                    MovieType("NewReleases", MovieTypeEnum.NEW_RELEASE_MOVIES)
                )
            )
        }
        setRecyclerView()

    }

    override fun observeEvents() {
        with(viewModel){
                topRatedMovies.observe(viewLifecycleOwner) {
                    when (it) {
                        is MovieUiState.Success -> {

                            top10Adapter.differ.submitList(it.data.subList(0, 10))
                        }
                        is MovieUiState.Error -> {

                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                        is MovieUiState.Loading -> {

                        }
                    }
                }

                upComingMovies.observe(viewLifecycleOwner){
                    when (it) {
                        is MovieUiState.Success -> {
                            newReleaseAdapter.differ.submitList(it.data)
                        }
                        is MovieUiState.Error -> {
                            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                        }
                        is MovieUiState.Loading -> {}
                    }
                }

            nowPlayingMovies.observe(viewLifecycleOwner) {

                    when (it) {
                        is MovieUiState.Success -> {
                            binding.loadingNowPlaying.gone()
                            nowPlayingAdapter.differ.submitList(it.data)
                        }

                        is MovieUiState.Error -> {
                            binding.loadingNowPlaying.gone()
                            Toast.makeText(context, "Error404", Toast.LENGTH_SHORT).show()
                        }
                        is MovieUiState.Loading -> {
                            binding.loadingNowPlaying.visible()
                        }
                    }

            }

        }

    }

    private fun setRecyclerView() {
        with(binding) {
            rvTop10.adapter = top10Adapter
            rvNewRelease.adapter= newReleaseAdapter
            viewPager.adapter = nowPlayingAdapter

        }
    }


}