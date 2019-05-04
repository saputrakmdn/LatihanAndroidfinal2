package latihan.android.com.latihanandroidfinal

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Window
import latihan.android.com.latihanandroidfinal.helper.UserHelper

class Splash: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash)
        Handler().postDelayed({
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            UserHelper(this).StatusSplash = true
            finish()
        }, 3000)
    }
    }
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        setContentView(R.layout.splash)
//
//        delayHandler = Handler()
//        delayHandler!!.postDelayed(mRunnable, splashDelay)
//
//    }
//
//    override fun onDestroy() {
//        if(delayHandler != null){
//            delayHandler!!.removeCallbacks(mRunnable)
//        }
//        super.onDestroy()
//    }
