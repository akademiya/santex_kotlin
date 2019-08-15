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
import java.util.regex.Pattern

class ContactsPresenter(view: ContactsActivity, applicationComponent: Application) : BasePresenter<ContactsActivity>(view) {

    init {
        (applicationComponent as AndroidApplication).applicationComponent.inject(this)
    }

    private var name = ""
    private var phone = ""
    private var message = ""
    private var isValidateSuccess = true

    override fun onBindView() {}
    override fun onUnbindView() {}

    fun onNumberTelClick(number: IContactsActivity.TelProvider) {
        when(number) {
            IContactsActivity.TelProvider.LIFE -> view?.onCallByNumber(number)
            IContactsActivity.TelProvider.KVS -> view?.onCallByNumber(number)
            IContactsActivity.TelProvider.MTS -> view?.onCallByNumber(number)
            IContactsActivity.TelProvider.CITY -> view?.onCallByNumber(number)
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
        onResetData()
        isValidateSuccess = true
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

    fun onValidateNameByFocus(hasFocus: Boolean) {
        isValidateSuccess = if (!hasFocus && name.isBlank()) {
            view?.showErrorByLostFocus(IContactsActivity.InvalidValue.NO_NAME)
            false
        } else {
            view?.onResetNameError()
            true
        }
    }

    fun onValidatePhoneByFocus(hasFocus: Boolean) {
        isValidateSuccess = if (!hasFocus && phone.isBlank() || phone == "(000) 000-0000") {
            view?.showErrorByLostFocus(IContactsActivity.InvalidValue.NO_PHONE)
            false
        } else {
            view?.onResetPhoneError()
            true
        }
    }

    fun onValidateMessageByFocus(hasFocus: Boolean) {
        isValidateSuccess = if (!hasFocus && message.isBlank()) {
            view?.showErrorByLostFocus(IContactsActivity.InvalidValue.NO_MESSAGE)
            false
        } else {
            view?.onResetMessageError()
            true
        }
    }

    private fun onResetData() {
        this.name = ""
        this.phone = ""
        this.message = ""
    }

    fun validateCorrectData(context: Context) {
        if (name.isEmpty()) {
            view?.showInvalidValue(IContactsActivity.InvalidValue.NO_NAME)
            isValidateSuccess = false
            return
        }
        if (phone.isEmpty()) {
            view?.showInvalidValue(IContactsActivity.InvalidValue.NO_PHONE)
            isValidateSuccess = false
            return
        } else {
            if (!validatePhone(phone) || phone.startsWith("(000)")) {
                view?.showInvalidValue(IContactsActivity.InvalidValue.INVALID_PHONE)
                isValidateSuccess = false
                return
            }
        }
        if (message.isEmpty()) {
            view?.showInvalidValue(IContactsActivity.InvalidValue.NO_MESSAGE)
            isValidateSuccess = false
            return
        }
        if (isValidateSuccess) {
            onDialogSendButtonClick(context)
        }
    }

    private fun validatePhone(phone: String) : Boolean {
        val pattern = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}")
        return pattern.matcher(phone).matches()
    }

    private fun onDialogSendButtonClick(context: Context) {
        val email = context.resources.getString(R.string.email)
        val sendIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, name)
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Мой номер: $phone\nМеня интересует: $message")
        if (sendIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(Intent.createChooser(sendIntent, "Santex"))
        } else {
            Toast.makeText(context, "На устройстве нет ни одного приложения, чтобы отправить сообщение на email", Toast.LENGTH_SHORT).show()
        }

    }

}