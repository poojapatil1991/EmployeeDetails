package com.example.employeedetails.executer

import com.example.employeedetails.executer.IExecuterThread
import rx.Scheduler
import rx.schedulers.Schedulers

/*
Creates background thread
 */
class BackgroundThread : IExecuterThread {
    override fun getScheduler(): Scheduler? {
        return Schedulers.newThread()
    }
}