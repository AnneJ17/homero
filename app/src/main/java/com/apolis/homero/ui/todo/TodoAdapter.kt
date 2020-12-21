package com.apolis.homero.ui.todo

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.apolis.homero.R
import com.apolis.homero.helpers.inflate

class TodoAdapter :  RecyclerView.Adapter<TodoAdapter.ViewHolder>(){

    private var itemList: ArrayList<String> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = parent.inflate(R.layout.todo_row_adapter, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(itemList[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun onBind(url: String) {
        }
    }
}