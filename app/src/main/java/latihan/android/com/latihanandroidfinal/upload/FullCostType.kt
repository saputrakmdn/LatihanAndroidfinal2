package latihan.android.com.latihanandroidfinal.upload

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.full_cost_type.*
import latihan.android.com.latihanandroidfinal.R

class FullCostType: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.full_cost_type)
        image_fct.setOnClickListener {
            Toast.makeText(applicationContext, "upload Image", Toast.LENGTH_SHORT).show()
        }
        set_location.setOnClickListener {
            Toast.makeText(applicationContext, "setLocation", Toast.LENGTH_SHORT).show()
        }
        post_full.setOnClickListener {
            Toast.makeText(applicationContext, "Post", Toast.LENGTH_SHORT).show()
        }
    }
}