package com.kazim.betteryou.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kazim.betteryou.Data.Message
import com.kazim.betteryou.Data.SuppData
import com.kazim.betteryou.databinding.AnsweritemlayoutBinding
import com.kazim.betteryou.databinding.SupplamentlayoutBinding


//adapter of list of Supplement
class SupplementAdapter (private val post:ArrayList<SuppData>): RecyclerView.Adapter<SupplementAdapter.RecyclerHolder>() {
    class RecyclerHolder(val binding: SupplamentlayoutBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val binding = SupplamentlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.binding.supptext.text =post[position].name
        holder.binding.contenttext.text=post[position].content
    }

    override fun getItemCount(): Int {
        return post.size
    }
}