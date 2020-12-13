package com.alan.githubsample.search.view

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.alan.githubsample.R
import com.alan.githubsample.base.BaseActivity
import com.alan.githubsample.base.BaseViewModel
import com.alan.githubsample.paging.IPagedBoundaryCallback
import com.alan.githubsample.search.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search_user.*
import org.koin.android.viewmodel.ext.android.viewModel

class SearchUserActivity : BaseActivity(),
    IPagedBoundaryCallback {

    private val viewModel: SearchViewModel by viewModel()
    private val adapter = UsersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_user)

        list_user.adapter = adapter

        viewModel.usersEvent.observe(this, Observer {
            adapter.submitList(it)
        })
    }

    override fun onStart() {
        super.onStart()

        imb_search.setOnClickListener {
            closeKeyboard()
            viewModel.search(edit_keyword.text.toString(), this)
        }
    }

    override fun getViewModel(): BaseViewModel {
        return viewModel
    }

    override fun isEmptyList(empty: Boolean) {
        text_not_found.visibility = if (empty) View.VISIBLE else View.GONE
    }

}