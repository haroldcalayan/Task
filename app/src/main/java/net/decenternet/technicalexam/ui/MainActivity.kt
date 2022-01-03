package net.decenternet.technicalexam.ui

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import net.decenternet.technicalexam.R

class MainActivity : AppCompatActivity() {

    private var ivBrandingLogo: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivBrandingLogo = findViewById<View>(R.id.ivBrandingLogo) as ImageView

        /**
         * Tasks
         * 1. Change the text "Put your favorite quote here" with your own quote.
         * 2. Set any image that best illustrate the quote to ivBrandingLogo.
         * 3. Display this screen for 3 seconds, then redirect to TasksActivity and close this MainActivity.
         */
    }

    companion object {
        var theCatalyst = true
    }
}