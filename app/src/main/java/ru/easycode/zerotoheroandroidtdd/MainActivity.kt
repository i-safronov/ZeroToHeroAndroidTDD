package ru.easycode.zerotoheroandroidtdd

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import ru.easycode.zerotoheroandroidtdd.component.Count
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private val count: Count = Count.Base()

    private lateinit var countTextView: TextView
    private lateinit var incrementButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        countTextView = findViewById(R.id.countTextView)
        incrementButton = findViewById(R.id.incrementButton)

        incrementButton.setOnClickListener {
            countTextView.text = count.increment(countTextView.text.toString())
        }
    }

}