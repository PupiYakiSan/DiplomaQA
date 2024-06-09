package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import ru.iteco.fmhandroid.R;

public class PageObjectPage {

    public void menuPage(String page) {

        onView(withId(R.id.main_menu_image_button)).perform(click());
        onView(withText(page)).perform(click());
    }

}