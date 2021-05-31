package net.devetude.pushup.view.starter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings

class MainActivityStarter(private val context: Context) {
    fun startApplicationDetailSettings() {
        val uri = Uri.fromParts(
            "package" /* scheme */,
            context.packageName,
            null /* fragment */
        )
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).setData(uri)
        context.startActivity(intent)
    }
}
