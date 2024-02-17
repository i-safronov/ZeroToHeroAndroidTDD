package ru.easycode.zerotoheroandroidtdd

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private lateinit var text: TextView
    private lateinit var parent: LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        parent = findViewById(R.id.rootLayout)
        button = findViewById(R.id.removeButton)
        text = findViewById(R.id.titleTextView)

        button.setOnClickListener {
            (parent as ViewGroup).removeView(text)
            button.isEnabled = false
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(
            KEY, !button.isEnabled
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState.getBoolean(KEY)) {
            (this.parent as ViewGroup).removeView(text)
            button.isEnabled = false
        }
    }

    companion object {
        const val KEY = "key"
    }

}