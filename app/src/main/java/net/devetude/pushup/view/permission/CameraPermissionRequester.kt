package net.devetude.pushup.view.permission

import android.app.Activity
import androidx.core.app.ActivityCompat

class CameraPermissionRequester(private val activity: Activity) {
    fun request() = ActivityCompat.requestPermissions(activity, arrayOf(PERMISSION), REQUEST_CODE)

    companion object {
        const val REQUEST_CODE = 1_000
        private const val PERMISSION = "android.permission.CAMERA"
    }
}
