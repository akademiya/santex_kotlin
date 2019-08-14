package adv.vadym.com.santex.mvp

import adv.vadym.com.santex.R
import adv.vadym.com.santex.view.impl.ContactsActivity
import adv.vadym.com.santex.view.impl.FeedbackActivity
import adv.vadym.com.santex.view.impl.MainActivity
import adv.vadym.com.santex.view.impl.PriceActivity
import android.app.ProgressDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.Toast
import java.net.URL

abstract class BaseActivity : AppCompatActivity(), IView, NavigationView.OnNavigationItemSelectedListener {

    private lateinit var toolbar: Toolbar
    private lateinit var drawer: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toggle: ActionBarDrawerToggle
    private var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initProgressDialog()
        init(savedInstanceState)
    }

    override fun setContentView(layoutResID: Int) {
        val fullView = layoutInflater.inflate(R.layout.activity_nav_drawer, null) as DrawerLayout
        val activityContainer = fullView.findViewById<View>(R.id.content_base) as FrameLayout
        layoutInflater.inflate(layoutResID, activityContainer, true)
        super.setContentView(fullView)

        toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        drawer = findViewById<View>(R.id.drawer_layout) as DrawerLayout
        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.drawer_open, R.string.drawer_close)
        drawer.addDrawerListener(toggle)
        toggle.drawerArrowDrawable.color = resources.getColor(R.color.white)
        toggle.syncState()
        navigationView = findViewById<View>(R.id.nav_view) as NavigationView
        navigationView.setNavigationItemSelectedListener(this)
    }

    abstract fun init(savedInstanceState: Bundle?)


    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when (menuItem.itemId) {
            R.id.nav_services -> startActivity(Intent(this, MainActivity::class.java))
            R.id.nav_price -> startActivity(Intent(this, PriceActivity::class.java))
            R.id.nav_feedback -> startActivity(Intent(this, FeedbackActivity::class.java))
            R.id.nav_contacts -> startActivity(Intent(this, ContactsActivity::class.java))
            R.id.nav_share -> {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                val shareBody = getString(R.string.share_body)
                sharingIntent.apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_SUBJECT, "Santex")
                    putExtra(Intent.EXTRA_TEXT, shareBody + URL("http", "play.google.com", "store/apps/details?id=adv.vadym.com.santex"))
                }
                startActivity(Intent.createChooser(sharingIntent, "Share:"))
            }
            R.id.nav_improve -> {
                val sendIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:vadym.adv@gmail.com"))
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Для разработчика Santex")
                startActivity(Intent.createChooser(sendIntent, "Для разработчика Santex:"))
            }
        }
        drawer.findViewById<View>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    private fun initProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this)
            mProgressDialog!!.isIndeterminate = true
            mProgressDialog!!.setCancelable(false)
        }
    }

    private fun showProgress(msgResId: Int, keyListener: DialogInterface.OnKeyListener?) {
        if (isFinishing || mProgressDialog!!.isShowing) return
        if (msgResId != 0) {
            mProgressDialog?.setMessage(resources.getString(msgResId))
        }
        if (keyListener != null) {
            mProgressDialog?.setOnKeyListener(keyListener)
        } else {
            mProgressDialog?.setCancelable(false)
        }
        mProgressDialog?.show()
    }

    override fun showLoading() {
        showProgress(R.string.loading, null)
    }

    override fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog?.dismiss()
        }
    }

    override fun loadError(e: Throwable) {
        loadError(e.localizedMessage)
    }

    override fun loadError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}