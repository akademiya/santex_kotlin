package adv.vadym.com.santex

import adv.vadym.com.santex.di.component.ApplicationComponent
import adv.vadym.com.santex.di.component.DaggerApplicationComponent
import adv.vadym.com.santex.di.module.AppModule
import android.app.Application

class AndroidApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()

        applicationComponent.inject(this)
    }
}