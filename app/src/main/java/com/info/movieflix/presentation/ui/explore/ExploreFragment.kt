package com.info.movieflix.presentation.ui.explore

import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.common.util.gone
import com.info.movieflix.common.util.visible
import com.info.movieflix.databinding.FragmentExploreBinding
import com.info.movieflix.domain.model.state.MovieUiState
import com.info.movieflix.presentation.ui.explore.adapters.ExploreAdapter
import com.info.movieflix.presentation.ui.explore.adapters.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExploreFragment : BaseFragment<FragmentExploreBinding>(FragmentExploreBinding::inflate) {

    private val viewModel : ExploreViewModel by viewModels()
    private val exploreAdapter = ExploreAdapter()
    private val searchAdapter = SearchAdapter()

    override fun onViewCreateFinish() {
        setAdapters()

        binding.ibFilter.setOnClickListener {
            findNavController().navigate(ExploreFragmentDirections.tofilterFragment())
        }

        with(binding){
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
                android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let{ viewModel.getSearch(it) }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return if (newText?.length == 0){
                        rvExplore.visible()
                        lySearch.gone()
                        notFound.gone()
                        false
                    }else{
                        newText?.let{ viewModel.getSearch(it) }
                        rvExplore.gone()
                        notFound.gone()
                        lySearch.visible()
                        true
                    }
                }
            })
        }

    }

    override fun observeEvents() {
        with(viewModel){
            with(binding){
                exploreData.observe(viewLifecycleOwner) {
                    when (it) {
                        is MovieUiState.Success -> {
                            loadingExplore.gone()
                            notFound.gone()
                            exploreAdapter.differ.submitList(it.data)
                        }

                        is MovieUiState.Error -> {
                            loadingExplore.gone()
                            notFound.visible()

                        }

                        is MovieUiState.Loading -> {
                            loadingExplore.visible()
                        }
                    }
                }

                searchData.observe(viewLifecycleOwner) {
                    when (it) {
                        is MovieUiState.Success -> {
                            loadingExplore.gone()
                            if (it.data.isEmpty()) {
                                notFound.visible()

                            }else{
                                notFound.gone()
                            }
                            searchAdapter.differ.submitList(it.data)

                        }
                        is MovieUiState.Error -> {
                            loadingExplore.gone()
                            notFound.visible()

                        }

                        is MovieUiState.Loading -> {
                            loadingExplore.visible()
                        }
                    }
                }
            }

        }
    }

    private fun setAdapters() {
        with(binding) {
            rvExplore.adapter = exploreAdapter
            rvSearch.adapter = searchAdapter
        }
    }

}