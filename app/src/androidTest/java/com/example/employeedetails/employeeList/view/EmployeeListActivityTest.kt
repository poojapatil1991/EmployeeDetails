package com.example.employeedetails.employeeList.view

import androidx.appcompat.app.ActionBar
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.example.employeedetails.R
import kotlinx.android.synthetic.main.content_employee_list.*
import org.junit.*
import org.junit.Assert.*

class EmployeeListActivityTest{
    @get:Rule
    var mActivityRule : ActivityTestRule<EmployeeListActivity> = ActivityTestRule<EmployeeListActivity>(EmployeeListActivity::class.java)
    lateinit var mEmployeeListActivity: EmployeeListActivity
    @Before
    fun setUp() {
        mEmployeeListActivity = mActivityRule.activity
    }

    @After
    fun tearDown() {
    }

    @Test
    fun onCreate() {
        var rvEmployeeList = mEmployeeListActivity.rv_employee_list
        assertNotNull(rvEmployeeList)
    }

    @Test
    fun testSwipeRefresh(){
        Espresso.onView(withId(R.id.swipe_refresh)).perform(ViewActions.swipeDown())
    }

    @Test
    fun testRecyclerViewScrollUp(){
        Espresso.onView(withId(R.id.rv_employee_list)).perform(ViewActions.swipeUp())
    }

    @Test
    fun testRecyclerViewScrollDown(){
        Espresso.onView(withId(R.id.rv_employee_list)).perform(ViewActions.swipeDown())
    }

    @Test
    fun testRecyclerViewScrolling(){
        Espresso.onView(withId(R.id.rv_employee_list)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}