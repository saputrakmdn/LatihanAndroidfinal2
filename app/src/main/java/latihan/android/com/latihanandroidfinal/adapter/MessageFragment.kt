package latihan.android.com.latihanandroidfinal.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import latihan.android.com.latihanandroidfinal.R

class MessageFragment: Fragment() {
    companion object {
        fun getInstance() : MessageFragment = MessageFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_message, container, false)
    }
}