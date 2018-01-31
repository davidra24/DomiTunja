package dmt.appsolution.co.dmt.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.widget.ArrayAdapter
import dmt.appsolution.co.dmt.R
import dmt.appsolution.co.dmt.constants.Constants
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
        dialog.setOnShowListener({
            noticeDialog = activity as NoticeDialogListener
            startSpinnerFilter(dialog)
            buttonsListener(dialog)
        })
        return dialog
    }

    private fun startSpinnerFilter(dialog: Dialog){
        var list: MutableList<String> = mutableListOf()
        Constants.restaurantType.forEach { tipoLugar -> list.add(tipoLugar.tipoLugar!!) }
        var adapterFood: ArrayAdapter<String> = ArrayAdapter(activity,
                android.R.layout.simple_spinner_item, list)
        dialog.spinnerFilter.adapter = adapterFood
    }

    private fun buttonsListener(dialog: Dialog){
        dialog.buttonAcceptFilter.setOnClickListener{
            Constants.restaurantType
                    .filter { it.tipoLugar == dialog.spinnerFilter.selectedItem.toString() }
                    .forEach { Constants.FOOD_FILTER = it.idTipoLugar!! }
            noticeDialog!!.onAcceptButton()
            this.dismiss()
        }
        dialog.buttonCancelFilter.setOnClickListener{this.dismiss()}
    }


}