package com.martinez.xapoapp.presentation.paymentList

import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.martinez.xapoapp.R

import com.martinez.xapoapp.presentation.paymentList.adapter.LanguageAdapter
import kotlinx.android.synthetic.main.fragment_language_list_dialog_list_dialog.*

class LanguageListDialogFragment : BottomSheetDialogFragment() {

    private val projectViewModel: ProjectListDetailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_language_list_dialog_list_dialog,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerVLanguage?.layoutManager =
            LinearLayoutManager(context)
        recyclerVLanguage?.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        recyclerVLanguage?.adapter =
            LanguageAdapter(projectViewModel.languages){
                it?.let {
                    projectViewModel.codeLanguageHasChange(it)
                }
                dismiss()
            }
    }
}