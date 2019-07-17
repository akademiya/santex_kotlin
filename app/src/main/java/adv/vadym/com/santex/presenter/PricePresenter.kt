package adv.vadym.com.santex.presenter

import adv.vadym.com.santex.AndroidApplication
import adv.vadym.com.santex.mvp.BasePresenter
import adv.vadym.com.santex.view.impl.PriceActivity
import android.app.Application

class PricePresenter(view: PriceActivity, applicationComponent: Application) : BasePresenter<PriceActivity>(view) {

    init {
        (applicationComponent as AndroidApplication).applicationComponent.inject(this)
    }

    override fun onBindView() {
    }

    override fun onUnbindView() {

    }

}