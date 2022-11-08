package com.example.calculatorapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private var strNumber = StringBuilder()
    private lateinit var numberButtons: Array<Button>
    private lateinit var operatorButton: List<Button>
    private lateinit var resultTv: TextView
    private var operator: Operator = Operator.NONE
    private var isOperatorClicked: Boolean = false
    private var operand1: Double = 0.0
    private lateinit var button9: Button
    private lateinit var button8: Button
    private lateinit var button7: Button
    private lateinit var button6: Button
    private lateinit var button5: Button
    private lateinit var button4: Button
    private lateinit var button3: Button
    private lateinit var button2: Button
    private lateinit var button1: Button
    private lateinit var button0: Button
    private lateinit var clear: Button
    private lateinit var div: Button
    private lateinit var mult: Button
    private lateinit var plus: Button
    private lateinit var minus: Button
    private lateinit var dot: Button
    private lateinit var result: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeComponent()

    }


    private fun initializeComponent() {
        resultTv = findViewById(R.id.resultTv)
        button0 = findViewById(R.id.button0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        dot = findViewById(R.id.dot)
        div = findViewById(R.id.div)
        mult = findViewById(R.id.mult)
        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)
        result = findViewById(R.id.result)
        clear = findViewById(R.id.clear)
        numberButtons = arrayOf(
            button0,
            button1,
            button2,
            button3,
            button4,
            button5,
            button6,
            button7,
            button8,
            button9,
            dot
        )
        operatorButton = listOf(div, mult, plus, minus)
        for (i in numberButtons) {
            i.setOnClickListener { numberButtonClick(i) }
        }
        for (i in operatorButton) {
            i.setOnClickListener { operatorButtonClick(i) }
        }
        result.setOnClickListener { resultClick() }
        clear.setOnClickListener {
            strNumber.clear()
            resultTv.text = strNumber
            isOperatorClicked = false
        }
    }


    private fun resultClick() {
        val operand2 = strNumber.toString().toDouble()
        val result: Double
        when (operator) {
            Operator.ADD -> result = operand1 + operand2
            Operator.SUB -> result = operand1 - operand2
            Operator.MUL -> result = operand1 * operand2
            Operator.DIV -> result = operand1 / operand2
            else -> result = 0.0
        }
        strNumber.clear()
        strNumber.append(result.toString())
        resultTv.text = strNumber
        isOperatorClicked = true

    }

    private fun operatorButtonClick(btn: Button) {
        if (btn.text == "+") operator = Operator.ADD
        else if (btn.text == "-") operator = Operator.SUB
        else if (btn.text == "*") operator = Operator.MUL
        else if (btn.text == "/") operator = Operator.DIV
        else operator = Operator.NONE
        isOperatorClicked = true


    }

    private fun numberButtonClick(btn: Button) {
        if (isOperatorClicked) {
            operand1 = strNumber.toString().toDouble()
            strNumber.clear()
            isOperatorClicked = false
        }
        strNumber.append(btn.text)
        resultTv.text = strNumber

    }

    fun getOptionsActivity(view: View) {
        val intent = Intent(this, OptionsActivity::class.java)
        startActivity(intent)
    }

}