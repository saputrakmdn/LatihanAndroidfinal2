package latihan.android.com.latihanandroidfinal



import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import latihan.android.com.latihanandroidfinal.adapter.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var menu : Menu = navigation.menu
        selectedMenu(menu.getItem(0))
        navigation.setOnNavigationItemSelectedListener {
            item: MenuItem ->  selectedMenu(item)
            true

        }

    }
    private fun selectedMenu(item : MenuItem){
        when(item.itemId){
            R.id.home -> selecttedFragment(HomeFragment.getInstance())
            R.id.search -> selecttedFragment(SearchFragment.getInstance())
            R.id.add -> selecttedFragment(AddFragment.getInstance())
            R.id.message -> selecttedFragment(MessageFragment.getInstance())
            R.id.profile -> selecttedFragment(ProfileFragment.getInstance())

        }
    }
    fun selecttedFragment(fragment: Fragment){
        val manager = supportFragmentManager
        var transaction : FragmentTransaction? = manager.beginTransaction()
        transaction?.replace(R.id.rootFragment, fragment)
        transaction?.commit()


    }
}
