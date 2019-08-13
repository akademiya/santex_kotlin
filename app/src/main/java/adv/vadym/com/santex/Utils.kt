package adv.vadym.com.santex

import android.Manifest.permission.CALL_PHONE
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.design.widget.TextInputEditText
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.requestPermissions
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import java.text.DecimalFormat
import java.text.MessageFormat

fun Context.makePhoneCall(activity: Activity, number: String) : Boolean {
    return try {
        val intent = Intent(Intent.ACTION_CALL)
        intent.data = Uri.parse("tel:$number")
        if (ActivityCompat.checkSelfPermission(this, CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent)
        } else {
            Log.e("tel", "number $number has not permission")
            requestPermissions(activity, arrayOf(CALL_PHONE), 1)
        }
        true
    } catch (e: Exception) {
        Log.e("tel", "number $number Exception")
        e.printStackTrace()
        false
    }
}

fun TextInputEditText.simpleTextWatcher(watcher: (String) -> Unit) : TextWatcher =
    object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            watcher(s.toString())
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }.also { addTextChangedListener(it) }

fun EditText.simpleTextWatcher(watcher: (String) -> Unit) : TextWatcher =
    object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            watcher(s.toString())
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }.also { addTextChangedListener(it) }

fun EditText.phoneFormatTextWatcher(watcher: (String) -> Unit) : TextWatcher =
    object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            if (s.isNullOrEmpty()) {return}
            watcher(s.toString().formatPhoneNumber(s.toString().toLong()))
        }
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }.also { addTextChangedListener(it) }


fun String.formatPhoneNumber(ph: Long) : String {
    val decimalFormat = DecimalFormat("0000000000")
    val rawString = decimalFormat.format(ph)
    val phoneMsgFormat = MessageFormat("({0}) {1}-{2}")
    val phoneNumArr = arrayOf(rawString.substring(0, 3), rawString.substring(3, 6), rawString.substring(6))
    return phoneMsgFormat.format(phoneNumArr)
}