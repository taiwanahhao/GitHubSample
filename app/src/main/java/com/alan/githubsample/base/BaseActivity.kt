package com.alan.githubsample.base

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.alan.githubsample.manager.DialogManager


/**
 * Created by AlanChang on 2020/12/12.
 */
open class BaseActivity : AppCompatActivity() {

    private val loadingDialog by lazy {
        DialogManager.loadingDialog(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getViewModel().loadingEvent.observe(this, Observer { show ->
            if (show)
                loadingDialog.show()
            else
                loadingDialog.dismiss()
        })

        getViewModel().errorEvent.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    fun closeKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = currentFocus ?: View(this)
        imm.hideSoftInputFromWindow(view.windowToken, 0)

        if (view is EditText) {
            view.clearFocus()
        }
    }

    open fun getViewModel() = BaseViewModel()
}