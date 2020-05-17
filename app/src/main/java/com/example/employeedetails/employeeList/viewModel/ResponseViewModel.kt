package com.example.employeedetails.employeeList.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeedetails.employeeList.EmployeeListUseCase
import com.example.employeedetails.executer.IExecuterThread
import com.example.employeedetails.executer.UIThread
import com.example.employeedetails.module.ThreadModule
import rx.Subscriber

class ResponseViewModel:ViewModel() {

    var status: String = " "
    var data: ArrayList<EmployeeDataViewModel> = ArrayList()
    var loadingError = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()
    var statusLiveData = MutableLiveData<String>()
    var dataLiveData = MutableLiveData<ArrayList<EmployeeDataViewModel>>()
    private val uiThread: UIThread = ThreadModule().providePostExecutionThread()
    private val executorThread: IExecuterThread = ThreadModule().provideExecutorThread()

    private val employeeListUseCase: EmployeeListUseCase =
        EmployeeListUseCase(executorThread, uiThread)

    fun getEmployeeList() {
        loadingError.value = false
        loading.value = true
        employeeListUseCase.execute(EmployeeListSubscriber())
    }

    /*
   Subscriber to show image list on UI
   as soon as image list downloads from server it get notifies and show list of images on UI
    */
    inner class EmployeeListSubscriber : Subscriber<ResponseViewModel>() {

        override fun onCompleted() {}
        override fun onError(e: Throwable) {
            loading.value = false
            loadingError.value = true
            Log.e("EmployeeListAPI", e.toString())
        }

        override fun onNext(resDetails: ResponseViewModel) {
            dataLiveData.value = resDetails.data
            statusLiveData.value = resDetails.status
            loadingError.value = false
            loading.value = false
        }
    }
}