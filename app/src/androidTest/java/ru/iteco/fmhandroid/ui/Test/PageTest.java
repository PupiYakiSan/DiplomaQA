package ru.iteco.fmhandroid.ui.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

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
import ru.iteco.fmhandroid.ui.PageObject.PageObjectPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class PageTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void startPage() {
        pageObjectBefore.loginIn();
    }

    PageObjectBefore pageObjectBefore = new PageObjectBefore();
    PageObjectPage pageObjectPage = new PageObjectPage();

    @Test
    public void pageQuoteTopic() {

        onView(withId(R.id.our_mission_image_button)).perform(click());
        onView(withId(R.id.our_mission_title_text_view)).check(matches(withText("Love is all")));
    }

    @Test
    public void pageAbout() {

        pageObjectPage.menuPage("About");
        onView(withId(R.id.about_privacy_policy_label_text_view))
                .check(matches(withText("Privacy Policy:")));
    }

    @Test
    public void pageNews() {

        pageObjectPage.menuPage("News");
        onView(withText("News"))
                .check(matches(isDisplayed()));
        onView(withId(R.id.all_news_text_view)).check(matches(not(isDisplayed())));
    }

    @Test
    public void pageNewsPageAbout() {

        pageObjectPage.menuPage("News");
        pageObjectPage.menuPage("About");
        onView(withId(R.id.about_privacy_policy_label_text_view))
                .check(matches(withText("Privacy Policy:")));
    }

    @Test
    public void pageNewsPageMain() {

        pageObjectPage.menuPage("News");
        pageObjectPage.menuPage("Main");
        onView(withText("News"))
                .check(matches(isDisplayed()));
        onView(withId(R.id.all_news_text_view)).check(matches(isDisplayed()));
    }

    @Test
    public void pageQuoteTopicPageMain() {

        onView(withId(R.id.our_mission_image_button)).perform(click());
        pageObjectPage.menuPage("Main");
        onView(withText("News"))
                .check(matches(isDisplayed()));
        onView(withId(R.id.all_news_text_view)).check(matches(isDisplayed()));
    }

    @Test
    public void pageQuoteTopicPageNews() {

        onView(withId(R.id.our_mission_image_button)).perform(click());
        pageObjectPage.menuPage("News");
        onView(withText("News"))
                .check(matches(isDisplayed()));
        onView(withId(R.id.all_news_text_view)).check(matches(not(isDisplayed())));
    }

    @Test
    public void pageQuoteTopicPageAbout() {

        onView(withId(R.id.our_mission_image_button)).perform(click());
        pageObjectPage.menuPage("About");
        onView(withId(R.id.about_privacy_policy_label_text_view))
                .check(matches(withText("Privacy Policy:")));
    }

    @Test
    public void exitPageAbout() {

        pageObjectPage.menuPage("About");
        onView(withId(R.id.about_back_image_button)).perform(click());
        onView(withText("News"))
                .check(matches(isDisplayed()));
        onView(withId(R.id.all_news_text_view)).check(matches(isDisplayed()));
    }

    @Test
    public void pageAboutPageNews() {

        pageObjectPage.menuPage("About");
        onView(withId(R.id.about_back_image_button)).perform(click());
        pageObjectPage.menuPage("News");
        onView(withText("News"))
                .check(matches(isDisplayed()));
        onView(withId(R.id.all_news_text_view)).check(matches(not(isDisplayed())));
    }
}
