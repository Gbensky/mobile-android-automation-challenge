package com.example.android.gymondoautomationtest.tests


import android.content.Intent
import androidx.core.view.children
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.android.gymondoautomationtest.ListActivity
import com.example.android.gymondoautomationtest.pageObjects.ExerciseListPage
import com.github.javafaker.Faker
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.layout_item.view.*
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ExerciseListTest {
    @get:Rule
    val activityRule = IntentsTestRule(ListActivity::class.java, false, false)
    private val exerciseListPage = ExerciseListPage()
    private val faker = Faker()

    private var childCountDefault = 0;
    private val fullExerciseSearchText = "436 - 10 Min Abs"
    private val partExerciseSearchText = faker.number().digits(2)
    private val nonExistingExerciseSearchText = faker.lorem().characters(5, 8)


    @Before
    fun setUp(){
        activityRule.launchActivity(Intent())

        onView(exerciseListPage.searchExerciseInputField).check(matches(isDisplayed()))
        onView(exerciseListPage.searchButton).check(matches(isDisplayed()))
        onView(exerciseListPage.clearSearchTextButton).check(matches(isDisplayed()))
        onView(exerciseListPage.exerciseListView).check(matches(isDisplayed()))
        exerciseListPage.closeSoftKeyboard()

        childCountDefault = activityRule.activity.recycler_view.childCount
    }

    @Test
    fun searchWithNoExerciseTextValue() {
        exerciseListPage
            .clickClearTextButton()
            .fillSearchField("")
            .clickSearchButton()

        onView(exerciseListPage.exerciseListView)
            .check(matches(hasChildCount(childCountDefault)))
    }

    @Test
    fun searchForAvailableExerciseOnListWithFullName() {
        exerciseListPage
            .clickClearTextButton()
            .fillSearchField(fullExerciseSearchText)
            .clickSearchButton()

        onView(exerciseListPage.exerciseListView)
            .check(matches(hasDescendant(withText(fullExerciseSearchText))))
    }

    @Test
    fun searchForAvailableExerciseOnListWithSubName() {
        exerciseListPage
            .clickClearTextButton()
            .fillSearchField(partExerciseSearchText)
            .clickSearchButton()

        val childCount = activityRule.activity.recycler_view.childCount

        // Checks if the expected search result was returned
        for (i in 0 until childCount) {
            val cell = activityRule.activity.recycler_view.children.toList()[i]
            val cellText = cell.item_text.text.toString()
            assertTrue(cellText.contains(partExerciseSearchText));
        }

    }

    @Test
    fun searchForNonExistingExerciseOnList() {
        exerciseListPage
            .clickClearTextButton()
            .fillSearchField(nonExistingExerciseSearchText)
            .clickSearchButton()

        onView(exerciseListPage.exerciseListView)
            .check(matches(hasChildCount(0)))
    }
}