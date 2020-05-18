package com.example.employeedetails.addEmployee.view

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.employeedetails.R
import com.example.employeedetails.addEmployee.viewModel.AddResponseViewModel
import com.example.employeedetails.employeeList.viewModel.ResponseViewModel
import com.example.employeedetails.utils.EmployeeDetailsApplication
import kotlinx.android.synthetic.main.activity_add_employee2.*

class AddEmployeeActivity : AppCompatActivity() {
    private var context: AddEmployeeActivity? = null
    private var addResponseViewModel: AddResponseViewModel? = null
    private lateinit var mDialog: Dialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee2)

        context = this
        mDialog = Dialog(this)
        mDialog.setCancelable(false)
        mDialog.setCanceledOnTouchOutside(false)

        addResponseViewModel = ViewModelProviders.of(this).get(AddResponseViewModel::class.java)
        addResponseViewModel!!.loading.observe(this, Observer { t: Boolean -> showLoading(t) })
        addResponseViewModel!!.loadingError.observe(
            this,
            Observer { t: Boolean -> showError(t, "Please try again!!!") })

        addResponseViewModel!!.statusLiveData.observe(this, Observer { t:String -> showSuccess(t) })

        btn_add_emp_submit.setOnClickListener(View.OnClickListener { v: View? ->addEmployee() })
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
    private fun addEmployee(){
        var name = etv_add_emp_name.text.toString()
        var salary = etv_add_emp_salary.text.toString()
        var age = etv_add_emp_age.text.toString()
        addResponseViewModel!!.addEmployee(name, salary, age)
    }
    private fun showSuccess(message: String){
        if(message == "success")
            this.finish()
    }
}
