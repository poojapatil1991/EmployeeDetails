package com.example.employeedetails.employeeList.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.employeedetails.employeeList.DeleteEmployeeUseCase
import com.example.employeedetails.executer.IExecuterThread
import com.example.employeedetails.executer.UIThread
import com.example.employeedetails.module.ThreadModule
import rx.Subscriber

class DeleteResponseViewModel : ViewModel() {

    var status: String = " "
    var message: String = " "

    var statusLiveData = MutableLiveData<String> ()
    var messageLiveData = MutableLiveData<String>()
    var loadingError = MutableLiveData<Boolean>()
    var loading = MutableLiveData<Boolean>()

    private val uiThread: UIThread = ThreadModule().providePostExecutionThread()
    private val executorThread: IExecuterThread = ThreadModule().provideExecutorThread()

    private val deleteEmployeeUseCase: DeleteEmployeeUseCase =
        DeleteEmployeeUseCase(executorThread, uiThread)


    fun deleteEmployee(id:String){
        loading.value = true
        loadingError.value =false
        deleteEmployeeUseCase.id = id
        deleteEmployeeUseCase.execute(DeleteEmployeeSubscriber())
    }

    inner class DeleteEmployeeSubscriber : Subscriber<DeleteResponseViewModel>() {

        override fun onCompleted() {}
        override fun onError(e: Throwable) {
            loadingError.value = true
            loading.value = false
            Log.e("DeleteEmployeeAPI", e.toString())
        }

        override fun onNext(resDetails: DeleteResponseViewModel) {
            loading.value = false
            loadingError.value = false
            statusLiveData.value = resDetails.status
            messageLiveData.value = resDetails.message
            Log.e("DeleteEmployeeAPI", resDetails.toString())
        }
    }
}