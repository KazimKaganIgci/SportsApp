package com.kazim.betteryou.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kazim.betteryou.Data.Meal
import com.kazim.betteryou.databinding.FoodRowBinding


//adapter of list of Foods
class FoodAdapter (private val post:List<Meal>): RecyclerView.Adapter<FoodAdapter.RecyclerHolder>() {
    class RecyclerHolder(val binding: FoodRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerHolder {
        val binding = FoodRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecyclerHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerHolder, position: Int) {
        holder.binding.text.text=post[position].strMeal
        Glide.with(holder.itemView.context).load(post[position].strMealThumb).into(holder.binding.imageView)


    }

    override fun getItemCount(): Int {
        return post.size
    }
}