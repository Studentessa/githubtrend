package com.martinez.xapoapp.presentation.paymentList.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import coil.load
import coil.transform.CircleCropTransformation
import com.martinez.data.domain.ProjectDomainModel
import com.martinez.xapoapp.R
import com.martinez.xapoapp.base.setTextViewDrawableColor
import com.martinez.xapoapp.databinding.ItemFragmentListProjectBinding

class ProjectRecyclerViewAdapter(
    private val values: List<ProjectDomainModel>,
    val mListener: (Int, ItemFragmentListProjectBinding) -> Unit
) : RecyclerView.Adapter<ProjectRecyclerViewAdapter.ProjectViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = ItemFragmentListProjectBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        val item = values[position]
        with(holder){
            ViewCompat.setTransitionName(binding.textViewAuthor, "textTransitionAuthor_${item.name}")
            ViewCompat.setTransitionName(binding.textViewName, "textTransitionName_${item.name}")
            ViewCompat.setTransitionName(binding.imageView3, "textTransitionLanguage_${item.name}")
            binding.textViewAuthor.text = item.author
            binding.textViewDescription.text = item.description
            binding.textViewForks.text = item.forks.toString()
            binding.textViewStarts.text = item.stars.toString()
            binding.textViewName.text = item.name
            if (!item.language.isNullOrEmpty()){
                binding.textViewLanguage.visibility = View.VISIBLE
                binding.textViewLanguage.text = item.language
            } else {
                binding.textViewLanguage.visibility = View.GONE
            }

            if (!item.languageColor.isNullOrEmpty()){
                binding.textViewLanguage.setTextViewDrawableColor(item.languageColor!!)
            }
            binding.root.setOnClickListener {
                mListener(position, binding)
            }

            binding.imageView3.load(item?.avatar){
                placeholder(R.mipmap.ic_launcher_round)
                transformations(CircleCropTransformation())
            }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ProjectViewHolder(val binding: ItemFragmentListProjectBinding) : RecyclerView.ViewHolder(binding.root)
}