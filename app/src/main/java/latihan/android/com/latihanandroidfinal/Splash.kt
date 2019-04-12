package latihan.android.com.latihanandroidfinal

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.view.Window

class Splash: AppCompatActivity() {
    private var delayHandler : Handler? = null
    private val splashDelay : Long = 3000

    internal val mRunnable : Runnable = Runnable {
        if(!isFinishing){
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.splash)

        delayHandler = Handler()
        delayHandler!!.postDelayed(mRunnable, splashDelay)

    }

    override fun onDestroy() {
        if(delayHandler != null){
            delayHandler!!.removeCallbacks(mRunnable)
        }
        super.onDestroy()
    }
}