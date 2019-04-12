package latihan.android.com.latihanandroidfinal.upload

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.people_wanted_type.*
import latihan.android.com.latihanandroidfinal.R

class PeopleWantedType: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.people_wanted_type)
        upload.setOnClickListener {
            Toast.makeText(applicationContext, "Upload", Toast.LENGTH_SHORT).show()
        }
        set_location.setOnClickListener {
            Toast.makeText(applicationContext, "location", Toast.LENGTH_SHORT).show()
        }
        post_other.setOnClickListener {
            Toast.makeText(applicationContext, "post", Toast.LENGTH_SHORT).show()
        }
    }
}