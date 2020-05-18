package com.example.employeedetails.employeeList.view

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.employeedetails.employeeList.viewModel.DeleteResponseViewModel
import kotlinx.android.synthetic.main.employee_card.view.*

class SwipeToDeleteCallback(
    val deleteResponseViewModel: DeleteResponseViewModel,
    val employeeListAdapter: EmployeeListAdapter
) : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        var deleteID = viewHolder.itemView.tv_id.text
        deleteResponseViewModel.deleteEmployee(deleteID as String)
        employeeListAdapter.notifyDataSetChanged()
        Log.e("Delete ID", deleteID as String)
    }
}