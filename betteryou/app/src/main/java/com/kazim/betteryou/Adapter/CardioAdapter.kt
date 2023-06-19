package com.kazim.betteryou.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kazim.betteryou.Data.SportResponse
import com.kazim.betteryou.R
import com.kazim.betteryou.databinding.MovesItemBinding


//adapter of list of cardio
class CardioAdapter (private val post:List<SportResponse>): RecyclerView.Adapter<CardioAdapter.RecyclerHolder>() {
    class RecyclerHolder(val binding:MovesItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val binding =MovesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.binding.nametext.text=post[position].name
        Glide.with(holder.itemView.context).load(R.drawable.cardio).into(holder.binding.imageView)
        holder.binding.infotext.text=post[position].instructions


    }

    override fun getItemCount(): Int {
        return post.size
    }
}