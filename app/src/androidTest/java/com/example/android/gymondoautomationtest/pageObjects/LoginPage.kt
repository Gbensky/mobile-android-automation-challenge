package com.example.android.gymondoautomationtest.pageObjects

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.android.gymondoautomationtest.R
import org.hamcrest.Matcher

class LoginPage {
    val emailAddressField: Matcher<View> = ViewMatchers.withId(R.id.editText)
    val passwordField: Matcher<View> = ViewMatchers.withId(R.id.editText2)
    val loginButton: Matcher<View> = ViewMatchers.withId(R.id.button)
    val toastText: Matcher<View> = ViewMatchers.withText("Username and/or password incorrect")

    fun enterEmailAddress(login: String): LoginPage {
        onView(emailAddressField).perform(ViewActions.typeText(login))
        return this
    }

    fun enterPassword(password: String): LoginPage {
        onView(passwordField).perform(ViewActions.typeText(password))
        return this
    }

    fun clickLoginButton(): LoginPage {
        onView(loginButton).perform(ViewActions.click())
        return this
    }

    fun closeSoftKeyboard(): LoginPage {
        Espresso.closeSoftKeyboard()
        return this
    }
}