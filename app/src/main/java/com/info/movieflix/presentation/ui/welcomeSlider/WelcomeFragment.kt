package com.info.movieflix.presentation.ui.welcomeSlider

import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.databinding.FragmentWelcomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WelcomeFragment : BaseFragment<FragmentWelcomeBinding>(FragmentWelcomeBinding::inflate) {

    private lateinit var viewPager2: ViewPager2

    private val pager2CallBack = object : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            if (position== PageLists.pageSlides.size -1){
                binding.buttonGetStarted.text="Get Started"
                binding.buttonGetStarted.setOnClickListener {
                    findNavController().navigate(WelcomeFragmentDirections.toinputOptionFragment())
                }
            }else{
                binding.buttonGetStarted.text="Continue"
                binding.buttonGetStarted.setOnClickListener {
                    viewPager2.currentItem = position+1
                }
            }


        }
    }


    override fun onViewCreateFinish() {
        setupViewPager(binding)
        onBackPress()
    }

    override fun observeEvents() {
    }



    private fun setupViewPager(binding: FragmentWelcomeBinding){

        val adapter = PagesAdapter(PageLists.pageSlides)
        viewPager2=binding.viewPager2
        viewPager2.adapter=adapter
        viewPager2.registerOnPageChangeCallback(pager2CallBack)
        binding.dotsIndicator.setViewPager2(viewPager2)
    }

    fun onBackPress(){
        requireActivity().onBackPressedDispatcher.addCallback(this,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigate(WelcomeFragmentDirections.tosplash())
            }
        })
    }

}