package adv.vadym.com.santex.presenter

import adv.vadym.com.santex.AndroidApplication
import adv.vadym.com.santex.view.impl.MainActivity
import adv.vadym.com.santex.mvp.BasePresenter
import android.app.Application

class MainPresenter(mainView: MainActivity, applicationComponent: Application) : BasePresenter<MainActivity>(mainView) {

    init {
        (applicationComponent as AndroidApplication).applicationComponent.inject(this)
    }

    override fun onBindView() {

    }

    override fun onUnbindView() {

    }

    fun onCallToSantehnicButtonClick() {
        view?.dialogCreateMessageOrder()
    }
}