package com.info.movieflix.presentation.ui.detail

import android.content.Intent
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.info.androidileriders2.roomDB.Favorite
import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.common.util.gone
import com.info.movieflix.common.util.visible
import com.info.movieflix.data.dto.DetailsResponseModelItem
import com.info.movieflix.databinding.FragmentDetailBinding
import com.info.movieflix.domain.model.state.CastUiState
import com.info.movieflix.domain.model.state.DetailUiState
import com.info.movieflix.presentation.ui.detail.adapter.CastAdapter
import com.info.movieflix.presentation.ui.detail.adapter.GenreAdapter
import com.info.movieflix.presentation.ui.detail.adapter.viewPagerTabLayoutAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {

     private val viewModel by viewModels<DetailViewModel>()
     private lateinit var adapter: viewPagerTabLayoutAdapter
     private lateinit var movie: DetailsResponseModelItem
     private val genreAdapter = GenreAdapter()
     private val castAdapter = CastAdapter()
     private val args by navArgs<DetailFragmentArgs>()


    override fun onViewCreateFinish() {
        setViewPager()
        setButtons()
        getData(args.id.toInt())
        setRecyclerViews()
        observeEvents()

        binding.imageViewSave.setOnClickListener {
            viewModel.addFavorite(
                Favorite(
                    movie.id,
                    movie.title,
                    movie.posterPath,
                    movie.voteAverage
                )
            )
            Toast.makeText(context, "${movie.title} saved in movie list", Toast.LENGTH_SHORT).show()
        }

        binding.ImageViewBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun observeEvents() {
        viewModel.cast.observe(viewLifecycleOwner){
            when (it) {

                is CastUiState.Success -> {
                    castAdapter.differ.submitList(it.data.cast)

                }

                is CastUiState.Error -> {
                    Toast.makeText(context, "Error download cast", Toast.LENGTH_SHORT).show()

                }

                is CastUiState.Loading -> {

                }

            }
        }

        viewModel.detailMovies.observe(viewLifecycleOwner){
            when (it) {
                is DetailUiState.Success -> {
                    binding.movie = it.data
                    genreAdapter.differ.submitList(it.data.genres)
                    movie = it.data
                    binding.progressBarDetail.gone()
                }

                is DetailUiState.Error -> {
                    Toast.makeText(context, "Error download details", Toast.LENGTH_SHORT).show()
                    binding.progressBarDetail.gone()
                }

                is DetailUiState.Loading -> {
                    binding.progressBarDetail.visible()
                }

            }
        }
    }

    private fun getData(id: Int) {
        viewModel.getDetailMovie(id)
        viewModel.getCastDetail(id)
    }
    private fun setRecyclerViews() {
        binding.rvDetailGenre.adapter = genreAdapter
        binding.recyclerViewCast.adapter = castAdapter
    }


    private fun shareMovie() {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, movie.title)
            type = "text/plain"
        }

        startActivity(Intent.createChooser(sendIntent, null))
    }

    private fun setButtons(){
        binding.imageViewSend.setOnClickListener{
            shareMovie()
        }

        binding.constraintLayoutRating.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.toRating(movie.id.toString()))
        }

        binding.buttonDownload.setOnClickListener {
            val dialogFragment = DownloadDialogFragment()
            dialogFragment.show(requireActivity().supportFragmentManager, "My Fragment")

        }
    }

    private fun setViewPager(){
        with(binding) {
            viewPagerTablayout.adapter =
                viewPagerTabLayoutAdapter(childFragmentManager, lifecycle, args.id.toInt())

            val tabsArray = arrayOf(
                "Trailers",
                "More Like This",
                "Comments",
            )

            TabLayoutMediator(tabLayout, viewPagerTablayout) { tab, position ->
                tab.text = tabsArray[position]
            }.attach()
        }
    }



}

