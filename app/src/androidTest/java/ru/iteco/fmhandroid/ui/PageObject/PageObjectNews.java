package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.Service.Expectation.waitDisplayed;

import ru.iteco.fmhandroid.R;

public class PageObjectNews {

    PageObjectPage pageObjectPage = new PageObjectPage();

    public void formCreating() {
        pageObjectPage.menuPage("News");
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