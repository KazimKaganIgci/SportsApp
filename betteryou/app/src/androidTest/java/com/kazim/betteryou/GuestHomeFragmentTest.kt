package com.kazim.betteryou

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4
import com.kazim.betteryou.R
import com.kazim.betteryou.GuestFragments.GuestHomeFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GuestHomeFragmentTest {

    @Test
    fun testTextViewContent() {
        // GuestHomeFragment'i başlat
        val scenario = launchFragmentInContainer<GuestHomeFragment>()

        // TextView'in içeriğini kontrol et
        Espresso.onView(ViewMatchers.withText("Uygulamamıza hoşgeldiniz"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testButtonVisibility() {
        // GuestHomeFragment'i başlat
        val scenario = launchFragmentInContainer<GuestHomeFragment>()

        // Button'un görünür olduğunu kontrol et
        Espresso.onView(ViewMatchers.withId(R.id.btn))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}
