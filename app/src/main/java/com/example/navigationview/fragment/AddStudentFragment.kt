package com.example.navigationview.fragment


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.navigationview.AppConstant
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
            viewModel.id = id
            viewModel.from = from
            viewModel.fetchSingleStudent(id)
        }

        viewModel.addStudentInfo.observe(viewLifecycleOwner, Observer {
            closeSoftKeyboard(context ,binding.btnSave)
            findNavController().navigateUp()
        })

        viewModel.student?.observe(viewLifecycleOwner, Observer { its ->
            its?.let {
                viewModel.name.value = it.name
                viewModel.email.value = it.email
                viewModel.address.value = it.address
                viewModel.phone.value = it.phone
            }
        })

        viewModel.updateStudentRecord.observe(viewLifecycleOwner, Observer {
            findNavController().navigateUp()
        })
    }

    private fun closeSoftKeyboard(context: Context?, btnSave: Button) {
        val iMm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        iMm.hideSoftInputFromWindow(btnSave.windowToken, 0)
        view?.clearFocus()
    }
}