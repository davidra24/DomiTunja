package dmt.appsolution.co.dmt.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import dmt.appsolution.co.dmt.R

/**
 * Created by Martin on 26/12/2017.
 */
class DialogFilter: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        //return super.onCreateDialog(savedInstanceState)
        var builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setView(activity.layoutInflater.inflate(R.layout.dialog_filter_restaurant, null))
        return builder.create()
    }
}