package com.example.employeedetails.utils

import rx.Subscriber

interface BaseInteractor <T> {
    fun execute(subscriber: Subscriber<T>)
}