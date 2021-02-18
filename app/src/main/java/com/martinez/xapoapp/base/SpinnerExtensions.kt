package com.martinez.xapoapp.base

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner


fun Spinner.onItemSelected(onItemSelected: (Int) -> Unit){
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(parent: AdapterView<*>?) {

        }
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            onItemSelected(position)
        }
    }
}

fun Spinner.setAdapter(array: Int){
    ArrayAdapter.createFromResource(
        context,
        array,
        android.R.layout.simple_spinner_item
    ).also { sAdapter ->
        // Specify the layout to use when the list of choices appears
        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        adapter = sAdapter
    }
}

