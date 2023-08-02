package com.kisannetwork.assignment.contact

import android.content.Context
import android.content.DialogInterface
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kisannetwork.assignment.R

class ContactInfoDialog(
    context: Context,
    contact: Contact,
    onClickListener: DialogInterface.OnClickListener
) : MaterialAlertDialogBuilder(context) {

    init {
        setTitle(context.getString(R.string.contact_info))
        setMessage("${contact.displayName}\n+${contact.countryCode} ${contact.phoneNumber}")
        setPositiveButton(context.getString(R.string.send_message), onClickListener)
    }

}