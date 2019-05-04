package latihan.android.com.latihanandroidfinal.upload

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.other_ask_type.*
import latihan.android.com.latihanandroidfinal.R
import java.io.IOException
import java.util.*

class OtherAskType: AppCompatActivity() {
    val PICK_IMAGE_REQUEST = 71
    val PERMISSION_REQUEST_CODE = 1001
    var value = 0.0
    lateinit var filepath : Uri
    lateinit var storage : FirebaseStorage
    lateinit var storageRefence : StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.other_ask_type)
        storage = FirebaseStorage.getInstance()
        storageRefence = storage.reference
        upload.setOnClickListener {
            when {
                (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) -> {
                    if (ContextCompat.checkSelfPermission(
                            this@OtherAskType, Manifest.permission.READ_EXTERNAL_STORAGE
                        ) != PackageManager.PERMISSION_GRANTED
                    ) {
                        requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_REQUEST_CODE)
                    } else {
                        chooseImage()
                    }
                }
                else->chooseImage()
            }
        }
        set_location.setOnClickListener {
            Toast.makeText(applicationContext, "location", Toast.LENGTH_SHORT).show()
        }
        post_other.setOnClickListener {
            uploadFile()
        }
    }

    private fun uploadFile() {
        val progress = ProgressDialog(this).apply {
            setTitle("Uploading Picture...")
            setCancelable(false)
            setCanceledOnTouchOutside(false)
            show()
        }
        var ref: StorageReference = storageRefence.child("OtherAskType/"+ UUID.randomUUID().toString())
        ref.putFile(filepath).addOnSuccessListener {
                taskSnapshot ->
            progress.dismiss()
            Toast.makeText(this@OtherAskType, "Uploaded", Toast.LENGTH_SHORT).show()
            imgView.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.mipmap.ic_launcher))
        }.addOnFailureListener {
                e-> progress.dismiss()
            Toast.makeText(this@OtherAskType, "Failed"+e.message, Toast.LENGTH_SHORT).show()

        }.addOnProgressListener {
                taskSnapshot ->
            value = (100.0 * taskSnapshot.bytesTransferred)/ taskSnapshot.totalByteCount
            progress.setMessage("Upload.."+ value.toInt()+"%")
        }
    }

    private fun chooseImage() {
        val intent = Intent().apply {
            type = "image/*"
            action = Intent.ACTION_GET_CONTENT
        }
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.data != null){
            filepath = data.data
            try {
                var bitmap: Bitmap = MediaStore.Images.Media.getBitmap(contentResolver, filepath)
                imgView.setImageBitmap(bitmap)
            }catch (e : IOException){
                e.printStackTrace()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode){
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isEmpty()|| grantResults[0]== PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this@OtherAskType, "Oops! Permission Denied!!", Toast.LENGTH_SHORT).show()
                else
                    chooseImage()
            }
        }
    }
}