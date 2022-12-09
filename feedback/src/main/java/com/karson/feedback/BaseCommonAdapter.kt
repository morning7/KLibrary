package com.karson.feedback

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseCommonAdapter<VH: ViewHolder, T> : BaseAbstractAdapter<ViewHolder, T>() {
    private var state: State = State.LOADING

    fun setState(state: State) {
        this.state = state
    }

    override fun getItemCount(): Int {
        if (getData().isEmpty()) {
            if (state == State.NONE || state == State.LOADING || state == State.ERROR) {
                return 1
            }
        }
        return getData().size
    }

    override fun getItemViewType(position: Int): Int {
        return when (state) {
            State.NONE -> {
                State.NONE.value
            }
            State.LOADING -> {
                State.LOADING.value
            }
            State.ERROR -> {
                State.ERROR.value
            }
            else -> {
                State.SUCCESS.value
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            State.NONE.value -> {
                onCreateEmptyViewHolder(parent)
            }
            State.LOADING.value -> {
                onCreateLoadingViewHolder(parent)
            }
            State.ERROR.value -> {
                onCreateErrorViewHolder(parent)
            }
            else -> {
                onCreateViewHolder(parent)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bindHolder(holder, position)
        if (holder is BaseViewHolder) {
            holder.itemView.setOnClickListener {
                callback?.onItemClick(getData()[position])
            }
        }
    }

    abstract fun bindHolder(holder: ViewHolder, position: Int)

    private fun onCreateEmptyViewHolder(parent: ViewGroup): ViewHolder {
        val emptyView = LayoutInflater.from(parent.context).inflate(R.layout.item_empty, parent, false)
        return object : ViewHolder(emptyView) {}
    }

    private fun onCreateLoadingViewHolder(parent: ViewGroup): ViewHolder {
        val loadingView = LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
        return object : ViewHolder(loadingView) {}
    }

    private fun onCreateErrorViewHolder(parent: ViewGroup): ViewHolder {
        val loadingView = LayoutInflater.from(parent.context).inflate(R.layout.item_error, parent, false)
        return object : ViewHolder(loadingView) {}
    }

    abstract fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder

    open class BaseViewHolder(view: View) : ViewHolder(view) {

    }

    enum class State(val value: Int) {
        NONE(0), LOADING(1), SUCCESS(2), ERROR(3);
    }

    interface Callback<T> {
        fun onItemClick(item : T)
    }

    private var callback: Callback<T>? = null

    fun setCallback(callback: Callback<T>) {
        this.callback = callback
    }

}