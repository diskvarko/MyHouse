package com.example.myhouse.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.example.myhouse.R


class DialogText(val listener: NoticeDialogListener) : DialogFragment() {

    interface NoticeDialogListener {
        fun onSaveName(text: String)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val view: View = layoutInflater.inflate(R.layout.alert, null)
            val textView = view.findViewById<EditText>(R.id.textViewDialog)
            builder.setIcon(R.drawable.edit)
                .setView(view)
                .setPositiveButton("ОК") { dialog, _ ->
                    listener.onSaveName(text = textView?.text.toString())
                    dialog.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}