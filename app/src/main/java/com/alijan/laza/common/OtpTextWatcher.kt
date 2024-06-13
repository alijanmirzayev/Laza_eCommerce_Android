package com.alijan.laza.common

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class OtpTextWatcher(private val currentEditText: EditText, private val nextEditText: EditText?) : TextWatcher {
    override fun afterTextChanged(s: Editable?) {
        if (s?.length == 1) {
            nextEditText?.requestFocus()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
}
