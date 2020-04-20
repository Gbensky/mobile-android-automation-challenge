package com.example.android.gymondoautomationtest.tests

import android.content.Intent
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.gymondoautomationtest.ListActivity
import com.example.android.gymondoautomationtest.MainActivity
import com.example.android.gymondoautomationtest.pageObjects.LoginPage
import com.github.javafaker.Faker
import org.hamcrest.CoreMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginTest {
    @get:Rule
    val activityRule = IntentsTestRule(MainActivity::class.java, false, false)

    private val loginPage = LoginPage()
    private val faker = Faker()

    private val validEmailAddress = "automation@gymondo.de"
    private val validPassword = "automation"
    private val invalidEmailAddress = faker.internet().emailAddress()
    private val invalidPassword = faker.internet().password()


    @Before
    fun setUp(){
        activityRule.launchActivity(Intent())

        onView(loginPage.emailAddressField)
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(loginPage.passwordField)
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        onView(loginPage.loginButton)
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkLoginWithBlankEntriesInTheFields() {
        loginPage
            .enterEmailAddress("")
            .enterPassword("")
            .closeSoftKeyboard()
            .clickLoginButton()

        onView(loginPage.toastText)
            .inRoot(RootMatchers.withDecorView(CoreMatchers.not(CoreMatchers.`is`(activityRule.activity.window.decorView))))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkLoginWithInvalidEmailAddress() {
        loginPage
            .enterEmailAddress(invalidEmailAddress)
            .enterPassword(validPassword)
            .closeSoftKeyboard()
            .clickLoginButton()

        onView(loginPage.toastText)
            .inRoot(RootMatchers.withDecorView(CoreMatchers.not(CoreMatchers.`is`(activityRule.activity.window.decorView))))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkLoginWithInvalidPassword() {
        loginPage
            .enterEmailAddress(validEmailAddress)
            .enterPassword(invalidPassword)
            .closeSoftKeyboard()
            .clickLoginButton()

        onView(loginPage.toastText)
            .inRoot(RootMatchers.withDecorView(CoreMatchers.not(CoreMatchers.`is`(activityRule.activity.window.decorView))))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkLoginWithInvalidEmailAddressAndPassword() {
        loginPage
            .enterEmailAddress(invalidEmailAddress)
            .enterPassword(invalidPassword)
            .closeSoftKeyboard()
            .clickLoginButton()

        onView(loginPage.toastText)
            .inRoot(RootMatchers.withDecorView(CoreMatchers.not(CoreMatchers.`is`(activityRule.activity.window.decorView))))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkLoginWithValidEmailAddressAndPassword() {
        loginPage
            .enterEmailAddress(validEmailAddress)
            .enterPassword(validPassword)
            .closeSoftKeyboard()
            .clickLoginButton()

        intended(hasComponent(ListActivity::class.java.name))
    }
}