package com.example.employeedetails.utils

import com.example.employeedetails.employeeList.viewModel.ResponseViewModel
import retrofit2.http.GET
import rx.Observable

interface ApiInterface {
    // This API returns the Country features from server
    @GET("/employees")
    fun getAssociateList(): Observable<ResponseViewModel>
}