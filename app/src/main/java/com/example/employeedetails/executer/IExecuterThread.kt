package com.example.employeedetails.executer

import rx.Scheduler

interface IExecuterThread {
    fun getScheduler(): Scheduler?
}