package com.martinez.xapoapp.presentation.paymentList

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.CircleCropTransformation
import com.martinez.xapoapp.R
import com.martinez.xapoapp.base.setTextViewDrawableColor
import com.martinez.xapoapp.databinding.FragmentProjectDetailBinding
import com.martinez.xapoapp.presentation.paymentList.adapter.ContributorRecyclerViewAdapter
import com.martinez.xapoapp.presentation.paymentList.adapter.ProjectRecyclerViewAdapter

class ProjectDetailFragment : Fragment() {


    private val projectViewModel: ProjectListDetailViewModel by activityViewModels()
    private lateinit var binding: FragmentProjectDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        initDetailView()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ViewCompat.setTransitionName(binding.textViewAuthor, "textTransitionAuthor_${projectViewModel.projectSelected?.name}")
        ViewCompat.setTransitionName(binding.textViewDetailName, "textTransitionName_${projectViewModel.projectSelected?.name}")
        ViewCompat.setTransitionName(binding.imageViewAvatar, "textTransitionLanguage_${projectViewModel.projectSelected?.name}")
    }

    private fun initDetailView() {
        val projectSelected = projectViewModel.projectSelected
        binding.imageViewAvatar.load(projectSelected?.avatar){
            placeholder(R.drawable.ic_baseline_account_circle_100)
            transformations(CircleCropTransformation())
        }
        binding.textViewDetailLanguage.text = projectSelected?.language
        binding.textViewDetailName.text = projectSelected?.name
        binding.textViewAuthor.text = projectSelected?.author
        binding.textViewUrl.text = projectSelected?.url
        binding.textViewDetailForks.text = projectSelected?.forks.toString()
        binding.textViewDetailStarts.text = projectSelected?.stars.toString()
        binding.textViewDetailDescription.text = projectSelected?.description
        if (!projectSelected?.languageColor.isNullOrEmpty()){
            binding.textViewDetailLanguage.setTextViewDrawableColor(projectSelected?.languageColor!!)
        }

        if (!projectSelected?.builtBy.isNullOrEmpty()) {
            binding.recyclerContributors.apply {
                layoutManager = GridLayoutManager(requireContext(), 4)
                adapter = ContributorRecyclerViewAdapter(projectSelected?.builtBy)
            }
        }

    }


}