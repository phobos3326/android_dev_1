package com.example.m17_recyclerview.ui.main


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.m17_recyclerview.R
import com.example.m17_recyclerview.databinding.ListItemBinding
import com.example.m17_recyclerview.entity.ModelPhotos

class MyAdapter(val photo: List<ModelPhotos.Photo>) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = photo[position]
        val image = photo[position].imgSrc
        val context = holder.itemView.context


        Glide.with(context)
            .load(image)
            .into(holder.binding.imageView)



        holder.binding.textViewRover.text = buildString {
            append(context.getString(R.string.rover))
            append(item.rover.name)
        }
        holder.binding.textVieCamera.text = buildString {
            append(context.getString(R.string.camera))
            append(item.camera.name)
        }
        holder.binding.textViewSol.text = buildString {
            append(context.getString(R.string.sol))
            append(item.sol.toString())
        }
        holder.binding.textVieDate.text = buildString {
            append(context.getString(R.string.date))
            append(item.earthDate)
        }
    }

    override fun getItemCount(): Int {
        return photo.size
    }
}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)