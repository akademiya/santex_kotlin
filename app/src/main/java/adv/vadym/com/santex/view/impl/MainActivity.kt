package adv.vadym.com.santex.view.impl

import adv.vadym.com.santex.R
import adv.vadym.com.santex.mvp.BaseActivity
import adv.vadym.com.santex.presenter.MainPresenter
import adv.vadym.com.santex.view.IMainActivity
import android.os.Bundle
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

    override fun changeButtonColor() {
        call_santehnic_button.setBackgroundColor(resources.getColor(R.color.inactive_color))
    }

}