package com.example.navigationview.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationview.R
import com.example.navigationview.databinding.RowItemStudentBinding
import com.example.navigationview.db.model.Student
import com.example.navigationview.listener.StudentCellClickListener

class StudentListAdapter : RecyclerView.Adapter<StudentListAdapter.StudentListHolder>() {
    var list: List<Student> = ArrayList()
    var listener: StudentCellClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentListHolder {
        val binding: RowItemStudentBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_item_student,
            parent,
            false
        )
        return StudentListHolder(binding)

    }

    override fun onBindViewHolder(holder: StudentListHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateAdapter(
        arrayList: ArrayList<Student>,
        studentCellClickListener: StudentCellClickListener
    ) {
        this.list = arrayList
        this.listener = studentCellClickListener
        notifyDataSetChanged()
    }

    inner class StudentListHolder(val binding: RowItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            binding.data = student
            binding.cvCell.setOnClickListener {
                listener?.itemClickListener(student)
            }
        }
    }
}