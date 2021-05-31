package net.devetude.pushup.view.starter

import android.content.Context
import com.nhaarman.mockitokotlin2.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class MainActivityStarterTest {
    @get:Rule
    val rule: MockitoRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @Mock
    private lateinit var context: Context

    private lateinit var starter: MainActivityStarter

    @Before
    fun setUp() {
        starter = MainActivityStarter(context)
    }

    @Test
    fun `Test startApplicationDetailSettings`() {
        whenever(context.packageName).doReturn(t = "net.devetude.pushup")
        doNothing().whenever(context).startActivity(any())

        starter.startApplicationDetailSettings()

        verify(context).packageName
        verify(context).startActivity(
            argThat {
                action == "android.settings.APPLICATION_DETAILS_SETTINGS" &&
                        data?.toString() == "package:net.devetude.pushup"
            }
        )
    }
}
