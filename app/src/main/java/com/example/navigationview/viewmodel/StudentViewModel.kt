package com.example.navigationview.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.*
import com.example.navigationview.R
import com.example.navigationview.db.model.Student
import com.example.navigationview.repository.StudentRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    val repository: StudentRepository,
    application: Application
) : AndroidViewModel(application), LifecycleObserver {

    /**
     * get list of student list from DB
     */
   // var studentList: LiveData<List<Student>>? = null

    var studentList: LiveData<List<Student>>? = null

    /**
     * get list of student list from DB
     */
    var student: LiveData<Student>? = null

    /**
     * get list of student list from DB
     */
    var addStudentInfo = MutableLiveData<Long>()

    /**
     * get list of student list from DB
     */
    var updateStudentRecord = MutableLiveData<Int>()
    var nameError = MutableLiveData<String>()
    var emailError = MutableLiveData<String>()
    var addressError = MutableLiveData<String>()
    var phoneError = MutableLiveData<String>()



    var id: String? = null
    var from: String? = null
    var email = MutableLiveData<String>()
    var name = MutableLiveData<String>()
    var address = MutableLiveData<String>()
    var phone = MutableLiveData<String>()

    /*
    * Get list of student into table
    */
    fun getStudentList() {
        viewModelScope.launch {
            studentList = repository.getStudentList()
        }
    }

    /*
    * Added new student record into table
    */
    fun addStudentInfo() {
        // validate input fields
        val isValid = validateUserInputFiled(
            name.value,
            email.value,
            address.value,
            phone.value
        )
        viewModelScope.launch {
            if (isValid) {
                val data = Student(
                    null,
                    name.value.toString(),
                    email.value.toString(),
                    address.value.toString(),
                    phone.value.toString()
                )
                addStudentInfo.value = repository.addStudentInfo(data)
            }
        }
    }

    /*
     * updated existing student record into table
     */
    fun updateStudent(id: Int?) {
        // validate input fields
        val isValid = validateUserInputFiled(
            name.value,
            email.value,
            address.value,
            phone.value
        )
        viewModelScope.launch {
            if (isValid) {
                val data = Student(
                    id,
                    name.value.toString(),
                    email.value.toString(),
                    address.value.toString(),
                    phone.value.toString()
                )
                updateStudentRecord.value = repository.updateStudentInfo(data)
            }
        }
    }

    /*
     * Fetch single student record based on the selected model primary ID
     */
    fun fetchSingleStudent(id: String?) {
        viewModelScope.launch {
            id?.let {
                student = repository.getIndividualStudent(it.toLong())
            }
        }
    }

    /**
     * Validate user inputs
     */
    private fun validateUserInputFiled(
        name: String? = null,
        email: String? = null,
        address: String? = null,
        phone: String? = null
    ): Boolean {
        var nameValid: Boolean? = false
        var emailValid: Boolean? = false
        var addressValid: Boolean? = false
        var phoneValid: Boolean? = false

        if (name.isNullOrEmpty())
            nameError.value = getApplication<Application>().resources.getString(R.string.name_error)
        else
            nameValid = true

        if (email.isNullOrEmpty()) {
            emailError.value = getApplication<Application>().resources.getString(R.string.email_error)
        }else {
            emailValid = isEmail(email)
            if(emailValid == false){
                emailError.value = getApplication<Application>().resources.getString(R.string.email_error)
            }
        }


        if (address.isNullOrEmpty())
            addressError.value = getApplication<Application>().resources.getString(R.string.address_error)
        else
            addressValid = true


        if (phone.isNullOrEmpty()) {
            phoneError.value = getApplication<Application>().resources.getString(R.string.phone_error)
        } else {
            if (phone.length != 10)
                phoneError.value = getApplication<Application>().resources.getString(R.string.phone_error)
            else
                phoneValid = true
        }
        return if (nameValid!! && emailValid!! && addressValid!! && phoneValid!!)
            true
        else
            false
    }


    fun isEmail(email: String?):Boolean{
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun addorUpdateStudent() {
        nameError.value = ""
        emailError.value = ""
        addressError.value = ""
        phoneError.value = ""
        if (from.isNullOrEmpty())
            addStudentInfo()
        else
            updateStudent(id?.toInt())
    }


}