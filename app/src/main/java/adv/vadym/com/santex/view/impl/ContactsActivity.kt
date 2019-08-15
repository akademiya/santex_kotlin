package adv.vadym.com.santex.view.impl

import adv.vadym.com.santex.R
import adv.vadym.com.santex.makePhoneCall
import adv.vadym.com.santex.mvp.BaseActivity
import adv.vadym.com.santex.phoneFormatTextWatcher
import adv.vadym.com.santex.presenter.ContactsPresenter
import adv.vadym.com.santex.simpleTextWatcher
import adv.vadym.com.santex.view.IContactsActivity
import android.app.AlertDialog
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.contacts_activity.*

class ContactsActivity : BaseActivity(), IContactsActivity {

    private lateinit var presenter: ContactsPresenter
    private lateinit var tilName: TextInputLayout
    private lateinit var tilPhone: TextInputLayout
    private lateinit var tilMessage: TextInputLayout

    override fun init(savedInstanceState: Bundle?) {
        setContentView(R.layout.contacts_activity)
        presenter = ContactsPresenter(this, application)

        send_message_button.setOnClickListener {
            presenter.onSendMessageButtonClick()
        }

        tv_life.setOnClickListener { presenter.onNumberTelClick(IContactsActivity.TelProvider.LIFE) }
        tv_kvs.setOnClickListener { presenter.onNumberTelClick(IContactsActivity.TelProvider.KVS) }
        tv_mts.setOnClickListener { presenter.onNumberTelClick(IContactsActivity.TelProvider.MTS) }
        tv_city.setOnClickListener { presenter.onNumberTelClick(IContactsActivity.TelProvider.CITY) }
        tv_email.setOnClickListener { presenter.onEmailTextСlick(this) }

    }

    override fun onCallByNumber(number: IContactsActivity.TelProvider) {
        when(number) {
            IContactsActivity.TelProvider.LIFE -> applicationContext.makePhoneCall(this, "0637712972")
            IContactsActivity.TelProvider.KVS ->  applicationContext.makePhoneCall(this, "0985128713")
            IContactsActivity.TelProvider.MTS -> applicationContext.makePhoneCall(this, resources.getString(R.string.tel_mts))
            IContactsActivity.TelProvider.CITY -> applicationContext.makePhoneCall(this, resources.getString(R.string.tel_city))
        }
    }

    override fun dialogCreateMessage() {
        val subView = LayoutInflater.from(this).inflate(R.layout.send_message_dialog, null)
        tilName = subView.findViewById(R.id.til_name)
        tilPhone = subView.findViewById(R.id.til_phone)
        tilMessage = subView.findViewById(R.id.til_message)

        val name = subView.findViewById<EditText>(R.id.et_name)
        name.simpleTextWatcher { presenter.onNameInput(it) }
        name.setOnFocusChangeListener { _, hasFocus ->
            presenter.onValidateNameByFocus(hasFocus)
        }

        val phone = subView.findViewById<EditText>(R.id.et_phone)
        phone.phoneFormatTextWatcher { presenter.onPhoneInput(it) }
        phone.setOnFocusChangeListener { _, hasFocus ->
            presenter.onValidatePhoneByFocus(hasFocus)
        }

        val message = subView.findViewById<EditText>(R.id.et_message)
        message.simpleTextWatcher { presenter.onMessageInput(it) }
        message.setOnFocusChangeListener { _, hasFocus ->
            presenter.onValidateMessageByFocus(hasFocus)
        }


        AlertDialog.Builder(this).apply {
            setView(subView)
            create()
            setTitle(R.string.dialog_title)
            setPositiveButton(R.string.send) { _, _ ->
                presenter.validateCorrectData(this@ContactsActivity)
            }
            setNegativeButton(R.string.cancel) {_, _ -> }
            show().apply {
                getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.negative_button_color))
            }
        }
    }

    override fun showErrorByLostFocus(errorField: IContactsActivity.InvalidValue) {
        when(errorField) {
            IContactsActivity.InvalidValue.NO_NAME -> {
                tilName.error = resources.getString(R.string.no_name)
            }
            IContactsActivity.InvalidValue.NO_PHONE -> {
                tilPhone.error = resources.getString(R.string.no_phone)
            }
            IContactsActivity.InvalidValue.NO_MESSAGE -> {
                tilMessage.error = resources.getString(R.string.no_message)
            }
        }
    }

    override fun showInvalidValue(errorField: IContactsActivity.InvalidValue) {
        when(errorField) {
            IContactsActivity.InvalidValue.NO_NAME -> {
                Toast.makeText(this, resources.getString(R.string.no_name), Toast.LENGTH_LONG).show()
            }
            IContactsActivity.InvalidValue.NO_PHONE -> {
                Toast.makeText(this, resources.getString(R.string.no_phone), Toast.LENGTH_LONG).show()
            }
            IContactsActivity.InvalidValue.INVALID_PHONE -> {
                Toast.makeText(this, resources.getString(R.string.invalid_phone), Toast.LENGTH_LONG).show()
            }
            IContactsActivity.InvalidValue.NO_MESSAGE -> {
                Toast.makeText(this, resources.getString(R.string.no_message), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResetNameError() {
        tilName.error = ""
    }

    override fun onResetPhoneError() {
        tilPhone.error = ""
    }

    override fun onResetMessageError() {
        tilMessage.error = ""
    }
}