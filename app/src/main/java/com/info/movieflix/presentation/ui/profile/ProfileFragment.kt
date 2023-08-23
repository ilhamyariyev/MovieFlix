package com.info.movieflix.presentation.ui.profile

import android.app.AlertDialog
import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.databinding.FragmentProfileBinding
import de.hdodenhof.circleimageview.CircleImageView


class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {
    private lateinit var auth : FirebaseAuth

    override fun onViewCreateFinish() {
        with(binding){
            constraintLayoutJoinPremium.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.toSubscribe())
            }
            clProfile.setOnClickListener {
                findNavController().navigate(ProfileFragmentDirections.toEditProfile())
            }
        }

        binding.clLogout.setOnClickListener {
//            val binding1:ItemLogoutBinding
//            binding1= ItemLogoutBinding.inflate(layoutInflater)
//            val dialog = BottomSheetDialog(requireContext(), R.style.DialogStyle)
//            val view = layoutInflater.inflate(R.layout.item_logout, null)
//            dialog.setContentView(view)
//            dialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
//            dialog.show()
//
//
//            binding1.buttonNo.setOnClickListener {
//                dialog.dismiss()
//            }
//            binding1.buttonYes.setOnClickListener {
//                auth= FirebaseAuth.getInstance()
//                auth.signOut()
//                findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSignInFragment())
//                dialog.dismiss()
//            }
            val alertDialog = AlertDialog.Builder(requireContext()).apply {
                setTitle("Exit")
                setMessage("Do you want to exit?")
                setNegativeButton("No"){dialog,_ ->
                    dialog.dismiss()
                }
                setPositiveButton("Yes"){dialog,_->
                    auth= FirebaseAuth.getInstance()
                    auth.signOut()
                    findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToSignInFragment())
                    dialog.dismiss()
                }
            }
            alertDialog.create()
            alertDialog.show()

        }

        binding.cardView.setOnClickListener {
            with(binding){
                uploadImage(imageView18)
            }

        }


    }

    private fun uploadImage(imageViewProfile: CircleImageView?) {
        val intent = Intent()
        intent.action= Intent.ACTION_GET_CONTENT
        intent.type ="image/*"
        startActivityForResult(intent,1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==1){
            binding.imageView18.setImageURI(data?.data)
        }
    }

    override fun observeEvents() {

    }

}