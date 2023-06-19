package com.kazim.betteryou.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kazim.betteryou.Data.Message
import com.kazim.betteryou.databinding.AnsweritemlayoutBinding


//adapter of list of Message
class MessageAdapter(private val post:ArrayList<Message>): RecyclerView.Adapter<MessageAdapter.RecyclerHolder>() {
    class RecyclerHolder(val binding:AnsweritemlayoutBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val binding =AnsweritemlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.binding.answertext.text =post[position].answer
        holder.binding.askText.text=post[position].message
    }

    override fun getItemCount(): Int {
        return post.size
    }
}