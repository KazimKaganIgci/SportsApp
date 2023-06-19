package com.kazim.betteryou.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kazim.betteryou.Data.Blog
import com.kazim.betteryou.databinding.SupplamentlayoutBinding


//adapter of blog list
class BlogAdapter(private val post:ArrayList<Blog>): RecyclerView.Adapter<BlogAdapter.RecyclerHolder>() {
    class RecyclerHolder(val binding: SupplamentlayoutBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val binding = SupplamentlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.binding.supptext.text =post[position].title
        holder.binding.contenttext.text=post[position].content
    }

    override fun getItemCount(): Int {
        return post.size
    }
}