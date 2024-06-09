package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static java.lang.Thread.sleep;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class News {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void startPage() {
        pageObject.loginIn();
    }

    PageObject pageObject = new PageObject();

    private static final String toastMessage =
            "Fill empty fields";
    private static final String toastMessageCategory =
            "Incorrect category";

    @Test
    public void createNewsPositive() {

        pageObject.createNewsPositive();
        onView(withText("Control panel")).check(matches(withText("Control panel")));
    }

    @Test
    public void createNewsEmptyCategory() {

        pageObject.createNewsNegative("Category");
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void createNewsEmptyTitle() {

        pageObject.createNewsNegative("Title");
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void createNewsEmptyPublicationDate() {

        pageObject.createNewsNegative("Publication date");
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void createNewsEmptyTime() {

        pageObject.createNewsNegative("Time");
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void createNewsEmptyDescription() {

        pageObject.createNewsNegative("Description");
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void createEmptyNewsAll() {

        pageObject.formCreating();
        onView(withId(R.id.save_button)).perform(click());
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void createNewsAnyCategory() {

        pageObject.createNewsAnyCategory("test");
        onView(withText(toastMessageCategory)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void canceledCreateNews() {

        pageObject.formCreating();

        onView(withHint("Category")).perform(typeText("test"), closeSoftKeyboard());
        onView(withHint("Title")).perform(typeText("test"), closeSoftKeyboard());

        onView(withId(R.id.cancel_button)).perform(scrollTo(), click());
        onView(withId(android.R.id.button1)).perform(scrollTo(), click());

        onView(withText("Control panel")).check(matches(withText("Control panel")));
    }

}
