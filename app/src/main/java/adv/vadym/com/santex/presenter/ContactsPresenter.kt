package adv.vadym.com.santex.presenter

import adv.vadym.com.santex.AndroidApplication
import adv.vadym.com.santex.R
import adv.vadym.com.santex.makePhoneCall
import adv.vadym.com.santex.view.impl.MainActivity
import adv.vadym.com.santex.mvp.BasePresenter
import adv.vadym.com.santex.view.IContactsActivity
import adv.vadym.com.santex.view.impl.ContactsActivity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri

class ContactsPresenter(view: ContactsActivity, applicationComponent: Application) : BasePresenter<ContactsActivity>(view) {

    init {
        (applicationComponent as AndroidApplication).applicationComponent.inject(this)
    }

    private var recipient = ""
    private var name = ""
    private var phone = ""
    private var message = ""

    override fun onBindView() {

    }

    override fun onUnbindView() {}

    fun onNumberTelClick(number: IContactsActivity.TelProvider) {
        when(number) {
            IContactsActivity.TelProvider.LIFE -> view?.onPhoneNumberClick(number)
            IContactsActivity.TelProvider.KVS -> view?.onPhoneNumberClick(number)
            IContactsActivity.TelProvider.MTS -> view?.onPhoneNumberClick(number)
            IContactsActivity.TelProvider.CITY -> view?.onPhoneNumberClick(number)
        }
    }

    fun onEmailTextСlick(context: Context) {
        val email = context.resources.getString(R.string.email)
        val sendIntent = Intent(Intent.ACTION_SENDTO)
        sendIntent.data = Uri.parse("mailto:$email")
        context.startActivity(Intent.createChooser(sendIntent, "Santex"))
    }

    fun onSendMessageButtonClick() {
        view?.dialogCreateMessage()
    }

    fun onDialogSendButtonClick(context: Context) {
        val email = context.resources.getString(R.string.email)
        val sendIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"))
        sendIntent.data = Uri.parse("mailto:$email")
//        sendIntent.putExtra(Intent.EXTRA_SUBJECT, name)
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "$name, $phone, $recipient\n$message")
        context.startActivity(Intent.createChooser(sendIntent, "Santex"))
    }

    fun dialogData(email: String, name: String, phone: String, message: String) {
        this.recipient = email
        this.name = name
        this.phone = phone
        this.message = message
    }
}