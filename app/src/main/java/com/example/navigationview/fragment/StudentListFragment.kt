package com.example.navigationview.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.navigationview.AppConstant
import com.example.navigationview.R
import com.example.navigationview.databinding.FragmentStudentListBinding
import com.example.navigationview.db.model.Student
import com.example.navigationview.fragment.adapter.StudentListAdapter
import com.example.navigationview.listener.StudentCellClickListener
import com.example.navigationview.viewmodel.StudentViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass.
 * Use the [StudentListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class StudentListFragment : Fragment() {
    //View Binding
    lateinit var binding: FragmentStudentListBinding
    //initialize the viewmodel
    lateinit var studentViewModel: StudentViewModel
    //initialize the Adapter
    lateinit var studentListAdapter: StudentListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_student_list, container, false)
        //observe the fragment lifecyle
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //subscribe the viewmodel to the fragment
        studentViewModel = ViewModelProvider(this).get(StudentViewModel::class.java)
        binding.viewmodel = studentViewModel
        setView()
    }

    private fun setView() {
        studentListAdapter = StudentListAdapter()
        binding.rvStudentList.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.rvStudentList.adapter = studentListAdapter
        binding.rvStudentList.isNestedScrollingEnabled = false
    }

    /**
     * Handle student card click listener
     */
    private val studentCellClickListener = object : StudentCellClickListener {
        override fun itemClickListener(student: Student) {
            //Passing parameter from one fragment to another
            val bundle = Bundle()
            bundle.putString(AppConstant.ID, student.id.toString())
            bundle.putString(AppConstant.IAMFROM, "EDIT")
            findNavController().navigate(R.id.nav_AddStudent, bundle)
        }
    }

    override fun onResume() {
        super.onResume()
        //fetch student list from DB
        studentViewModel.getStudentList()

        //Observe the student list livedata along with fragment/activity lifecycle
        studentViewModel.studentList.observe(viewLifecycleOwner, Observer {
            if(it.isNotEmpty()) {
                binding.tvNoRecord.visibility = View.GONE
                studentListAdapter.updateAdapter(
                    it as ArrayList<Student>,
                    studentCellClickListener
                )
            } else {
                binding.tvNoRecord.visibility = View.VISIBLE
            }
        })
    }
}