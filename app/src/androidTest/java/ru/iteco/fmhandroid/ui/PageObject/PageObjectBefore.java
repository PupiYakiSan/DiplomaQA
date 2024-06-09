package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.ui.Service.Expectation.waitDisplayed;

import ru.iteco.fmhandroid.R;

public class PageObjectBefore {
    PageObjectAuthorization pageObjectAuthorization = new PageObjectAuthorization();
    public void loginOut() {

        try {
            onView(isRoot()).perform(waitDisplayed(R.id.authorization_image_button, 5000));
            onView(withId(R.id.authorization_image_button)).perform(click());
            onView(withId(android.R.id.title)).perform(click());
        } catch (Exception e){
        }
    }

    public void loginIn() {

        try {
            onView(isRoot()).perform(waitDisplayed(R.id.enter_button, 5000));
            pageObjectAuthorization.authorization("login2", "password2");
        } catch (Exception e){
        }
    }

}