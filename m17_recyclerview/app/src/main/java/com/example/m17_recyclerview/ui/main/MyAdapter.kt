package com.example.m17_recyclerview.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.m17_recyclerview.databinding.ListItemBinding
import com.example.m17_recyclerview.entity.ModelPhotos

class MyAdapter( val photo: List<ModelPhotos.Photo>) : RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = photo[position]
        holder.binding.textField.text = item.toString()
    }

    override fun getItemCount(): Int {
        return photo.size
    }
}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)