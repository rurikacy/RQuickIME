package com.rquickime

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast

class PickerActivity : Activity() {
    private var requested = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Wait for Android to establish an IME client for this window.
        val editor = object : EditText(this) {
            override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection? {
                val connection = super.onCreateInputConnection(outAttrs)
                post { requestPicker() }
                return connection
            }
        }
        editor.alpha = 0f
        editor.showSoftInputOnFocus = false
        editor.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
        setContentView(editor)
        editor.requestFocus()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (!hasFocus || isFinishing) return
        if (requested) {
            // The system picker has given focus back after selection or cancellation.
            finish()
            return
        }
        // Also exit if the ROM never creates an input connection for this window.
        window.decorView.postDelayed({
            if (hasWindowFocus() && !isFinishing && !isDestroyed) {
                Toast.makeText(this, R.string.picker_missing, Toast.LENGTH_LONG).show()
                finish()
            }
        }, 3000)
    }

    private fun requestPicker() {
        if (!hasWindowFocus() || requested || isFinishing) return
        requested = true
        getSystemService(InputMethodManager::class.java).showInputMethodPicker()
    }

    override fun onStop() {
        super.onStop()
        if (!isChangingConfigurations && !isFinishing) finish()
    }
}
