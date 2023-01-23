package com.example.bmicalculator

import android.app.Activity
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat.recreate
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val weightText = findViewById<EditText>(R.id.weighttext)
        val heightText = findViewById<EditText>(R.id.heighttext)
        val calculateButton = findViewById<Button>(R.id.button)

        calculateButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validateinput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2digit = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2digit)


            }
        }
    }
    private fun validateinput(weight:String?,height:String?):Boolean{
        return when {
            weight.isNullOrEmpty() ->{
                Toast.makeText(this, "Weight is empty",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() ->{
                Toast.makeText(this, "Height is empty",Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true

            }            }
    }


    private fun displayResult(bmi:Float){
        val result = findViewById<TextView>(R.id.tvresult)
        val message = findViewById<TextView>(R.id.tvmessage)
//        val range = findViewById<TextView>(R.id.tvrange)
        var cardview = findViewById<CardView>(R.id.cvresult)
        val underWeight = findViewById<TextView>(R.id.textunderweight)
        val healthy = findViewById<TextView>(R.id.texthealthy)
        val overWeight = findViewById<TextView>(R.id.textoverweight)
        val obesity = findViewById<TextView>(R.id.textobesity)

        result.text = bmi.toString()

        var messageText = ""
//        var rangeText = ""
        var color = 0


        when{
            bmi<18.50 ->{
                messageText = "(UNDERWEIGHT)"
                color = R.color.under_weight
//                rangeText = "(Underweight = BMI below 18.5)"
                underWeight.setBackgroundColor(Color.parseColor("#FFF071"))

            }
            bmi in 18.5..24.9 ->{
                messageText = "(HEALTHY)"
                color = R.color.normal
//                rangeText = "(Normal = BMI between 18.5 - 24.9)"
                healthy.setBackgroundColor(Color.parseColor("#3CA340"))


            }
            bmi in 25.00..29.99 ->{
                messageText = "(OVERWEIGHT)"
                color = R.color.over_weight
//                rangeText = "(Overweight = BMI between 25 - 29.9)"
                overWeight.setBackgroundColor(Color.parseColor("#CC6760"))

            }
            bmi > 29.99 ->{
                messageText = "(OBESE)"
                color = R.color.obese
//                rangeText = "(Obesity = BMI between 30 - 39.9)"
                obesity.setBackgroundColor(Color.parseColor("#E1473B"))

            }
        }
//        message.setTextColor(ContextCompat.getColor(this,color))
        result.setBackgroundColor(ContextCompat.getColor(this,color))
        cardview.setBackgroundColor(ContextCompat.getColor(this,color))

//        obesity.setTextColor(ContextCompat.getColor(this,color))
//        healthy.setTextColor(ContextCompat.getColor(this,color))
//        overWeight.setTextColor(ContextCompat.getColor(this,color))
//        underWeight.setTextColor(ContextCompat.getColor(this,color))

        message.text = messageText
//        range.text = rangeText

    }
}