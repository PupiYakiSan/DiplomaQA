package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Authorization {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void startPage() {
        pageObject.loginOut();
    }

    private static final String toastMessage =
            "Something went wrong. Try again later.";
    private static final String toastMessageEmpty =
            "Login and password cannot be empty";
    PageObject pageObject = new PageObject();

    @Test
    public void invalidLoginInvalidPasswordOption1() {

        pageObject.authorization("login3", "password3");
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void invalidLoginInvalidPasswordOption2() {

        pageObject.authorization("Any", "qwerty");
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void invalidLoginValidPassword() {

        pageObject.authorization("Any", "password2");
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void validLoginInvalidPassword() {

        pageObject.authorization("login2", "qwerty");
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void emptyLoginEmptyPassword() {

        pageObject.authorization("", "");
        onView(withText(toastMessageEmpty)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void validLoginValidPassword() {

        pageObject.authorization("login2", "password2");
        onView(withText("News")).check(matches(isDisplayed()));
    }
}
