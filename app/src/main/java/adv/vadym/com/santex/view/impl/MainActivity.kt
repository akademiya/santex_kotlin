package adv.vadym.com.santex.view.impl

import adv.vadym.com.santex.R
import adv.vadym.com.santex.mvp.BaseActivity
import adv.vadym.com.santex.presenter.MainPresenter
import adv.vadym.com.santex.view.IMainActivity
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), IMainActivity {

    private lateinit var presenter: MainPresenter

    override fun init(savedInstanceState: Bundle?) {
        super.setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, application)

        call_santehnic_button.setOnClickListener { presenter.onCallToSantehnicButtonClick() }

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.bindView(this)
    }

    override fun onDetachedFromWindow() {
        presenter.unbindView(this)
        super.onDetachedFromWindow()
    }

    override fun dialogCreateMessageOrder() {
        val subView = LayoutInflater.from(this).inflate(R.layout.call_master_dialog, null)
        AlertDialog.Builder(this).apply {
            setView(subView)
            create()
            setTitle(resources.getString(R.string.call_master))
            setPositiveButton(R.string.send) {_, _ -> }
            setNegativeButton(R.string.cancel) {_, _ ->}
        }.show().apply {
            getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.negative_button_color))
        }
    }

}