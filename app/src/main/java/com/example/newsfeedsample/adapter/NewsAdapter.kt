package com.example.newsfeedsample.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.newsfeedsample.NewsFragmentDirections
import com.example.newsfeedsample.R
import com.example.newsfeedsample.model.Article
import com.example.newsfeedsample.utils.simplifyIsoDate
import kotlinx.android.synthetic.main.rc_item.view.*

class NewsAdapter(context: Context, val params: MutableMap<String, Any?> = mutableMapOf()) : PagingDataAdapter<Article, NewsAdapter.NewsViewHolder>(DiffCallback()) {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.itemView.setOnClickListener {
            if (item != null) {
                val action = NewsFragmentDirections.actionNewsFragmentToArticleFragment(item)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = layoutInflater.inflate(R.layout.rc_item, parent, false)
        return NewsViewHolder(view, params)
    }

    class NewsViewHolder(itemView: View, private val params: Map<String, Any?>) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article?) {
            with(itemView) {
                item_article_image.load(article?.urlToImage) {
                    placeholder(R.drawable.img_placeholder)
                }
                item_article_title.text = article?.title
                item_article_topic.text = params.get("category")?.toString() ?: ""
                item_article_publishedAt.text = simplifyIsoDate(article?.publishedAt)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.title == newItem.title
    }

}