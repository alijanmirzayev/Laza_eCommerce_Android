package com.alijan.laza.presentation.detail

import com.alijan.laza.databinding.FragmentDetailBinding
import com.alijan.laza.presentation.BaseFragment

class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override fun layoutInflater(): FragmentDetailBinding {
        return FragmentDetailBinding.inflate(layoutInflater)
    }

    override fun setupUI() {

    }

}