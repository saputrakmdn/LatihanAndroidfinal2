package latihan.android.com.latihanandroidfinal.adapter

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_add.*
import latihan.android.com.latihanandroidfinal.R
import latihan.android.com.latihanandroidfinal.upload.FullCostType

class AddFragment: Fragment() {
    companion object {
        fun getInstance() : AddFragment = AddFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fct.setOnClickListener {
            val intent = Intent(context, FullCostType::class.java)
            startActivity(intent)
        }
    }
}