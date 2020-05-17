package com.example.employeedetails.utils

//Gives the Context of application

import android.app.Application
import android.content.Context

class EmployeeDetailsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }

    companion object {
        lateinit var context: Context
    }
}