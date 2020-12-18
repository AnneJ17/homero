package com.apolis.homero.helpers

import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener


class SpinnerBinding {
    // selectedValue is mentioned in the xml
//    @BindingAdapter("android:selectedValue")
//    fun setSelectedPosition(view: AdapterView<Adapter>, position: Int) {
//        if(view.selectedItemPosition != position) {
//            view.setSelection(position)
//        }
//    }
    // selectedValueAttrChanged - no need to add in the xml as it's just a callback for listening
    // the item changed in the spinner
    @BindingAdapter(value = ["selectedValue", "selectedValueAttrChanged"], requireAll = false)
    fun setNewValue(spinner: AppCompatSpinner, choice: String, changeListener: InverseBindingListener) {
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                changeListener.onChange()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                changeListener.onChange()
            }
        }
        var adapter = spinner.adapter as ArrayAdapter<String>
        if(changeListener != null) {
            spinner.setSelection(adapter.getPosition(choice))
        }
    }

    @InverseBindingAdapter(attribute = "selectedValue", event = "selectedValueAttrChanged")
    fun getNewValue(spinner: AppCompatSpinner): String{
       return spinner.selectedItem as String
    }

}