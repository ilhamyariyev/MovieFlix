package com.info.movieflix.presentation.ui.profile

import android.content.Intent
import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.databinding.FragmentEditProfileBinding
import de.hdodenhof.circleimageview.CircleImageView


class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>(FragmentEditProfileBinding::inflate) {
    override fun onViewCreateFinish() {
        binding.cardView.setOnClickListener {
            with(binding){
                uploadImage(imageViewProfile)
            }

        }
    }

    private fun uploadImage(imageViewProfile: CircleImageView?) {
        val intent = Intent()
        intent.action=Intent.ACTION_GET_CONTENT
        intent.type ="image/*"
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1){
            binding.imageViewProfile.setImageURI(data?.data)
        }
    }

    override fun observeEvents() {

    }

}