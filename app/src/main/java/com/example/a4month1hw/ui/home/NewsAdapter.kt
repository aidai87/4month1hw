package com.example.a4month1hw.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.a4month1hw.databinding.ItemNewsBinding
import java.text.SimpleDateFormat
import java.util.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    var onLongClick: ((Int) ->Unit?)? = null
    private var data = arrayListOf<News>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(
                    parent
                        .context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
    fun addItem(news: News) {
        data.add(0,news)

    }

    fun delete(pos: Int) {
        data.removeAt(pos)
    }

    inner class NewsViewHolder(private var binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: News) {
            val simpleDateFormat = SimpleDateFormat("HH:mm, dd MMMM yyyy")
            val dataTime = news.time?.let { Date(it) }
            val time: String = simpleDateFormat.format(dataTime)
            binding.textTitle.text = news.title
            binding.textData.text = time

            itemView.setOnLongClickListener {
                onLongClick?.invoke(adapterPosition)
                return@setOnLongClickListener true
            }
        }
    }
}