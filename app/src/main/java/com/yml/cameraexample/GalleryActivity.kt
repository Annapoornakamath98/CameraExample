package com.yml.cameraexample

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.lang.Exception

private lateinit var galleryBtn: Button
private lateinit var imageViewN: ImageView
private lateinit var recyclerView: RecyclerView
private lateinit var storageDir: File
private val filePath = mutableListOf<String>()
private lateinit var img : File
class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        recyclerView = findViewById(R.id.recyclerView1)


        try{
            storageDir= File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!.path)
            for(i in storageDir.list().indices){
                img = File(storageDir.path + "/" + storageDir.list()[i])
                filePath.add(img.absolutePath)

            }
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = ImageAdapter(filePath)

        }catch (exception: Exception){
            Toast.makeText(applicationContext,exception.toString(), Toast.LENGTH_LONG).show()
        }
    }
}