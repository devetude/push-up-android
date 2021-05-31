package net.devetude.pushup.view.permission

import android.app.Activity
import androidx.core.app.ActivityCompat
import com.nhaarman.mockitokotlin2.argThat
import com.nhaarman.mockitokotlin2.eq
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockedStatic
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class CameraPermissionRequesterTest {
    @get:Rule
    val rule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var activity: Activity

    private lateinit var mockedActivityCompat: MockedStatic<ActivityCompat>

    private lateinit var requester: CameraPermissionRequester

    @Before
    fun setUp() {
        requester = CameraPermissionRequester(activity)
        mockedActivityCompat = Mockito.mockStatic(ActivityCompat::class.java)
    }

    @After
    fun tearDown() = mockedActivityCompat.close()

    @Test
    fun `Test request`() {
        requester.request()

        mockedActivityCompat.verify {
            ActivityCompat.requestPermissions(
                eq(activity),
                argThat { size == 1 && first() == "android.permission.CAMERA" },
                eq(value = 1_000)
            )
        }
    }
}
