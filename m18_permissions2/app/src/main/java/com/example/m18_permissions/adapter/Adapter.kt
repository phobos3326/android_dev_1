package com.example.m18_permissions.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.m18_permissions.database.Photo
import com.example.m18_permissions.databinding.ListItemBinding
import javax.inject.Inject

class Adapter @Inject constructor(private val repository: () -> Unit) :
    RecyclerView.Adapter<MyViewHolder>() {


    private var data: List<Photo> = emptyList()

    fun setData(photo: List<Photo>) {
        this.data = photo
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data.getOrNull(position)
        with(holder.binding){
            item.let {
                Glide.with(imageView.context)
                    .load(it?.photo)
                    .into(holder.binding.imageView)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {

}