package com.karson.feedback

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karson.feedback.databinding.ItemCommentBinding
import java.text.SimpleDateFormat
import java.util.*

class CommentAdapter(commentList: List<CommentInfo>) : RecyclerView.Adapter<CommentAdapter.CommentHolder>() {
    private var data = commentList.toMutableList()

    fun addData(commentList: List<CommentInfo>) {
        data.addAll(commentList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder {
        return CommentHolder(ItemCommentBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: CommentHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    inner class CommentHolder(private val itemBinding: ItemCommentBinding) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(commentInfo: CommentInfo) {
            itemBinding.tvContent.text = commentInfo.content
            itemBinding.tvName.text = commentInfo.creator_name
            itemBinding.tvClient.text = commentInfo.fengshows_client

            val format = SimpleDateFormat("yyy-MM-dd", Locale.getDefault())
            val date = format.format(commentInfo.modified_time)
            itemBinding.tvDate.text = date
        }
    }
}

