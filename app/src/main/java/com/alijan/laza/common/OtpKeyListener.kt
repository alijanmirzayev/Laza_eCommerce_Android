package com.alijan.laza.common

import android.view.KeyEvent
import android.view.View
import android.widget.EditText

class OtpKeyListener(private val currentEditText: EditText, private val previousEditText: EditText?) : View.OnKeyListener {
    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL) {
            if (currentEditText.text.isEmpty()) {
                previousEditText?.requestFocus()
            }
        }
        return false
    }
}
