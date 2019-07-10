package adv.vadym.com.santex

import android.Manifest.permission.CALL_PHONE
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityCompat.requestPermissions
import android.util.Log

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