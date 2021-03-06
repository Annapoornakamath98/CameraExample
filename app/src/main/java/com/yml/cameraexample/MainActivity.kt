package com.yml.cameraexample

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.File
import java.io.IOException
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

var imageList: MutableList<Any>? = null
var photoURI: Uri? = null
private lateinit var click: Button
//private lateinit var tv: TextView
private lateinit var disp: Button
private lateinit var image: ImageView
private lateinit var showBtn: Button
private lateinit var recycler: RecyclerView
private lateinit var imageDir: File
private val filePath = mutableListOf<String>()
lateinit var imageFile : File
@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        click = findViewById(R.id.btnClick)
        image = findViewById(R.id.imageView)
        disp = findViewById(R.id.btnDisplay)
        showBtn = findViewById(R.id.btnShow)
        recycler = findViewById(R.id.recyclerView)
        click.setOnClickListener {
            openCamera()
        }
        disp.setOnClickListener {
            startActivity(Intent(this, GalleryActivity::class.java))
        }
        showBtn.setOnClickListener {
            display()
        }
    }

    private fun openCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                photoFile?.also {
                    photoURI = FileProvider.getUriForFile(
                            this,
                            "com.example.android.fileprovider",
                            it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, 44)
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 44 && resultCode == RESULT_OK) {
            //val bitmap = data?.extras?.get("data")
            //image.setImageBitmap(bitmap as Bitmap?)
            val bitmap: Bitmap = BitmapFactory.decodeFile(currentPhotoPath)
            // val imageList: MutableList<Any>? = null
            //imageList?.add(currentPhotoPath)
            image.setImageBitmap(bitmap)
            image.setImageURI(photoURI)
            galleryAddPic()

        }
    }


    lateinit var currentPhotoPath: String

    @SuppressLint("SimpleDateFormat")
    @Throws(IOException::class)
    private fun createImageFile(): File {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
                "JPEG_${timeStamp}_",
                ".jpg",
                storageDir
        ).apply {
            currentPhotoPath = absolutePath
        }
    }

    private fun galleryAddPic() {
        Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE).also { mediaScanIntent ->
            val f = File(currentPhotoPath)
            mediaScanIntent.data = Uri.fromFile(f)
            sendBroadcast(mediaScanIntent)

        }
    }

    private fun display() {
        try{
            imageDir= File(getExternalFilesDir(Environment.DIRECTORY_PICTURES)!!.path)

            for(i in imageDir.list().indices){
                imageFile = File(imageDir.path + "/" + imageDir.list()[i])
                val p = imageFile.path
                filePath.add(p)
            }
            recycler.layoutManager = LinearLayoutManager(this)
            recycler.adapter = ImageAdapter( filePath)

        }catch (exception: Exception){
            Toast.makeText(applicationContext,exception.toString(),Toast.LENGTH_LONG).show()
        }



//        val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath())
//        val singleFile = File(file.path + "/" + file.list()[1])
//        val bitmap = BitmapFactory.decodeFile(singleFile.path)
//        image.setImageBitmap(bitmap)

    }

}