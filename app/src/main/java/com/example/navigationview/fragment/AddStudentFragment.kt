package com.example.navigationview.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.navigationview.AppConstant
import com.example.navigationview.NavigationApplication
import com.example.navigationview.R
import com.example.navigationview.databinding.FragmentAddStudentBinding
import com.example.navigationview.viewmodel.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_add_student.*


/**
 * A simple [Fragment] subclass.
 * Use the [AddStudentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class AddStudentFragment : Fragment() {
    //View Binding
    lateinit var binding: FragmentAddStudentBinding
    //initialize the viewmodel
    lateinit var viewModel: StudentViewModel
    //Get the student primary ID if coming from edit
    var id: String? = null
    //Get the coming from screen
    var from: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            id = bundle.getString(AppConstant.ID)
            from = bundle.getString(AppConstant.IAMFROM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_student, container, false)
        //observe the fragment lifecyle
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //subscribe the viewmodel to the fragment
        viewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        binding.studentViewModel = viewModel

        from.let {
            viewModel.fetchSingleStudent(id)
        }
        binding.btnSave.setOnClickListener {
            clearErrorMessage()
            if (from.isNullOrEmpty()) {
                // Insert new student details into table
                viewModel.addStudentInfo(
                    binding.evName.text.toString(),
                    binding.evEmail.text.toString(),
                    binding.evAddress.text.toString(),
                    binding.evPhone.text.toString()
                )
            } else {
                // update existing student details into table
                viewModel.updateStudent(
                    id?.toInt(),
                    binding.evName.text.toString(),
                    binding.evEmail.text.toString(),
                    binding.evAddress.text.toString(),
                    binding.evPhone.text.toString()
                )
            }
        }

        viewModel.addStudentInfo.observe(viewLifecycleOwner, Observer {
            findNavController().navigateUp()
        })

        viewModel.student.observe(viewLifecycleOwner, Observer { its ->
            its?.let {
                binding.evName.setText(it.name)
                binding.evEmail.setText(it.email)
                binding.evAddress.setText(it.address)
                binding.evPhone.setText(it.phone)
            }
        })

        viewModel.updateStudentRecord.observe(viewLifecycleOwner, Observer {
            findNavController().navigateUp()
        })

        viewModel.nameError.observe(viewLifecycleOwner, Observer {
           if(it.isNullOrEmpty())
              binding.tvNameError.text = ""
           else
               binding.tvNameError.text = it
        })

        viewModel.emailError.observe(viewLifecycleOwner, Observer {
            if(it.isNullOrEmpty())
                binding.tvEmailError.text = ""
            else
                binding.tvEmailError.text = it
        })

        viewModel.addressError.observe(viewLifecycleOwner, Observer {
            if(it.isNullOrEmpty())
                binding.tvAddressError.text = ""
            else
                binding.tvAddressError.text = it
        })

        viewModel.phoneError.observe(viewLifecycleOwner, Observer {
            if(it.isNullOrEmpty())
                binding.tvPhoneError.text = ""
            else
                binding.tvPhoneError.text = it
        })
    }

    private fun clearErrorMessage() {
        viewModel.nameError.value = null
        viewModel.addressError.value = null
        viewModel.emailError.value = null
        viewModel.phoneError.value = null
        binding.tvNameError.text = ""
        binding.tvAddressError.text = ""
        binding.tvPhoneError.text = ""
        binding.tvEmailError.text = ""
    }
}