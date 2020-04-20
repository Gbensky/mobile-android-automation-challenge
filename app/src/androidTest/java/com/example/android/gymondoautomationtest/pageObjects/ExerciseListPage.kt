package com.example.android.gymondoautomationtest.pageObjects

import android.view.View
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.android.gymondoautomationtest.R
import org.hamcrest.Matcher

class ExerciseListPage {

    val searchExerciseInputField: Matcher<View> = ViewMatchers.withId(R.id.editTxtSearch)
    val searchButton: Matcher<View> = ViewMatchers.withId(R.id.btnSearch)
    val clearSearchTextButton: Matcher<View> = ViewMatchers.withId(R.id.btnClear)
    val exerciseListView: Matcher<View> = ViewMatchers.withId(R.id.recycler_view)

    fun fillSearchField(exercise: String): ExerciseListPage {
        Espresso.onView(searchExerciseInputField).perform(ViewActions.typeText(exercise))
        return this
    }

    fun clickSearchButton(): ExerciseListPage {
        Espresso.onView(searchButton).perform(ViewActions.click())
        return this
    }

    fun clickClearTextButton(): ExerciseListPage {
        Espresso.onView(clearSearchTextButton).perform(ViewActions.click())
        return this
    }

    fun closeSoftKeyboard(): ExerciseListPage {
        Espresso.closeSoftKeyboard()
        return this
    }
}