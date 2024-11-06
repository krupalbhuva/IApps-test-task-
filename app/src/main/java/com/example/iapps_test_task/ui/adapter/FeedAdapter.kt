package com.example.iapps_test_task.ui.adapter

import android.annotation.SuppressLint
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.iapps_test_task.databinding.FeedLayoutBinding
import com.example.iapps_test_task.model.ItemsItem

class FeedAdapter : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {

    private var dataList: List<ItemsItem> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setFeed(feeds: List<ItemsItem>) {
        dataList = feeds
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(FeedLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(dataList.get(position))
    }

    class FeedViewHolder(val binding: FeedLayoutBinding) : ViewHolder(binding.root) {
        fun bind(item: ItemsItem) {

            Glide.with(binding.root.context).load(item.media?.m).into(binding.ivImage)

            binding.tvDescription.text =
                Html.fromHtml(item.description, Html.FROM_HTML_MODE_COMPACT)

            binding.cvMain.setOnClickListener {

            }
        }
    }
}