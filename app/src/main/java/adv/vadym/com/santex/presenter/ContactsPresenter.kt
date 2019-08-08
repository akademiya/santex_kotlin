package adv.vadym.com.santex.presenter

import adv.vadym.com.santex.AndroidApplication
import adv.vadym.com.santex.R
import adv.vadym.com.santex.mvp.BasePresenter
import adv.vadym.com.santex.view.IContactsActivity
import adv.vadym.com.santex.view.impl.ContactsActivity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast

class ContactsPresenter(view: ContactsActivity, applicationComponent: Application) : BasePresenter<ContactsActivity>(view) {

    init {
        (applicationComponent as AndroidApplication).applicationComponent.inject(this)
    }

    private var name = ""
    private var phone = ""
    private var message = ""

    override fun onBindView() {}
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
        if (sendIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(Intent.createChooser(sendIntent, "Santex"))
        } else {
            Toast.makeText(context, "На устройстве нет ни одного приложения, чтобы отправить сообщение на email", Toast.LENGTH_SHORT).show()
        }
    }

    fun onSendMessageButtonClick() {
        view?.dialogCreateMessage()
    }

    fun onNameInput(name: String) {
        this.name = name
    }

    fun onPhoneInput(phone: String) {
        this.phone = phone
    }

    fun onMessageInput(message: String) {
        this.message = message
    }

    fun onDialogSendButtonClick(context: Context) {
        val email = context.resources.getString(R.string.email)
        val sendIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, name)
        sendIntent.putExtra(Intent.EXTRA_TEXT, "$phone\n$message")
        if (sendIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(Intent.createChooser(sendIntent, "Santex"))
        } else {
            Toast.makeText(context, "На устройстве нет ни одного приложения, чтобы отправить сообщение на email", Toast.LENGTH_SHORT).show()
        }

    }

}