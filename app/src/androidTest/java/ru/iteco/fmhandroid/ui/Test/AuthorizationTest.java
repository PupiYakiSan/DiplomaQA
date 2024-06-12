package ru.iteco.fmhandroid.ui.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.PageObject.PageObjectAuthorization;
import ru.iteco.fmhandroid.ui.PageObject.PageObjectBefore;
import ru.iteco.fmhandroid.ui.Service.DataHelper;
import ru.iteco.fmhandroid.ui.Service.ToastMatcher;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void startPage() {
        pageObjectBefore.loginOut();
    }

    private static final String toastMessage =
            "Something went wrong. Try again later.";
    private static final String toastMessageEmpty =
            "Login and password cannot be empty";
    PageObjectBefore pageObjectBefore = new PageObjectBefore();
    PageObjectAuthorization pageObjectAuthorization = new PageObjectAuthorization();


    @Test
    public void invalidLoginInvalidPasswordOption1() {

        pageObjectAuthorization.authorization(DataHelper.getInvalidLogin(), DataHelper.getInvalidPassword());
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void invalidLoginValidPassword() {

        pageObjectAuthorization.authorization(DataHelper.getInvalidLogin(), DataHelper.getValidPassword());
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void validLoginInvalidPassword() {

        pageObjectAuthorization.authorization(DataHelper.getValidLogin(), DataHelper.getInvalidPassword());
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }


    @Test
    public void validLoginValidPassword() {

        pageObjectAuthorization.authorization(DataHelper.getValidLogin(), DataHelper.getValidPassword());
        onView(withText("News")).check(matches(isDisplayed()));
    }

    @Test
    public void emptyLoginEmptyPassword() {

        pageObjectAuthorization.authorization("", "");
        onView(withText(toastMessageEmpty)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void invalidLoginInvalidPasswordOption2() {

        pageObjectAuthorization.authorization("login3", "password3");
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

}
