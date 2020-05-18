package com.example.employeedetails.addEmployee.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeedetails.addEmployee.AddEmployeeUseCase
import com.example.employeedetails.addEmployee.model.AddEmployee
import com.example.employeedetails.employeeList.viewModel.ResponseViewModel
import com.example.employeedetails.executer.IExecuterThread
import com.example.employeedetails.executer.UIThread
import com.example.employeedetails.module.ThreadModule
import rx.Subscriber

class AddResponseViewModel :ViewModel(){
    var status:String = ""
    var data: AddEmployee = AddEmployee()
    var loadingError = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()
    var statusLiveData = MutableLiveData<String>()
    private val uiThread: UIThread = ThreadModule().providePostExecutionThread()
    private val executorThread: IExecuterThread = ThreadModule().provideExecutorThread()

    private val addEmployeeUseCase : AddEmployeeUseCase = AddEmployeeUseCase(executorThread, uiThread)

    fun addEmployee(name:String,salary :String,age:String) {
        loadingError.value = false
        loading.value = true
        addEmployeeUseCase.name = name
        addEmployeeUseCase.salary = salary
        addEmployeeUseCase.age = age
        addEmployeeUseCase.execute(AddEmployeeSubscriber())
    }
    inner class AddEmployeeSubscriber : Subscriber<AddResponseViewModel>() {

        override fun onCompleted() {}
        override fun onError(e: Throwable) {
            loading.value = false
            loadingError.value = true
            Log.e("EmployeeListAPI", e.toString())
        }

        override fun onNext(resDetails: AddResponseViewModel) {
            statusLiveData.value = resDetails.status
            loadingError.value = false
            loading.value = false
        }
    }
}
