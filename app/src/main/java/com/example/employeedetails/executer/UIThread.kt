package com.example.employeedetails.executer

import com.example.employeedetails.executer.IExecuterThread
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers

/*
UI Thread
 */
class UIThread : IExecuterThread {
    override fun getScheduler(): Scheduler? {
        return AndroidSchedulers.mainThread()
    }
}