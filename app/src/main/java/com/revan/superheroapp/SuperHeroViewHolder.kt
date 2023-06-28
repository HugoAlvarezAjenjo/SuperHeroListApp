package com.revan.superheroapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.revan.superheroapp.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superheroItemResponse: SuperHeroItemResponse){
        binding.tvSuperheroName.text = superheroItemResponse.name
        Picasso.get().load(superheroItemResponse.superheroImage.url).into(binding.ivSuperhero)
    }
}