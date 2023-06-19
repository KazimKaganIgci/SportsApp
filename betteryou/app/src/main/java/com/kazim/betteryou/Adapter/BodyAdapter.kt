package com.kazim.betteryou.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kazim.betteryou.Data.SportResponse
import com.kazim.betteryou.R
import com.kazim.betteryou.databinding.MovesItemBinding


//adapter of list of fitness movements
class BodyAdapter(private val post:List<SportResponse>): RecyclerView.Adapter<BodyAdapter.RecyclerHolder>() {
    class RecyclerHolder(val binding: MovesItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val binding = MovesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.binding.nametext.text=post[position].name
        Glide.with(holder.itemView.context).load(R.drawable.build).into(holder.binding.imageView)
        holder.binding.infotext.text=post[position].instructions


    }

    override fun getItemCount(): Int {
        return post.size
    }
}