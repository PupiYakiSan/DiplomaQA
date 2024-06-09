package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNot.not;

import static java.lang.Thread.sleep;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class Page {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void startPage() {
        pageObject.loginIn();
    }

    PageObject pageObject = new PageObject();

    @Test
    public void pageQuoteTopic() {

        onView(withId(R.id.our_mission_image_button)).perform(click());
        onView(withId(R.id.our_mission_title_text_view)).check(matches(withText("Love is all")));
    }

    @Test
    public void pageAbout() {

        pageObject.menuPage("About");
        onView(withId(R.id.about_privacy_policy_label_text_view))
                .check(matches(withText("Privacy Policy:")));
    }

    @Test
    public void pageNews() {

        pageObject.menuPage("News");
        onView(withText("News"))
                .check(matches(isDisplayed()));
        onView(withId(R.id.all_news_text_view)).check(matches(not(isDisplayed())));
    }

    @Test
    public void pageNewsPageAbout() {

        pageObject.menuPage("News");
        pageObject.menuPage("About");
        onView(withId(R.id.about_privacy_policy_label_text_view))
                .check(matches(withText("Privacy Policy:")));
    }

    @Test
    public void pageNewsPageMain() {

        pageObject.menuPage("News");
        pageObject.menuPage("Main");
        onView(withText("News"))
                .check(matches(isDisplayed()));
        onView(withId(R.id.all_news_text_view)).check(matches(isDisplayed()));
    }

    @Test
    public void pageQuoteTopicPageMain() {

        onView(withId(R.id.our_mission_image_button)).perform(click());
        pageObject.menuPage("Main");
        onView(withText("News"))
                .check(matches(isDisplayed()));
        onView(withId(R.id.all_news_text_view)).check(matches(isDisplayed()));
    }

    @Test
    public void pageQuoteTopicPageNews() {

        onView(withId(R.id.our_mission_image_button)).perform(click());
        pageObject.menuPage("News");
        onView(withText("News"))
                .check(matches(isDisplayed()));
        onView(withId(R.id.all_news_text_view)).check(matches(not(isDisplayed())));
    }

    @Test
    public void pageQuoteTopicPageAbout() {

        onView(withId(R.id.our_mission_image_button)).perform(click());
        pageObject.menuPage("About");
        onView(withId(R.id.about_privacy_policy_label_text_view))
                .check(matches(withText("Privacy Policy:")));
    }

    @Test
    public void exitPageAbout() {

        pageObject.menuPage("About");
        onView(withId(R.id.about_back_image_button)).perform(click());
        onView(withText("News"))
                .check(matches(isDisplayed()));
        onView(withId(R.id.all_news_text_view)).check(matches(isDisplayed()));
    }

    @Test
    public void pageAboutPageNews() {

        pageObject.menuPage("About");
        onView(withId(R.id.about_back_image_button)).perform(click());
        pageObject.menuPage("News");
        onView(withText("News"))
                .check(matches(isDisplayed()));
        onView(withId(R.id.all_news_text_view)).check(matches(not(isDisplayed())));
    }
}
