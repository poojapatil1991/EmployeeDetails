package com.example.employeedetails.employeeList

import com.example.employeedetails.employeeList.viewModel.ResponseViewModel
import com.example.employeedetails.executer.IExecuterThread
import com.example.employeedetails.executer.UIThread
import com.example.employeedetails.module.ApiModule
import com.example.employeedetails.utils.ApiInterface
import com.example.employeedetails.utils.UseCase
import rx.Observable

class EmployeeListUseCase(executorThreadI: IExecuterThread, postExecuterThread: UIThread) :
    UseCase<ResponseViewModel>(executorThreadI, postExecuterThread) {
    private var apiRequest: ApiInterface? = ApiModule().provideAllApi()

    override fun createObservable(): Observable<ResponseViewModel> {
        return apiRequest!!.getAssociateList()
    }
}