package com.kazim.betteryou.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kazim.betteryou.Data.Note
import com.kazim.betteryou.R
import com.kazim.betteryou.databinding.FoodRowBinding
import com.kazim.betteryou.databinding.NoteitemBinding


//adapter of list of Note
class NoteAdapter (private val post:List<Note>): RecyclerView.Adapter<NoteAdapter.RecyclerHolder>() {
    class RecyclerHolder(val binding: NoteitemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val binding =  NoteitemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.binding.infotext.text=post[position].content
        Glide.with(holder.itemView.context).load(R.drawable.note).into(holder.binding.imageView)
    }

    override fun getItemCount(): Int {
        return post.size
    }
}