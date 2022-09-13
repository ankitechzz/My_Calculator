package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? = null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    // view the button i have clicked
    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    // for on click display numerics
    fun onClear(view: View) {
        tvInput?.text = ""
    }

    // for decimal point
    fun onDecimalPoint(view: View) {
        if (lastNumeric && !lastDot) {
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }

    }
//checking if we used a operator than we cannot use other time , for caculation it should end by a number not by any oprator, let is used for nullabe and it is a lambda
    fun onOperator(view: View){
        tvInput?.text?.let {

            if (lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
        }

    }

    //result after clicking equal button
    fun onEqual(view : View){
        if(lastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            // using this to not crash
            try{
                if(tvValue.startsWith("-")){
             prefix = "-"
             tvValue = tvValue.substring(1)
            }
            if (tvValue.contains("-")){
                val splitValue = tvValue.split("-")

                var one = splitValue[0]
                var two = splitValue[1]

                if (prefix.isNotEmpty()){
                    one = prefix + one
                }
                tvInput?.text = removeZeroAferDot((one.toDouble() - two.toDouble()).toString())
            }


                else if (tvValue.contains("+")){
                val splitValue = tvValue.split("+")

                var one = splitValue[0]
                var two = splitValue[1]

                if (prefix.isNotEmpty()){
                    one = prefix + one
                }
                tvInput?.text = removeZeroAferDot((one.toDouble() + two.toDouble()).toString())
            }


                else if (tvValue.contains("/")){
                val splitValue = tvValue.split("/")

                var one = splitValue[0]
                var two = splitValue[1]

                if (prefix.isNotEmpty()){
                    one = prefix + one
                }
                tvInput?.text = removeZeroAferDot((one.toDouble() / two.toDouble()).toString())
            }


                else if (tvValue.contains("*")){
                val splitValue = tvValue.split("*")

                var one = splitValue[0]
                var two = splitValue[1]

                if (prefix.isNotEmpty()){
                    one = prefix + one
                }
                tvInput?.text = removeZeroAferDot((one.toDouble() * two.toDouble()).toString())
            }



            }
            catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun removeZeroAferDot(result: String) : String{
        var value = result
        if (result.contains(".0")){
            value = result.substring(0,result.length-2)
        }
      return value
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
                       value.startsWith("/")
                    || value.startsWith("*")
                    || value.startsWith("+")
                    || value.startsWith("-")
        }
    }
}