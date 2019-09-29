package com.example.ass7_sec1_603021040_7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ass7_sec1_603021040_7.Employee
import com.example.ass7_sec1_603021040_7.EmployeeAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_dialog_layout.view.*

class MainActivity : AppCompatActivity() {
    val employeeList = arrayListOf<Employee>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        testEmployeeData()
        recycler_view.adapter = EmployeeAdapter(this.employeeList,applicationContext)
        recycler_view.layoutManager = LinearLayoutManager(applicationContext) as RecyclerView.LayoutManager?
        recycler_view.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
    }

    fun addStudent (v: View) {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.add_dialog_layout, null)
        val mBuilder = AlertDialog.Builder(this)
        mBuilder.setView(mDialogView)
        val mAlertDialog = mBuilder.show()

        mDialogView.btnAdd.setOnClickListener {
            var radioGroup: RadioGroup = mDialogView.group
            var  selectedId : Int = radioGroup.checkedRadioButtonId
            val  radioButton : RadioButton = mDialogView.findViewById(selectedId)
            employeeList.add(
                Employee(
                    mDialogView.edit_name.text.toString(),radioButton.text.toString(),
                    mDialogView.edit_email.text.toString(),mDialogView.edit_salary.text.toString().toInt()
                )
            )
            recycler_view.adapter?.notifyDataSetChanged()
            Toast.makeText(
                applicationContext,
                "The employee is added successfully",
                Toast.LENGTH_SHORT
            ).show()
            mAlertDialog.dismiss()

        }
        mDialogView.btnCancel.setOnClickListener() {
            mAlertDialog.dismiss()
        }


    }



    fun testEmployeeData() {
        employeeList.add(Employee("Wisunsaya", "Female", "wisunsaya.se@kkumail.com" ,58000))
        employeeList.add(Employee("Tanyada", "Female", "Tanyada.lee@kkumail.com" ,60000))
    }
}
