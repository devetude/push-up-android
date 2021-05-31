package net.devetude.pushup.view.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

class CameraPermissionChecker(private val context: Context) {
    fun isGranted(): Boolean =
        ContextCompat.checkSelfPermission(context, PERMISSION) == PackageManager.PERMISSION_GRANTED

    companion object {
        private const val PERMISSION = "android.permission.CAMERA"
    }
}
