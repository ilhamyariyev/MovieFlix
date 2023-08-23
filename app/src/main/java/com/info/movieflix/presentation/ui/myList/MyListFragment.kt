package com.info.movieflix.presentation.ui.myList

import android.app.AlertDialog
import androidx.fragment.app.viewModels
import com.info.movieflix.common.base.BaseFragment
import com.info.movieflix.common.util.gone
import com.info.movieflix.common.util.visible
import com.info.movieflix.databinding.FragmentMyListBinding
import com.info.movieflix.presentation.ui.myList.adapter.FavoriteAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyListFragment : BaseFragment<FragmentMyListBinding>(FragmentMyListBinding::inflate) {
    private val viewModel by viewModels<MyListViewModel>()
    private val favoriteAdapter = FavoriteAdapter()

    override fun onViewCreateFinish() {
        setRecycler()
        viewModel.getFavoriteItemsLiveData()

        favoriteAdapter.onDeleteClick = {

            val alertDialog = AlertDialog.Builder(requireContext()).apply {
                setTitle("Delete item from cart")
                setMessage("Do you want to delete this item from your Favorite?")
                setNegativeButton("Cancel") { dialog, _ ->
                    dialog.dismiss()
                }
                setPositiveButton("Delete") { dialog, _ ->
                    viewModel.deleteFavorite(it)
                    dialog.dismiss()
                }

            }
            alertDialog.create()
            alertDialog.show()
        }
    }

    override fun observeEvents() {
        with(viewModel) {
            favoriteItems.observe(viewLifecycleOwner) {
                if (it.isEmpty()) {
                    showEmptyCart()
                    favoriteAdapter.differ.submitList(it)
                } else {
                    hideEmptyCart()
                    favoriteAdapter.differ.submitList(it)
                }
            }
        }

    }

    private fun setRecycler() {
        binding.rvFavorite.adapter = favoriteAdapter
    }

    private fun hideEmptyCart() {
        binding.emptyList.gone()
    }

    private fun showEmptyCart() {
        binding.emptyList.visible()
    }


}