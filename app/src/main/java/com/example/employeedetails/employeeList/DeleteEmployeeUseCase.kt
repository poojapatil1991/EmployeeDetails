package com.example.employeedetails.employeeList

import com.example.employeedetails.employeeList.viewModel.DeleteResponseViewModel
import com.example.employeedetails.employeeList.viewModel.ResponseViewModel
import com.example.employeedetails.executer.IExecuterThread
import com.example.employeedetails.executer.UIThread
import com.example.employeedetails.module.ApiModule
import com.example.employeedetails.utils.ApiInterface
import com.example.employeedetails.utils.UseCase
import rx.Observable

class DeleteEmployeeUseCase (executorThreadI: IExecuterThread, postExecuterThread: UIThread) :
    UseCase<DeleteResponseViewModel>(executorThreadI, postExecuterThread) {
    private var apiRequest: ApiInterface? = ApiModule().provideAllApi()
    var id :String = ""
    override fun createObservable(): Observable<DeleteResponseViewModel> {
        return apiRequest!!.deleteEmployee(id)
    }
}