package com.example.navigationview.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.navigationview.db.model.Student
import com.example.navigationview.extension.validation.view_ktx.validator
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
    var studentList = MutableLiveData<List<Student>>()

    /**
     * get list of student list from DB
     */
    var addStudentInfo = MutableLiveData<Long>()

    /**
     * get list of student list from DB
     */
    var updateStudentRecord = MutableLiveData<Int>()

    /**
     * get list of student list from DB
     */
    var student = MutableLiveData<Student>()

    var nameError = MutableLiveData<String>()
    var emailError = MutableLiveData<String>()
    var addressError = MutableLiveData<String>()
    var phoneError = MutableLiveData<String>()

    /*
    * Get list of student into table
    */
    fun getStudentList() {
        viewModelScope.launch {
            studentList.value = repository.getStudentList()
        }
    }

    /*
    * Added new student record into table
    */
    fun addStudentInfo(name: String, email: String, address: String, phone: String) {
        // validate input fields
        val isValid = validateUserInputFiled(name, email, address, phone)
        viewModelScope.launch {
            if (isValid) {
                val data = Student(null, name, email, address, phone)
                addStudentInfo.value = repository.addStudentInfo(data)
            }
        }
    }

    /*
     * updated existing student record into table
     */
    fun updateStudent(id: Int?, name: String, email: String, address: String, phone: String) {
        // validate input fields
        val isValid = validateUserInputFiled(name, email, address, phone)
        viewModelScope.launch {
            if (isValid) {
                val data = Student(id, name, email, address, phone)
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
                student.value = repository.getIndividualStudent(it.toLong())
            }
        }
    }

    /**
     * Validate user inputs
     */
    private fun validateUserInputFiled(
        name: String,
        email: String,
        address: String,
        phone: String
    ): Boolean {
        var nameValid = false
        var emailValid = false
        var addressValid = false
        var phoneValid = false
        name.validator().nonEmpty("Name cannot be empty")
            .maxLength(255)
            .addErrorCallback {
                nameError.value = "Please enter valid name"
            }
            .addSuccessCallback {
                nameValid = true
            }
            .check()


        email.validator().nonEmpty("email cannot be empty")
            .maxLength(255)
            .addErrorCallback {
                emailError.value = "Please enter valid email"
            }
            .addSuccessCallback {
                emailValid = true
            }
            .check()

        address.validator().nonEmpty("address cannot be empty")
            .maxLength(255)
            .addErrorCallback {
                addressError.value = "Please enter valid address"
            }
            .addSuccessCallback {
                addressValid = true
            }
            .check()

        phone.validator().nonEmpty("phone cannot be empty")
            .maxLength(255)
            .addErrorCallback {
                phoneError.value = "Please enter valid phone"
            }
            .addSuccessCallback {
                phoneValid = true
            }
            .check()

        return if (nameValid && emailValid && addressValid && phoneValid)
            true
        else
            false
    }
}