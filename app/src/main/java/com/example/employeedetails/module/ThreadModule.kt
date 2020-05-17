package com.example.employeedetails.module

import com.example.employeedetails.executer.BackgroundThread
import com.example.employeedetails.executer.IExecuterThread
import com.example.employeedetails.executer.UIThread

// module to get the required threads
class ThreadModule {
    //Function returns the background thread
    fun provideExecutorThread(): IExecuterThread {
        return BackgroundThread()
    }

    // Function returns the UI thread
    fun providePostExecutionThread(): UIThread {
        return UIThread()
    }
}