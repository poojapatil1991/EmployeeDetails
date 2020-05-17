package com.example.employeedetails.employeeList.view

import android.app.Dialog
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.employeedetails.R
import com.example.employeedetails.employeeList.viewModel.EmployeeDataViewModel
import com.example.employeedetails.employeeList.viewModel.ResponseViewModel
import com.example.employeedetails.utils.EmployeeDetailsApplication
import com.example.employeedetails.utils.NetworkConnection

import kotlinx.android.synthetic.main.activity_employee_list.*
import kotlinx.android.synthetic.main.content_employee_list.*

class EmployeeListActivity : AppCompatActivity(),SwipeRefreshLayout.OnRefreshListener,
    LifecycleOwner {

    private var employeeListAdapter: EmployeeListAdapter =
        EmployeeListAdapter(ArrayList<EmployeeDataViewModel>())
    private var context: EmployeeListActivity? = null
    private var responseViewModel: ResponseViewModel? = null
    private lateinit var mDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_list)
        setSupportActionBar(toolbar)
        context = this
        mDialog = Dialog(this)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)

        responseViewModel = ViewModelProviders.of(this).get(ResponseViewModel::class.java)
        rv_employee_list!!.layoutManager = LinearLayoutManager(context)
        rv_employee_list!!.adapter = employeeListAdapter

        responseViewModel!!.loadingError.observe(
            this,
            Observer { t: Boolean -> showError(t, "Please try again!!!") })

        responseViewModel!!.loading.observe(this, Observer { t: Boolean -> showLoading(t) })
        //responseViewModel!!.statusLiveData.observe(this, Observer { t: String -> showTitle(t) })
        responseViewModel!!.dataLiveData
            .observe(this, Observer { it: ArrayList<EmployeeDataViewModel> ->
                employeeListAdapter.setArrayList(it)
            })

        loadDataInRecyclerView()

    }
    //Function to show country feature in recycler view
    private fun loadDataInRecyclerView() {
        if (NetworkConnection.isNetworkConnected()) {
            responseViewModel!!.getEmployeeList()
        } else {
            showError(true, "Check your internet connection. Please try again!!!")
        }
    }

    override fun onRefresh() {
        swipe_refresh.isRefreshing = false
        loadDataInRecyclerView()
    }

    private fun showError(showError: Boolean, message: String) {
        if (showError) {
            Toast.makeText(
                EmployeeDetailsApplication.context,
                message,
                Toast.LENGTH_SHORT
            )
                .show()

        }
    }

    private fun showProgressDialog() {
        mDialog.setContentView(R.layout.progress_dialog_layout)
        mDialog.show()
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            showProgressDialog()
        } else {
            hideLoading()
        }
    }

    private fun hideLoading() {
        if (mDialog.isShowing) {
            mDialog.hide()
        }
    }

  /*  //Function to show title in action bar
    private fun showTitle(title: String) {
        supportActionBar!!.title = title
    }*/
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
}
