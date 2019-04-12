package latihan.android.com.latihanandroidfinal.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import latihan.android.com.latihanandroidfinal.R


class ProfileFragment: Fragment()  {
    companion object {
        fun getInstance() : ProfileFragment = ProfileFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_profile, container, false)
    }
}