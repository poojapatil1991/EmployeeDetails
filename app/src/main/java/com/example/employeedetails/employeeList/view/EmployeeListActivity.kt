package com.example.employeedetails.employeeList.view

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.employeedetails.R
import com.example.employeedetails.addEmployee.view.AddEmployeeActivity
import com.example.employeedetails.employeeList.viewModel.DeleteResponseViewModel
import com.example.employeedetails.employeeList.viewModel.EmployeeDetailsViewModel
import com.example.employeedetails.employeeList.viewModel.ResponseViewModel
import com.example.employeedetails.utils.EmployeeDetailsApplication
import com.example.employeedetails.utils.NetworkConnection
import kotlinx.android.synthetic.main.activity_employee_list.*
import kotlinx.android.synthetic.main.content_employee_list.*


class EmployeeListActivity : AppCompatActivity(),SwipeRefreshLayout.OnRefreshListener,
    LifecycleOwner {

    private var employeeListAdapter: EmployeeListAdapter =
        EmployeeListAdapter(ArrayList<EmployeeDetailsViewModel>())
    private var context: EmployeeListActivity? = null
    private var responseViewModel: ResponseViewModel? = null
    private var deleteResponseViewModel: DeleteResponseViewModel? = null
    private lateinit var mDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_employee_list)
        setSupportActionBar(toolbar)
        context = this
        mDialog = Dialog(this)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)
        swipe_refresh.setOnRefreshListener(this)

        responseViewModel = ViewModelProviders.of(this).get(ResponseViewModel::class.java)
        deleteResponseViewModel = ViewModelProviders.of(this).get(DeleteResponseViewModel::class.java)

        rv_employee_list!!.layoutManager = LinearLayoutManager(context)
        rv_employee_list!!.adapter = employeeListAdapter

        loadDataInRecyclerView()

        responseViewModel!!.loadingError.observe(
            this,
            Observer { t: Boolean -> showError(t, "Please try again!!!") })

        responseViewModel!!.loading.observe(this, Observer { t: Boolean -> showLoading(t) })
        responseViewModel!!.dataLiveData
            .observe(this, Observer { it: ArrayList<EmployeeDetailsViewModel> ->
                employeeListAdapter.setArrayList(it)
            })

        val itemTouchHelperCallback: ItemTouchHelper.SimpleCallback =
            SwipeToDeleteCallback(deleteResponseViewModel!!,employeeListAdapter )
        ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(rv_employee_list)

        deleteResponseViewModel!!.loading.observe(this, Observer { t: Boolean -> showLoading(t)  })
        deleteResponseViewModel!!.loadingError.observe(
            this,
            Observer { t: Boolean -> showError(t, "Please try again!!!") })

        btn_add.setOnClickListener(View.OnClickListener { v: View? -> showAddEmployeeActivity()  })

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
    private fun showAddEmployeeActivity(){
        var intent: Intent = Intent(this,AddEmployeeActivity::class.java)
        startActivity(intent)
    }
}
