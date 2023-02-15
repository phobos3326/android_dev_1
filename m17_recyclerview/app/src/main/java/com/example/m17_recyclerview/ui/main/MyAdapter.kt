package com.example.m17_recyclerview.ui.main


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.m17_recyclerview.databinding.ListItemBinding
import com.example.m17_recyclerview.entity.ModelPhotos

class MyAdapter : RecyclerView.Adapter<MyViewHolder>() {

    private var data: List<ModelPhotos.Photo> = emptyList()
    fun setData(photo: List<ModelPhotos.Photo>) {
        this.data = photo
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = data.getOrNull(position)




        with(holder.binding) {
            textViewRover.text = item?.rover?.name
            textVieCamera.text=item?.camera?.name
            textViewSol.text=item?.sol.toString()
            textVieDate.text=item?.earthDate
            item.let {
                Glide.with(imageView.context)
                    .load(it?.imgSrc)
                    .into(holder.binding.imageView)
            }
        }

        holder.binding.root.setOnClickListener { }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)