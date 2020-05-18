package com.example.employeedetails.addEmployee

import com.example.employeedetails.addEmployee.model.AddEmployee
import com.example.employeedetails.addEmployee.viewModel.AddResponseViewModel
import com.example.employeedetails.employeeList.model.EmployeeDetails
import com.example.employeedetails.employeeList.viewModel.ResponseViewModel
import com.example.employeedetails.executer.IExecuterThread
import com.example.employeedetails.executer.UIThread
import com.example.employeedetails.module.ApiModule
import com.example.employeedetails.utils.ApiInterface
import com.example.employeedetails.utils.UseCase
import rx.Observable

class AddEmployeeUseCase  (executorThreadI: IExecuterThread, postExecuterThread: UIThread) :
    UseCase<AddResponseViewModel>(executorThreadI, postExecuterThread) {
    private var apiRequest: ApiInterface? = ApiModule().provideAllApi()
    var name :String = ""
    var salary : String = ""
    var age : String = ""
    override fun createObservable(): Observable<AddResponseViewModel> {
        var employeeData : AddEmployee = AddEmployee()
        employeeData.name = name
        employeeData.salary = salary
        employeeData.age = age
        return apiRequest!!.addEmployee(employeeData)
    }
}