package adv.vadym.com.santex.presenter

import adv.vadym.com.santex.AndroidApplication
import adv.vadym.com.santex.mvp.BasePresenter
import adv.vadym.com.santex.view.IMainActivity
import adv.vadym.com.santex.view.impl.MainActivity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import java.util.regex.Pattern


class MainPresenter(mainView: MainActivity, applicationComponent: Application) : BasePresenter<MainActivity>(mainView) {

    init {
        (applicationComponent as AndroidApplication).applicationComponent.inject(this)
    }

    private var name = ""
    private var phone = ""
    private var time = ""
    private var isValidateSuccess = true

    override fun onBindView() {}

    override fun onUnbindView() {}

    fun onReserveCalendarDateClick(date: String) {
        view?.onReservedDate(date)
    }

    fun onNameInput(name: String) {
        this.name = name
    }

    fun onPhoneInput(phone: String) {
        this.phone = phone
    }

    fun onTimeInput(time: String) {
        this.time = time
    }

    fun onValidateNameByFocus(hasFocus: Boolean) {
        isValidateSuccess = if (!hasFocus && name.isBlank()) {
            view?.showErrorByLostFocus(IMainActivity.InvalidValue.NO_NAME)
            false
        } else {
            view?.onResetNameError()
            true
        }
    }

    fun onValidatePhoneByFocus(hasFocus: Boolean) {
        isValidateSuccess = if (!hasFocus && phone.isBlank() || phone == "(000) 000-0000") {
            view?.showErrorByLostFocus(IMainActivity.InvalidValue.NO_PHONE)
            false
        } else {
            view?.onResetPhoneError()
            true
        }
    }

    fun onValidateTimeByFocus(hasFocus: Boolean) {
        isValidateSuccess = if (!hasFocus && time.isBlank()) {
            view?.showErrorByLostFocus(IMainActivity.InvalidValue.NO_TIME)
            false
        } else {
            view?.onResetTimeError()
            true
        }
    }

    fun validateCorrectData(context: Context) {
        if (name.isEmpty()) {
            view?.showInvalidValue(IMainActivity.InvalidValue.NO_NAME)
            isValidateSuccess = false
            return
        }
        if (phone.isEmpty()) {
            view?.showInvalidValue(IMainActivity.InvalidValue.NO_PHONE)
            isValidateSuccess = false
            return
        } else {
            if (!validatePhone(phone) || phone.startsWith("(000)")) {
                view?.showInvalidValue(IMainActivity.InvalidValue.INVALID_PHONE)
                isValidateSuccess = false
                return
            }
        }
        if (time.isEmpty()) {
            view?.showInvalidValue(IMainActivity.InvalidValue.NO_TIME)
            isValidateSuccess = false
            return
        } else {
            if (!validateTime(time)) {
                view?.showInvalidValue(IMainActivity.InvalidValue.INVALID_TIME)
                isValidateSuccess = false
                return
            }
        }
        if (isValidateSuccess) {
            onDialogSendButtonClick(context)
        }
    }

    fun onCallToSantehnicButtonClick() {
        onResetData()
        isValidateSuccess = true
        view?.dialogCreateMessageOrder()
    }

    private fun validatePhone(phone: String) : Boolean {
        val pattern = Pattern.compile("\\(\\d{3}\\) \\d{3}-\\d{4}")
        return pattern.matcher(phone).matches()
    }

    private fun validateTime(time: String) : Boolean {
        val pattern = Pattern.compile("^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]\$")
        return pattern.matcher(time).matches()
    }

    private fun onResetData() {
        this.name = ""
        this.phone = ""
        this.time = ""
    }

    private fun onDialogSendButtonClick(context: Context) {
        val email = context.resources.getString(adv.vadym.com.santex.R.string.email)
        val sendIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, name)
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Мой номер: $phone\nПерезвоните мне в: $time")
        if (sendIntent.resolveActivity(context.packageManager) != null) {
            onResetData()
            context.startActivity(Intent.createChooser(sendIntent, "Santex"))
        } else {
            Toast.makeText(context, "На устройстве нет ни одного приложения, чтобы отправить сообщение на email", Toast.LENGTH_LONG).show()
        }
    }
}