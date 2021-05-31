package net.devetude.pushup.view.permission

import android.content.Context
import android.content.pm.PackageManager
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness
import org.robolectric.RobolectricTestRunner
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@RunWith(RobolectricTestRunner::class)
class CameraPermissionCheckerTest {
    @get:Rule
    val rule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var context: Context

    private lateinit var checker: CameraPermissionChecker

    @Before
    fun setUp() {
        checker = CameraPermissionChecker(context)
    }

    @Test
    fun `Test isGranted if granted`() {
        whenever(context.checkPermission(anyString(), anyInt(), anyInt()))
            .doReturn(PackageManager.PERMISSION_GRANTED)

        val isGranted = checker.isGranted()

        assertTrue { isGranted }
        verify(context).checkPermission(eq(value = "android.permission.CAMERA"), anyInt(), anyInt())
    }

    @Test
    fun `Test isGranted if denied`() {
        whenever(context.checkPermission(anyString(), anyInt(), anyInt()))
            .doReturn(PackageManager.PERMISSION_DENIED)

        val isGranted = checker.isGranted()

        assertFalse { isGranted }
        verify(context).checkPermission(eq(value = "android.permission.CAMERA"), anyInt(), anyInt())
    }
}
