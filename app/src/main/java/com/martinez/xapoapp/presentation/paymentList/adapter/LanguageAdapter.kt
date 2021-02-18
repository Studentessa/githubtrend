package com.martinez.xapoapp.presentation.paymentList.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martinez.xapoapp.data.domain.LanguageDomainModel
import com.martinez.xapoapp.databinding.ItemFragmentListLanguageBinding

class LanguageAdapter constructor(
    private val languageArray: List<LanguageDomainModel>?,
    val mListener: (LanguageDomainModel?) -> Unit
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding = ItemFragmentListLanguageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        holder.binding.text.text = languageArray?.get(position)?.name
        holder.binding.text.setOnClickListener {
            mListener(languageArray?.get(position))
        }
    }

    override fun getItemCount(): Int = languageArray?.size ?: 0

    inner class LanguageViewHolder(val binding: ItemFragmentListLanguageBinding) : RecyclerView.ViewHolder(binding.root)
}

