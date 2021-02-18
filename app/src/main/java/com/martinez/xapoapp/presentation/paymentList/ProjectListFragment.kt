package com.martinez.xapoapp.presentation.paymentList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.martinez.xapoapp.R
import com.martinez.xapoapp.base.observe
import com.martinez.xapoapp.base.onItemSelected
import com.martinez.xapoapp.base.setAdapter
import com.martinez.xapoapp.base.showSomethingWentWrongDialog
import com.martinez.xapoapp.data.domain.ProjectDomainModel
import com.martinez.xapoapp.databinding.FragmentProjectListBinding
import com.martinez.xapoapp.databinding.ItemFragmentListProjectBinding
import com.martinez.xapoapp.presentation.paymentList.adapter.ProjectRecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_project_list.*


@AndroidEntryPoint
class ProjectListFragment : Fragment() {

    private val projectViewModel: ProjectListDetailViewModel by activityViewModels()
    private lateinit var binding: FragmentProjectListBinding

    private val stateObserver = Observer(::onStateChanged)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectListBinding.inflate(inflater, container, false)
        val view = binding.root
        initRangeSppiner()
        binding.fab.setOnClickListener {
            showBottomSheetFragment()
        }
        return view
    }
    fun showBottomSheetFragment() {
        val bottomSheetFragment = LanguageListDialogFragment()
        bottomSheetFragment.show(parentFragmentManager, bottomSheetFragment.tag)


    }

    private fun initRangeSppiner() {
        binding.spinnerDateRange.setAdapter(R.array.filter_range)
        binding.spinnerDateRange.onItemSelected {
            projectViewModel.rangeHasChange(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(projectViewModel.state, stateObserver)
    }

    private fun onStateChanged(state: ProjectListViewModelState) {
        when (state){
           is ProjectListViewModelState.Empty -> displayEmptyState()
           is ProjectListViewModelState.Loading -> displayLoadingState(state)
            is ProjectListViewModelState.Success -> displayContent(state)
           is ProjectListViewModelState.Error ->
               showSomethingWentWrongDialog { displayEmptyState() }
           is ProjectListViewModelState.LoadingLanguages -> {
               fab.isEnabled = false
           }
           is ProjectListViewModelState.LanguageSuccess -> fab.isEnabled = true
       }
    }

    private fun displayLoadingState(state: ProjectListViewModelState.Loading) {
        if (state.language.isNotEmpty())
            textViewLanguage.text = state.language
        else
            textViewLanguage.text = getString(R.string.projectList_fragment_any_language)
        binding.recyclerView2.visibility = View.GONE
        binding.loadingGroup.visibility = View.VISIBLE
        binding.emptyView.emptyViewParent.visibility = View.GONE
    }

    private fun displayContent(state: ProjectListViewModelState.Success) {
        binding.emptyView.emptyViewParent.visibility = View.GONE
        binding.loadingGroup.visibility = View.GONE
        binding.recyclerView2.visibility = View.VISIBLE
        if (!state.list.isNullOrEmpty()){
            binding.recyclerView2.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = ProjectRecyclerViewAdapter(state.list){ position, binding ->
                    onProjectSelected(state.list[position], binding)
                }
            }
        }
    }

    private fun displayEmptyState() {
        binding.recyclerView2.visibility = View.GONE
        binding.loadingGroup.visibility = View.GONE
        binding.emptyView.emptyViewParent.visibility = View.VISIBLE
        binding.emptyView.textViewEmpty.text = String.format(getString(
            R.string.projectList_fragmentempty_empty_message),
            projectViewModel.codelanguageCode
        )
    }

    private fun onProjectSelected(project: ProjectDomainModel, binding: ItemFragmentListProjectBinding) {
        projectViewModel.projectSelected(project)

        activity?.let {
            val extras = FragmentNavigatorExtras(
                binding.textViewAuthor to "textTransitionAuthor_${project.name}",
                binding.textViewName to "textTransitionName_${project.name}",
                binding.imageView3 to "textTransitionLanguage_${project.name}"
            )
            Navigation.findNavController(it, R.id.nav_host_fragment)
                .navigate(
                    R.id.action_projectListFragment_to_projectDetailFragment,
                    null,
                    null,
                    extras
                )

        }
    }
}


