package com.example.mvvmrecipeapp.ui.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvmrecipeapp.data.model.RecipeNetworkEntity
import com.example.mvvmrecipeapp.databinding.RecipeItemBinding

class RecipeAdapter(private val recipes: ArrayList<RecipeNetworkEntity>): RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    fun setData(recipesList: List<RecipeNetworkEntity>){
        recipes.clear()
        recipes.addAll(recipesList)
    }

    class RecipeViewHolder(private val binding: RecipeItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeNetworkEntity){
            binding.tvId.text = "${recipe.rating?: 0}"
            binding.tvTitle.text = recipe.title
            Glide.with(binding.root.context)
                .load(recipe.featuredImage)
                .into(binding.igvFood)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder =
        RecipeViewHolder(RecipeItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }
}