package com.example.employeedetails.employeeList.viewModel

import androidx.lifecycle.ViewModel

class EmployeeDataViewModel: ViewModel() {
    var id: String = ""
    var employee_name : String = ""
    var employee_salary: Int = 0
    var employee_age : Int = 0
    var profile_image: String = ""
}