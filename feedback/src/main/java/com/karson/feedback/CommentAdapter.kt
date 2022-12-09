package com.karson.feedback

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karson.feedback.databinding.ItemCommentBinding
import java.text.SimpleDateFormat
import java.util.*

class CommentAdapter : BaseCommonAdapter<CommentAdapter.CommentHolder, CommentInfo>() {

    override fun onCreateViewHolder(parent: ViewGroup): BaseViewHolder {
        return CommentHolder(ItemCommentBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun bindHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CommentHolder) {
            holder.bind(getData()[position])
        }
    }

    inner class CommentHolder(private val itemBinding: ItemCommentBinding) :BaseViewHolder(itemBinding.root) {

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

