package dmt.appsolution.co.dmt.dialog

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.ArrayAdapter
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.entity.Constants
import dmt.appsolution.co.dmt.entity.NoticeDialogListener
import kotlinx.android.synthetic.main.dialog_filter_restaurant.*

/**
 * Created by Martin on 26/12/2017.
 */
class DialogFilter: DialogFragment() {
    var noticeDialog: NoticeDialogListener? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setView(activity.layoutInflater.inflate(R.layout.dialog_filter_restaurant, null))
        var dialog: AlertDialog = builder.create()
        dialog.setOnShowListener(DialogInterface.OnShowListener {
            noticeDialog = activity as NoticeDialogListener
            startSpinnerFilter(dialog)
            buttonsListener(dialog)
        })
        return dialog
    }

    private fun startSpinnerFilter(dialog: Dialog){
        var list:MutableList<String> = mutableListOf()
        list.add(Constants.ALL_FOOD)
        list.add(Constants.CHICKEN_FOOD)
        list.add(Constants.MEAT_FOOD)
        list.add(Constants.FISH_FOOD)
        var adapterFood: ArrayAdapter<String> = ArrayAdapter(activity,
                android.R.layout.simple_spinner_item, list)
        dialog.spinnerFilter.adapter = adapterFood
    }

    private fun buttonsListener(dialog: Dialog){
        dialog.buttonAcceptFilter.setOnClickListener{
            Constants.FOOD_FILTER = dialog.spinnerFilter.selectedItem.toString()
            noticeDialog!!.onAcceptButton()
            this.dismiss()
        }
        dialog.buttonCancelFilter.setOnClickListener{this.dismiss()}
    }


}