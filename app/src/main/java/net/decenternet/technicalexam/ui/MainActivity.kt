package net.decenternet.technicalexam.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
import net.decenternet.technicalexam.R
import net.decenternet.technicalexam.ui.tasks.TasksActivity

class MainActivity : AppCompatActivity() {

    private lateinit var ivBrandingLogo: ImageView
    private lateinit var textViewQuote: TextView

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivBrandingLogo = findViewById(R.id.ivBrandingLogo)
        textViewQuote = findViewById(R.id.textViewQuote)

        /**
         * Tasks
         * 1. Change the text "Put your favorite quote here" with your own quote.
         * 2. Set any image that best illustrate the quote to ivBrandingLogo.
         * 3. Display this screen for 3 seconds, then redirect to TasksActivity and close this MainActivity.
         */

        ivBrandingLogo.setImageResource(R.drawable.sacrifice_today)
        textViewQuote.setText(R.string.main_favorite_quote)

        startTimer(SCREEN_LIFE)
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }

    private fun startTimer(time: Long) {
        activityScope.launch {
            delay(time)
            finish()
            openTasks()
        }
    }

    private fun openTasks() {
        val intent = Intent(this, TasksActivity::class.java)
        startActivity(intent)
    }

    companion object {
        const val SCREEN_LIFE = 3000L

        var theCatalyst = false
    }
}