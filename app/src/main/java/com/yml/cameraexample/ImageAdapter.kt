package com.yml.cameraexample

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ImageAdapter(private val context: Context, private val uList: List<Any>) : RecyclerView.Adapter<ImageAdapter.ResponseViewHolder>() {
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
        val image = uList[position]
       // holder.responseItemView.setImageResource(uList[position] as Int)
        Glide.with(context)
                .load(image)
                .into(holder.responseItemView)
    }

    override fun getItemCount(): Int {
            return uList.size
    }
}
