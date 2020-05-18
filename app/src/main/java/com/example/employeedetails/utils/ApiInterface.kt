package com.example.employeedetails.utils

import com.example.employeedetails.addEmployee.model.AddEmployee
import com.example.employeedetails.addEmployee.viewModel.AddResponseViewModel
import com.example.employeedetails.employeeList.model.EmployeeDetails
import com.example.employeedetails.employeeList.viewModel.DeleteResponseViewModel
import com.example.employeedetails.employeeList.viewModel.ResponseViewModel
import retrofit2.http.*
import rx.Observable


interface ApiInterface {
    // This API returns the list of employees from server
    @GET("/api/v1/employees")
    fun getEmployeeList(): Observable<ResponseViewModel>

    @POST("/api/v1/create")
    fun addEmployee(
        @Body employee: AddEmployee?
    ): Observable<AddResponseViewModel>

    @POST("/api/v1/delete/{id}")
    fun deleteEmployee(@Path("id")id:String):Observable<DeleteResponseViewModel>

}