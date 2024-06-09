package ru.iteco.fmhandroid.ui.Test;


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


import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.PageObject.PageObjectBefore;
import ru.iteco.fmhandroid.ui.PageObject.PageObjectNews;
import ru.iteco.fmhandroid.ui.Service.ToastMatcher;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class NewsTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void startPage() {
        pageObjectBefore.loginIn();
    }

    PageObjectBefore pageObjectBefore = new PageObjectBefore();
    PageObjectNews pageObjectNews = new PageObjectNews();

    private static final String toastMessage =
            "Fill empty fields";
    private static final String toastMessageCategory =
            "Incorrect category";

    @Test
    public void createNewsPositive() {

        pageObjectNews.createNewsPositive();
        onView(withText("Control panel")).check(matches(withText("Control panel")));
    }

    @Test
    public void createNewsEmptyCategory() {

        pageObjectNews.createNewsNegative("Category");
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void createNewsEmptyTitle() {

        pageObjectNews.createNewsNegative("Title");
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void createNewsEmptyPublicationDate() {

        pageObjectNews.createNewsNegative("Publication date");
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void createNewsEmptyTime() {

        pageObjectNews.createNewsNegative("Time");
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void createNewsEmptyDescription() {

        pageObjectNews.createNewsNegative("Description");
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void createEmptyNewsAll() {

        pageObjectNews.formCreating();
        onView(withId(R.id.save_button)).perform(click());
        onView(withText(toastMessage)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void createNewsAnyCategory() {

        pageObjectNews.createNewsAnyCategory("test");
        onView(withText(toastMessageCategory)).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void canceledCreateNews() {

        pageObjectNews.formCreating();

        onView(withHint("Category")).perform(typeText("test"), closeSoftKeyboard());
        onView(withHint("Title")).perform(typeText("test"), closeSoftKeyboard());

        onView(withId(R.id.cancel_button)).perform(scrollTo(), click());
        onView(withId(android.R.id.button1)).perform(scrollTo(), click());

        onView(withText("Control panel")).check(matches(withText("Control panel")));
    }

}
