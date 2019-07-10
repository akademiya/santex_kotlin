package adv.vadym.com.santex.di.component

import adv.vadym.com.santex.AndroidApplication
import adv.vadym.com.santex.view.impl.MainActivity
import adv.vadym.com.santex.presenter.MainPresenter
import adv.vadym.com.santex.di.module.AppModule
import adv.vadym.com.santex.presenter.ContactsPresenter
import adv.vadym.com.santex.presenter.FeedbackPresenter
import adv.vadym.com.santex.presenter.PricePresenter
import adv.vadym.com.santex.view.impl.ContactsActivity
import adv.vadym.com.santex.view.impl.FeedbackActivity
import adv.vadym.com.santex.view.impl.PriceActivity
import dagger.Component

@Component(modules = [AppModule::class])
interface ApplicationComponent {

    fun inject(modApplication: AndroidApplication)

    // VIEW
    fun inject(view: MainActivity)
    fun inject(view: PriceActivity)
    fun inject(view: FeedbackActivity)
    fun inject(view: ContactsActivity)

    // PRESENTER
    fun inject(presenter: MainPresenter)
    fun inject(presenter: PricePresenter)
    fun inject(presenter: FeedbackPresenter)
    fun inject(presenter: ContactsPresenter)
}