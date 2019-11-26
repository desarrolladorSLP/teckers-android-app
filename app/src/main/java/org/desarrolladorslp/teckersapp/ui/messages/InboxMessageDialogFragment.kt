package org.desarrolladorslp.teckersapp.ui.messages

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import org.desarrolladorslp.teckersapp.R


class InboxMessageDialogFragment : DialogFragment() {

    @NonNull
    @Override
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        if (arguments != null) {
            if (arguments!!.getBoolean("notAlertDialog")) {
                return super.onCreateDialog(savedInstanceState);
            }
        }

        val builder: AlertDialog.Builder = AlertDialog.Builder(context!!)
        builder.setTitle("Alert Dialog")
        builder.setMessage("Alert Dialog inside DialogFragment")

        builder.setPositiveButton("Ok",
            DialogInterface.OnClickListener { dialog, which -> dismiss() })

        builder.setNegativeButton(
            "Cancel",
            DialogInterface.OnClickListener { dialog, which -> dismiss() })

        return builder.create()
    }

    @NonNull
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_inbox_message_dialog, container, false)
    }


    override fun onResume() {
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    interface DialogListener {
        fun onFinishEditDialog(inputText: String)
    }
}
