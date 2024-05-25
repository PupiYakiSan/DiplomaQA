package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isNotChecked;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static java.lang.Thread.sleep;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.test.espresso.RootViewException;
import androidx.test.espresso.ViewInteraction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import ru.iteco.fmhandroid.R;

public class PageObject {

    public void loginOut() {

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            onView(withText("News")).check(matches(isDisplayed()));
            onView(withId(R.id.authorization_image_button)).perform(click());
            onView(withId(android.R.id.title)).perform(click());
        } catch (Exception e){
        }
    }

    public void loginIn() {

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            onView(withText("Authorization")).check(matches(isDisplayed()));
            authorization("login2", "password2");
        } catch (Exception e){
        }
    }

    public void authorization(String login, String password) {

        onView(withHint("Login")).
                perform(typeText(login), closeSoftKeyboard());
        onView(withHint("Password")).
                perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.enter_button)).perform(click());

        try {
            sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void menuPage(String page) {

        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText(page)).perform(click());
    }

    public void formCreating() {
        menuPage("News");
        onView(withId(R.id.edit_news_material_button)).perform(click());
        onView(withId(R.id.add_news_image_view)).perform(click());
    }

    public void createNewsPositive() {
        formCreating();
        onView(withHint("Category")).perform(click());
        onView(withHint("Title")).perform(click());

        onView(withId(R.id.news_item_publish_date_text_input_edit_text)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.news_item_publish_time_text_input_edit_text)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        onView(withHint("Description")).perform(typeText("test"), closeSoftKeyboard());

        onView(withId(R.id.save_button)).perform(scrollTo(), click());
    }

    public void createNewsNegative(String nameField) {
        formCreating();
        onView(withHint("Category")).perform(click());
        onView(withHint("Title")).perform(click());

        onView(withId(R.id.news_item_publish_date_text_input_edit_text)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.news_item_publish_time_text_input_edit_text)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        onView(withHint("Description")).perform(typeText("test"), closeSoftKeyboard());

        onView(withHint(nameField)).perform(clearText());
        onView(withId(R.id.save_button)).perform(scrollTo(), click());
    }

    public void createNewsAnyCategory(String data) {
        formCreating();
        onView(withHint("Category")).perform(typeText(data), closeSoftKeyboard());
        onView(withHint("Title")).perform(typeText(data), closeSoftKeyboard());

        onView(withId(R.id.news_item_publish_date_text_input_edit_text)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        onView(withId(R.id.news_item_publish_time_text_input_edit_text)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());

        onView(withHint("Description")).perform(typeText("test"), closeSoftKeyboard());

        onView(withId(R.id.save_button)).perform(scrollTo(), click());
    }

}