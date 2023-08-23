package com.info.movieflix.presentation.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.info.movieflix.data.dto.DetailsResponseModelItem
import com.info.movieflix.databinding.FragmentRatingBinding
import com.info.movieflix.domain.model.state.DetailUiState
import com.taufiqrahman.reviewratings.BarLabels
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class RatingFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentRatingBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<RatingViewModel>()
    private val args by navArgs<RatingFragmentArgs>()
    private lateinit var movie: DetailsResponseModelItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getData(args.id.toInt())

        val colors = intArrayOf(
            Color.parseColor("#E81E2C"),
            Color.parseColor("#E81E2C"),
            Color.parseColor("#E81E2C"),
            Color.parseColor("#E81E2C"),
            Color.parseColor("#E81E2C")
        )

        val raters = intArrayOf(
            Random.nextInt(100),
            Random.nextInt(100),
            Random.nextInt(100),
            Random.nextInt(100),
            Random.nextInt(100)
        )

        binding.ratingReview.createRatingBars(100, BarLabels.STYPE1,colors,raters)



        viewModel.detailMovies.observe(viewLifecycleOwner){
            when (it) {
                is DetailUiState.Success -> {
                    movie = it.data
                    val vote = movie.voteAverage
                    val formattedVote = String.format("%.1f", vote)
                    binding.Rating.text = formattedVote
                }

                is DetailUiState.Error -> {
                    Toast.makeText(context, "Error download details", Toast.LENGTH_SHORT).show()

                }

                is DetailUiState.Loading -> {

                }

            }
        }

        setButtons()

    }

    private fun getData(id: Int) {
        viewModel.getDetailMovie(id)
    }

    private fun setButtons(){
        binding.buttonC.setOnClickListener {
            dialog?.dismiss()
        }

        binding.buttonS.setOnClickListener {
            Toast.makeText(context, "Your rating was Submitted", Toast.LENGTH_SHORT).show()
            dialog?.dismiss()
        }
    }
}