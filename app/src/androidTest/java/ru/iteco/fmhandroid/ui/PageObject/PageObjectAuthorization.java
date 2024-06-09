package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import ru.iteco.fmhandroid.R;

public class PageObjectAuthorization {

    public void authorization(String login, String password) {

        onView(withHint("Login")).
                perform(typeText(login), closeSoftKeyboard());
        onView(withHint("Password")).
                perform(typeText(password), closeSoftKeyboard());
        onView(withId(R.id.enter_button)).perform(click());

    }

}