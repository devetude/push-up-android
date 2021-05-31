package net.devetude.pushup.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import net.devetude.pushup.databinding.ActivityMainBinding
import net.devetude.pushup.view.permission.CameraPermissionChecker
import net.devetude.pushup.view.permission.CameraPermissionRequester
import net.devetude.pushup.view.starter.MainActivityStarter

class MainActivity : AppCompatActivity() {
    private val cameraPermissionChecker: CameraPermissionChecker by lazy {
        CameraPermissionChecker(context = this)
    }
    private val cameraPermissionRequester: CameraPermissionRequester by lazy {
        CameraPermissionRequester(activity = this)
    }
    private val activityStarter: MainActivityStarter by lazy { MainActivityStarter(context = this) }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        if (cameraPermissionChecker.isGranted()) {
            binding.cameraPermissionGroup.isVisible = false
            initCamera()
        } else {
            cameraPermissionRequester.request()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode != CameraPermissionRequester.REQUEST_CODE) return
        if (cameraPermissionChecker.isGranted()) {
            binding.cameraPermissionGroup.isVisible = false
            initCamera()
        }
    }

    private fun initBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun initViews() {
        binding.cameraPermissionSettingButton.setOnClickListener {
            activityStarter.startApplicationDetailSettings()
        }
    }

    private fun initCamera() {
        // TODO: Implement here.
    }
}
