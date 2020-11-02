package com.yml.cameraexample

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
class AdapterClass(private val context: Context, private val uList: MutableList<Any>) : RecyclerView.Adapter<AdapterClass.ResponseViewHolder>() {
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
        holder.responseItemView.setImageBitmap(uList[position] as Bitmap)
    }

    override fun getItemCount(): Int {
            return uList.size
    }
}
