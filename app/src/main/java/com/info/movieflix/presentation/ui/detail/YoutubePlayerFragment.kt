package com.info.movieflix.presentation.ui.detail

import androidx.navigation.fragment.navArgs
import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.databinding.FragmentYoutubePlayerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YoutubePlayerFragment : BaseFragment<FragmentYoutubePlayerBinding>(FragmentYoutubePlayerBinding::inflate) {

    private val args: YoutubePlayerFragmentArgs by navArgs()

    override fun onViewCreateFinish() {
        lifecycle.addObserver(binding.youtubePlayerView)

        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.loadVideo(args.videoId, 0f)
            }
        })
    }

    override fun observeEvents() {

    }

}