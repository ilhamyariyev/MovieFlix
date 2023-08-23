package com.info.movieflix.presentation.ui.detail.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.info.movieflix.presentation.ui.detail.CommentsFragment
import com.info.movieflix.presentation.ui.detail.MoreLikeThisFragment
import com.info.movieflix.presentation.ui.detail.TrailersFragment


class viewPagerTabLayoutAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val id: Int
)
    :FragmentStateAdapter(fragmentManager,lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0->{
                TrailersFragment(id)
            }
            1->{
                MoreLikeThisFragment(id)
            }
            2->{
                CommentsFragment(id)
            }
            else->Fragment()
        }
    }

}