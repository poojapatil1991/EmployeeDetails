package com.example.employeedetails.employeeList.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.employeedetails.R
import com.example.employeedetails.databinding.EmployeeDataBinding
import com.example.employeedetails.employeeList.viewModel.EmployeeDataViewModel

class EmployeeListAdapter(private var mEmployeeViewModelList: ArrayList<EmployeeDataViewModel>?) :
    RecyclerView.Adapter<EmployeeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(viewGroup.context)
        val employeeDataBinding: EmployeeDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.employee_list_card, viewGroup, false)

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
    class ViewHolder(private val employeeBinding: EmployeeDataBinding) :
        RecyclerView.ViewHolder(employeeBinding.root) {

        fun bind(employeeDataViewModel: EmployeeDataViewModel) {
            this.employeeBinding.employeeDataViewModel = employeeDataViewModel
            employeeBinding.executePendingBindings()
        }

    }

    fun setArrayList(arrayList: ArrayList<EmployeeDataViewModel>) {
        mEmployeeViewModelList = arrayList
        notifyDataSetChanged()
    }
}
