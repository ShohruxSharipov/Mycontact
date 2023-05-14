package com.example.mycontact.Dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class Dialogue:DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

            return activity?.let {
                val builder = AlertDialog.Builder(it)

                builder.setTitle("Warn")
                    .setMessage("Do you want to delete ?")
                    .setPositiveButton("Yes") { dialog, id ->

                    }
                    .setNegativeButton("No") { dialog, id ->
                        dialog.cancel()
                    }
                builder.create()
            } ?: throw IllegalStateException("Activity cannot be null")

    }
}