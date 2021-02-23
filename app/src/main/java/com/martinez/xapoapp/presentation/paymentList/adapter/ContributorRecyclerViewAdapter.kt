package com.martinez.xapoapp.presentation.paymentList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.martinez.data.domain.ContributorDomainModel
import com.martinez.xapoapp.R
import com.martinez.xapoapp.databinding.ItemContributorBinding

class ContributorRecyclerViewAdapter constructor(
    private val contributorList: List<ContributorDomainModel>?
) : RecyclerView.Adapter<ContributorRecyclerViewAdapter.ContributorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        val binding = ItemContributorBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ContributorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        holder.binding.imgViewContributor.load(contributorList?.get(position)?.avatar){
            placeholder(R.mipmap.ic_launcher_round)
            transformations(CircleCropTransformation())
        }
    }

    override fun getItemCount(): Int = contributorList?.size ?: 0

    inner class ContributorViewHolder(val binding: ItemContributorBinding) : RecyclerView.ViewHolder(binding.root)
}

