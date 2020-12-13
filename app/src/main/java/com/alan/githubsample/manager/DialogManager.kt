package com.alan.githubsample.manager

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.alan.githubsample.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Created by AlanChang on 2020/12/12.
 */
object DialogManager {

    fun loadingDialog(context: Context): AlertDialog {
        return MaterialAlertDialogBuilder(context)
            .setView(R.layout.dialog_loading)
            .create()
    }
}