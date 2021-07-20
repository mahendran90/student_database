package com.example.navigationview.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationview.R
import com.example.navigationview.databinding.RowItemStudentBinding
import com.example.navigationview.db.model.Student
import com.example.navigationview.listener.StudentCellClickListener
import kotlinx.android.synthetic.main.row_item_student.view.tvAddress
import kotlinx.android.synthetic.main.row_item_student.view.tvEmail
import kotlinx.android.synthetic.main.row_item_student.view.tvName
import kotlinx.android.synthetic.main.row_item_student.view.tvPhone

class StudentListAdapter : RecyclerView.Adapter<StudentListAdapter.StudentListHolder>() {
    var studentList :List<Student> = ArrayList()
    var studentClicklistener: StudentCellClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentListHolder {
       val binding : RowItemStudentBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.row_item_student, parent, false)
       return StudentListHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentListHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    fun updateAdapter(listItem: ArrayList<Student>, listener: StudentCellClickListener) {
        this.studentList = listItem
        this.studentClicklistener = listener
        notifyDataSetChanged()
    }

    inner class StudentListHolder(itemView: RowItemStudentBinding) :
        RecyclerView.ViewHolder(itemView.root){
        fun onBind(position: Int) {
            val row = studentList[position]
            itemView.tvName.text = "Name: ${row.name}"
            itemView.tvEmail.text = "Email: ${row.email}"
            itemView.tvAddress.text = "Info: ${row.address}"
            itemView.tvPhone.text = "Phone: ${row.phone}"
            itemView.setOnClickListener{
                studentClicklistener?.itemClickListener(row)
            }
        }
    }
}