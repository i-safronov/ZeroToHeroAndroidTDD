package ru.easycode.zerotoheroandroidtdd

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.io.Serializable

class MainActivity : AppCompatActivity() {

    private var state: ViewState = ViewState.Initial

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
            state = ViewState.Removed
            state.apply(linearLayout = parent, view = text, button = button)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(
            KEY, state
        )
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        state = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            savedInstanceState.getSerializable(KEY, ViewState::class.java) as ViewState
        } else {
            savedInstanceState.getSerializable(KEY) as ViewState
        }
        state.apply(linearLayout = parent, view = text, button = button)
    }

    companion object {
        const val KEY = "key"
    }

}

private interface ViewState: Serializable {

    fun apply(linearLayout: LinearLayout, view: View, button: Button)

    object Initial: ViewState {
        override fun apply(linearLayout: LinearLayout, view: View, button: Button) = Unit
    }

    object Removed: ViewState {
        override fun apply(linearLayout: LinearLayout, view: View, button: Button) {
            (linearLayout as ViewGroup).removeView(view)
            button.isEnabled = false
        }
    }

}