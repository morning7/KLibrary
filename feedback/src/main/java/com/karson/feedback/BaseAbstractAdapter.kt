package com.karson.feedback

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView


abstract class BaseAbstractAdapter<VH: RecyclerView.ViewHolder, T> : RecyclerView.Adapter<VH>() {

    private var data = mutableListOf<T>()

    fun getData() = data.toList()

    @SuppressLint("NotifyDataSetChanged")
    fun addData(list: List<T>, reload: Boolean = false) {
        if (list.isEmpty()) {
            return
        }

        if (reload) {
            data.clear()
        }

        if (data.isEmpty()) {
            data = list.toMutableList()
        } else {
            data.addAll(list)
        }
        notifyDataSetChanged()
    }

}