package com.ih13a175_19_hokaritaiyo.kadai04

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val textView: TextView = findViewById(R.id.numArea)

        val num0: Button = findViewById(R.id.num0)
        val num1: Button = findViewById(R.id.num1)
        val num2: Button = findViewById(R.id.num2)
        val num3: Button = findViewById(R.id.num3)
        val num4: Button = findViewById(R.id.num4)
        val num5: Button = findViewById(R.id.num5)
        val num6: Button = findViewById(R.id.num6)
        val num7: Button = findViewById(R.id.num7)
        val num8: Button = findViewById(R.id.num8)
        val num9: Button = findViewById(R.id.num9)
        val numdot: Button = findViewById(R.id.numdot)

        val calc1: Button = findViewById(R.id.calc1)
        val calc2: Button = findViewById(R.id.calc2)
        val calc3: Button = findViewById(R.id.calc3)
        val calc4: Button = findViewById(R.id.calc4)
        val calc5: Button = findViewById(R.id.calc5)

        val ac: Button = findViewById(R.id.ac)
        val backspace: Button = findViewById(R.id.backspace)

        var total: String = " "

        fun updateText(value: Any){
            when(value) {
                in 0..9 -> total += value
                ".", "+", "-", "×", "÷" -> {
                    if (total[total.length - 1] in '0'..'9') {
                        total += value
                    }
                }
                "bs" -> total = total.dropLast(1)
                "ac" -> total = ""
                "total" -> {
                    var calc = mutableListOf<String>()
                    var number = "0"
                    var error = ""
                    for (i in 0..(total.length - 1)){
                        when(total[i]) {
                            in '0'..'9', '.' -> number += total[i]
                            '+', '-', '×', '÷' -> {
                                calc.add(number)
                                number = ""
                                calc.add(total[i].toString())
                            }
                        }
                    }
                    calc.add(number)
                    var i = 0
                    while (i < calc.size){
                        when (calc[i]) {
                            "×" -> {
                                calc[i - 1] = (calc[i - 1].toDouble() * calc[i + 1].toDouble()).toString()
                                calc.removeAt(i)
                                calc.removeAt(i)
                                i -= 1
                            }
                            "÷" -> {
                                if (calc[i + 1] == "0"){
                                    error = "ゼロ除算エラー"
                                    break
                                }
                                calc[i - 1] = (calc[i - 1].toDouble() / calc[i + 1].toDouble()).toString()
                                calc.removeAt(i)
                                calc.removeAt(i)
                                i -= 1
                            }
                            else -> i += 1
                        }
                    }
                    i = 0
                    while (i < calc.size){
                        when (calc[i]) {
                            "+" -> {
                                calc[i - 1] = (calc[i - 1].toDouble() + calc[i + 1].toDouble()).toString()
                                calc.removeAt(i)
                                calc.removeAt(i)
                                i -= 1
                            }
                            "-" -> {
                                calc[i - 1] = (calc[i - 1].toDouble() - calc[i + 1].toDouble()).toString()
                                calc.removeAt(i)
                                calc.removeAt(i)
                                i -= 1
                            }
                            else -> i += 1
                        }
                    }
                    if (calc[0].toDouble() % 1.0 == 0.0){
                        total = calc[0].toDouble().toInt().toString()
                    } else {
                        total = calc[0]
                    }
                    if (error != "") {
                        total = error
                    }
                }
            }
            textView.text = total
        }
        num0.setOnClickListener {
            updateText(0)
        }
        num1.setOnClickListener {
            updateText(1)
        }
        num2.setOnClickListener {
            updateText(2)
        }
        num3.setOnClickListener {
            updateText(3)
        }
        num4.setOnClickListener {
            updateText(4)
        }
        num5.setOnClickListener {
            updateText(5)
        }
        num6.setOnClickListener {
            updateText(6)
        }
        num7.setOnClickListener {
            updateText(7)
        }
        num8.setOnClickListener {
            updateText(8)
        }
        num9.setOnClickListener {
            updateText(9)
        }
        numdot.setOnClickListener {
            updateText(".")
        }
        backspace.setOnClickListener {
            updateText("bs")
        }
        ac.setOnClickListener {
            updateText("ac")
        }
        calc1.setOnClickListener{
            updateText("+")
        }
        calc2.setOnClickListener{
            updateText("-")
        }
        calc3.setOnClickListener{
            updateText("×")
        }
        calc4.setOnClickListener{
            updateText("÷")
        }
        calc5.setOnClickListener{
            updateText("total")
        }
    }
}