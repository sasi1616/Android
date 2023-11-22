package com.example.testapp

object ValidationUtil {

    fun validateNumber(mobileNumber: MobileNumber) : String {
        if (mobileNumber.mobilenumber.isNotEmpty()) {
            return  "Validation successfull"
        }
        return "mobile number is empty"

    }
}