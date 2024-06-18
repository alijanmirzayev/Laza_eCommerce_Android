package com.alijan.laza.presentation.wishlist

import com.alijan.laza.databinding.FragmentWishlistBinding
import com.alijan.laza.presentation.BaseFragment
import com.alijan.laza.presentation.adapters.NewArrivalAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WishlistFragment : BaseFragment<FragmentWishlistBinding>() {
    private val wishlistAdapter = NewArrivalAdapter()
    override fun layoutInflater(): FragmentWishlistBinding {
        return FragmentWishlistBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        setAdapter()
    }

    private fun setAdapter(){
        binding.rvWishlist.adapter = wishlistAdapter
    }

}