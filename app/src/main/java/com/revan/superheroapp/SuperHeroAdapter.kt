package com.revan.superheroapp

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class SuperHeroAdapter(var superheroList: List<SuperHeroItemResponse> = emptyList()) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        return SuperHeroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        )
    }

    override fun getItemCount() = superheroList.size

    override fun onBindViewHolder(holder: SuperHeroViewHolder, position: Int) {
        holder.bind(superheroList[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(superheroList: List<SuperHeroItemResponse>){
        this.superheroList = superheroList
        notifyDataSetChanged()
    }

}