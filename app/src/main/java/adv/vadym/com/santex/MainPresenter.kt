package adv.vadym.com.santex

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
        view?.changeButtonColor()
    }
}