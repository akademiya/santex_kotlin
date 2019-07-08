package adv.vadym.com.santex.di.component

import adv.vadym.com.santex.AndroidApplication
import adv.vadym.com.santex.MainActivity
import adv.vadym.com.santex.MainPresenter
import adv.vadym.com.santex.di.module.AppModule
import dagger.Component

@Component(modules = [AppModule::class])
interface ApplicationComponent {

    fun inject(modApplication: AndroidApplication)

    // VIEW
    fun inject(view: MainActivity)

    // PRESENTER
    fun inject(presenter: MainPresenter)
}