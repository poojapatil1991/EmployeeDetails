package com.example.employeedetails.employeeList.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.employeedetails.R
import com.example.employeedetails.databinding.EmployeeCardBinding
import com.example.employeedetails.employeeList.viewModel.EmployeeDetailsViewModel
import java.util.*


class EmployeeListAdapter(private var mEmployeeViewModelList: ArrayList<EmployeeDetailsViewModel>?) :
    RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val employeeDataBinding: EmployeeCardBinding =
            DataBindingUtil.inflate(inflater, R.layout.employee_card, viewGroup, false)

        return ViewHolder(employeeDataBinding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val employeeViewModel = mEmployeeViewModelList!![position]
        viewHolder.bind(employeeViewModel)
    }

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return mEmployeeViewModelList!!.size
    }

    // View holder representing single row in list
    class ViewHolder(private val employeeBinding: EmployeeCardBinding) :
        RecyclerView.ViewHolder(employeeBinding.root) {

        fun bind(employeeDetailsViewModel: EmployeeDetailsViewModel) {
            this.employeeBinding.employeeDetailsViewModel = employeeDetailsViewModel
            employeeBinding.executePendingBindings()
        }

    }

    fun setArrayList(arrayList: ArrayList<EmployeeDetailsViewModel>) {
        mEmployeeViewModelList = arrayList
        notifyDataSetChanged()
    }
}
