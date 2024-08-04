package com.example.ages_in_minutes_calculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var  pickedDate : TextView? = null
    private var  ageInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val datePicker : Button = findViewById<Button>(R.id.datePicker)
        pickedDate = findViewById(R.id.text4)
        ageInMinutes = findViewById(R.id.text6)

        datePicker.setOnClickListener {
            dateDialog()
        }
    }




    private fun dateDialog(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        var dps =DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view, year,month,dayOfMonth->
                Toast.makeText(this," DatePicker Works !! ", Toast.LENGTH_SHORT).show()

                var selectedDate = "$dayOfMonth/${month+1}/$year"
                pickedDate?.text = selectedDate


                val sdf = SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time / 60000
                        val diffInMinutes = currentDateInMinutes - selectedDateInMinutes

                        ageInMinutes?.text = diffInMinutes.toString()
                    }

                }
            },
            year,
            month,
            day
        )
        dps.datePicker.maxDate = System.currentTimeMillis()-86400000
        dps.show()



    }
}