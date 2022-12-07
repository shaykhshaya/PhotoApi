package com.shaya.photosapi.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shaya.photosapi.databinding.GridViewItemBinding
import com.shaya.photosapi.network.Photo

class PhotoGridAdapter: ListAdapter<Photo, PhotoGridAdapter.PhotoViewHolder>(DiffCallback) {



    class PhotoViewHolder(private var binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(Photo: Photo){
            binding.photo = Photo
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.bind(photo)
    }
    companion object DiffCallback: DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.url == newItem.url
        }


    }




}