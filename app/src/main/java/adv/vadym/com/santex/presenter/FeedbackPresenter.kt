package adv.vadym.com.santex.presenter

import adv.vadym.com.santex.AndroidApplication
import adv.vadym.com.santex.mvp.BasePresenter
import adv.vadym.com.santex.view.impl.FeedbackActivity
import android.app.Application

class FeedbackPresenter(view: FeedbackActivity, applicationComponent: Application) : BasePresenter<FeedbackActivity>(view) {

    init {
        (applicationComponent as AndroidApplication).applicationComponent.inject(this)
    }

    override fun onBindView() {

    }

    override fun onUnbindView() {

    }

}