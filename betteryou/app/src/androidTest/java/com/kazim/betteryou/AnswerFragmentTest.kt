import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.runner.AndroidJUnit4
import com.kazim.betteryou.R
import com.kazim.betteryou.UserFragments.AnswerFragment
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnswerFragmentTest {

    @Test
    fun testRecyclerViewVisibility() {
        // AnswerFragment'i başlat
        val scenario = launchFragmentInContainer<AnswerFragment>()

        // RecyclerView'i bul
        scenario.onFragment { fragment ->
            val recyclerView = fragment.requireView().findViewById<RecyclerView>(R.id.recylerView)

            // RecyclerView'in görünür olduğunu kontrol et
            Espresso.onView(ViewMatchers.withId(R.id.recylerView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }
}
