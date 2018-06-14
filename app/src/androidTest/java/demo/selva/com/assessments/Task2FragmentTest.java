package demo.selva.com.assessments;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import demo.selva.com.assessments.activity.MainActivity;
import demo.selva.com.assessments.fragment.Task2Fragment;
import demo.selva.com.assessments.util.NetworkUtils;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * @author selva.raman
 * @version 1.0
 * @since 6/14/2018
 */
@RunWith(AndroidJUnit4.class)
public class Task2FragmentTest {
    @Rule
    public final ActivityTestRule<MainActivity> activityTestRule = new
            ActivityTestRule<>(MainActivity.class);

    @Before
    public void init() {
        activityTestRule.getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, Task2Fragment.getInstance()).commit();
    }

    @Test
    public void testPleaseWaitTextIsDisplayed() throws InterruptedException {
        onView(withId(R.id.text_loading)).check(matches(isDisplayed()));
    }

    @Test
    public void testBarChartViewIsDisplayed() throws InterruptedException {
        Thread.sleep(5000);
        onView(withId(R.id.bar_chart_view)).check(matches(isDisplayed()));
    }

    @Test
    public void testNoInternetConnectionViewIsDisplayed() {
        if (!NetworkUtils.isNetworkConnected()) {
            onView(withText(R.string.text_no_internet_connection)).check(matches(isDisplayed()));
        }
    }
}
