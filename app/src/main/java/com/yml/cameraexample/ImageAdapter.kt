package com.yml.cameraexample

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(private val uList: List<String>) : RecyclerView.Adapter<ImageAdapter.ResponseViewHolder>() {
    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): ResponseViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.image_list, parent, false)
        return ResponseViewHolder(itemView)
    }

    class ResponseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val responseItemView: ImageView = itemView.findViewById(R.id.images)
    }

    override fun onBindViewHolder(holder: ResponseViewHolder, position: Int) {
       // holder.responseItemView.setImageResource(uList[position] as Int)
        val bitmap= BitmapFactory.decodeFile(uList[position])
        holder.responseItemView.setImageBitmap(bitmap)
    }

    override fun getItemCount(): Int {
            return uList.size
    }
}
