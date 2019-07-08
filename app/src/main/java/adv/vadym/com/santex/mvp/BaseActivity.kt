package adv.vadym.com.santex.mvp

import adv.vadym.com.santex.R
import android.os.Bundle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.FrameLayout

abstract class BaseActivity : AppCompatActivity(), IView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
//        val fullView = layoutInflater.inflate(R.layout.activity_nav_drawer, null) as DrawerLayout
//        val activityContainer = fullView.findViewById<View>(R.id.content_base) as FrameLayout
//        layoutInflater.inflate(layoutResID, activityContainer, false)
        super.setContentView(layoutResID)
    }

    abstract fun init(savedInstanceState: Bundle?)



    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadError(e: Throwable) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadError(msg: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}