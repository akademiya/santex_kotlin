package adv.vadym.com.santex.view.impl

import adv.vadym.com.santex.R
import adv.vadym.com.santex.makePhoneCall
import adv.vadym.com.santex.mvp.BaseActivity
import adv.vadym.com.santex.presenter.ContactsPresenter
import adv.vadym.com.santex.simpleTextWatcher
import adv.vadym.com.santex.view.IContactsActivity
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import kotlinx.android.synthetic.main.contacts_activity.*

class ContactsActivity : BaseActivity(), IContactsActivity {

    private lateinit var presenter: ContactsPresenter

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

    override fun onPhoneNumberClick(number: IContactsActivity.TelProvider) {
        when(number) {
            IContactsActivity.TelProvider.LIFE -> applicationContext.makePhoneCall(this, "0637712972")
            IContactsActivity.TelProvider.KVS ->  applicationContext.makePhoneCall(this, "0985128713")
            IContactsActivity.TelProvider.MTS -> applicationContext.makePhoneCall(this, resources.getString(R.string.tel_mts))
            IContactsActivity.TelProvider.CITY -> applicationContext.makePhoneCall(this, resources.getString(R.string.tel_city))
        }
    }

    override fun dialogCreateMessage() {
        val subView = LayoutInflater.from(this).inflate(R.layout.send_message_dialog, null)
        val name = subView.findViewById<EditText>(R.id.et_name)
        name.simpleTextWatcher { presenter.onNameInput(it) }

        val phone = subView.findViewById<EditText>(R.id.et_phone)
        phone.simpleTextWatcher { presenter.onPhoneInput(it) }

        val message = subView.findViewById<EditText>(R.id.et_message)
        message.simpleTextWatcher { presenter.onMessageInput(it) }


        AlertDialog.Builder(this).apply {
            setView(subView)
            create()
            setTitle(R.string.dialog_title)
            setPositiveButton(R.string.send) { _, _ ->
                presenter.onDialogSendButtonClick(this@ContactsActivity)
            }
            setNegativeButton(R.string.cancel) {_, _ -> }
            show().apply {
                getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.negative_button_color))
            }
        }
    }
}