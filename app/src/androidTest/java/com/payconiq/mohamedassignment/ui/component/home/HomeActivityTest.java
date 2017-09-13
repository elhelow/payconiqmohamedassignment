package com.payconiq.mohamedassignment.ui.component.home;


import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.payconiq.mohamedassignment.R;
import com.payconiq.mohamedassignment.ui.component.repos.HomeActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {
    private final String testSearchString = "the";

    @Rule
    public ActivityTestRule<HomeActivity> mActivityTestRule = new ActivityTestRule<>(HomeActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void setup() {
        mIdlingResource = mActivityTestRule.getActivity().getCountingIdlingResource();
        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void testScroll() {
        onView(withId(R.id.rv_repo_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.tv_title)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()));
    }

    @Test
    public void testRefresh() {
        //Before refresh there is a list .
        onView(withId(R.id.rv_repo_list)).check(matches(isDisplayed()));
        onView(withId(R.id.pb_loading)).check(matches((not(isDisplayed()))));
        // do refresh .
        onView(withId(R.id.ic_toolbar_refresh)).perform(click());
        //after refresh there is a list.
        onView(withId(R.id.rv_repo_list)).check(matches(isDisplayed()));
        onView(withId(R.id.pb_loading)).check(matches((not(isDisplayed()))));
    }

    @Test
    public void displayrepoData() {
        onView(withId(R.id.rv_repo_list)).check(matches(isDisplayed()));
        onView(withId(R.id.pb_loading)).check(matches((not(isDisplayed()))));
    }


    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }


}
