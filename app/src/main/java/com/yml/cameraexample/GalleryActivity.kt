package com.yml.cameraexample

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private lateinit var galleryBtn: Button
private lateinit var imageViewN: ImageView
private lateinit var recyclerView: RecyclerView

class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        galleryBtn = findViewById(R.id.gallery)
        imageViewN = findViewById(R.id.imageView2)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        galleryBtn.setOnClickListener {
            display()
        }
    }

    private fun display() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(intent, 44)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 44) {
            if (data != null) {
                val imageURI = data.data
                val imageList: MutableList<Any>? = null
                if (imageURI != null) {
                    imageList?.add(data.data!!)
                }
                recyclerView.adapter = imageList?.let { ImageAdapter(this, it) }
                imageViewN.setImageURI(imageURI)
            }
        }
    }
}