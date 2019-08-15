package adv.vadym.com.santex.view.impl

import adv.vadym.com.santex.R
import adv.vadym.com.santex.mvp.BaseActivity
import adv.vadym.com.santex.phoneFormatTextWatcher
import adv.vadym.com.santex.presenter.MainPresenter
import adv.vadym.com.santex.simpleTextWatcher
import adv.vadym.com.santex.view.IMainActivity
import android.app.AlertDialog
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), IMainActivity {

    private lateinit var presenter: MainPresenter
    private lateinit var tilTime: TextInputLayout
    private lateinit var tilName: TextInputLayout
    private lateinit var tilPhone: TextInputLayout

    override fun init(savedInstanceState: Bundle?) {
        super.setContentView(R.layout.activity_main)
        presenter = MainPresenter(this, application)

        call_santehnic_button.setOnClickListener {
            presenter.onCallToSantehnicButtonClick()
        }

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        presenter.bindView(this)
    }

    override fun onDetachedFromWindow() {
        presenter.unbindView(this)
        super.onDetachedFromWindow()
    }

    override fun dialogCreateMessageOrder() {
        val subView = LayoutInflater.from(this).inflate(R.layout.call_master_dialog, null)
        tilName = subView.findViewById(R.id.til_name)
        tilPhone = subView.findViewById(R.id.til_phone)
        tilTime = subView.findViewById(R.id.til_time)


        val name = subView.findViewById<EditText>(R.id.et_name)
        name.simpleTextWatcher {
            presenter.onNameInput(it)
        }
        name.setOnFocusChangeListener { _, hasFocus ->
            presenter.onValidateNameByFocus(hasFocus)
        }

        val phone = subView.findViewById<EditText>(R.id.et_phone)
        phone.phoneFormatTextWatcher {
            presenter.onPhoneInput(it)
        }
        phone.setOnFocusChangeListener { _, hasFocus ->
            presenter.onValidatePhoneByFocus(hasFocus)
        }

        val time = subView.findViewById<EditText>(R.id.et_time)
        time.simpleTextWatcher {
            presenter.onTimeInput(it)
        }
        time.setOnFocusChangeListener { _, hasFocus ->
            presenter.onValidateTimeByFocus(hasFocus)
        }

        AlertDialog.Builder(this).apply {
            setView(subView)
            create()
            setTitle(resources.getString(R.string.call_master))
            setPositiveButton(R.string.send) {_, _ ->
                presenter.validateCorrectData(this@MainActivity)
            }
            setNegativeButton(R.string.cancel) {_, _ ->}
        }.show().apply {
            getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(resources.getColor(R.color.negative_button_color))
        }
    }

    override fun showErrorByLostFocus(errorField: IMainActivity.InvalidValue) {
        when(errorField) {
            IMainActivity.InvalidValue.NO_NAME -> {
                tilName.error = resources.getString(R.string.no_name)
            }
            IMainActivity.InvalidValue.NO_PHONE -> {
                tilPhone.error = resources.getString(R.string.no_phone)
            }
            IMainActivity.InvalidValue.NO_TIME -> {
                tilTime.error = resources.getString(R.string.no_time)
            }
        }
    }

    override fun showInvalidValue(errorField: IMainActivity.InvalidValue) {
        when(errorField) {
            IMainActivity.InvalidValue.NO_NAME -> {
                Toast.makeText(this, resources.getString(R.string.no_name), Toast.LENGTH_LONG).show()
            }
            IMainActivity.InvalidValue.NO_PHONE -> {
                Toast.makeText(this, resources.getString(R.string.no_phone), Toast.LENGTH_LONG).show()
            }
            IMainActivity.InvalidValue.INVALID_PHONE -> {
                Toast.makeText(this, resources.getString(R.string.invalid_phone), Toast.LENGTH_LONG).show()
            }
            IMainActivity.InvalidValue.NO_TIME -> {
                Toast.makeText(this, resources.getString(R.string.no_time), Toast.LENGTH_LONG).show()
            }
            IMainActivity.InvalidValue.INVALID_TIME -> {
                Toast.makeText(this, resources.getString(R.string.invalid_time), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResetNameError() {
        tilName.error = ""
    }

    override fun onResetPhoneError() {
        tilPhone.error = ""
    }

    override fun onResetTimeError() {
        tilTime.error = ""
    }
}