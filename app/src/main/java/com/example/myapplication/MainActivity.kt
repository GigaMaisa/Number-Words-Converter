package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val convertButton = findViewById<Button>(R.id.convertButton)
        val userInput = findViewById<EditText>(R.id.userInput)
        val result = findViewById<TextView>(R.id.numberInWords)

            convertButton.setOnClickListener {
                try {
                    val inputText = userInput.text.toString()
                    inputText.toInt()
                } catch (e: NumberFormatException) {
                    Toast.makeText(this, "Invalid input. Please enter a valid integer.", Toast.LENGTH_SHORT).show()
                    userInput.text.clear()
                }

                result.text = convert(userInput.text.toString())
                userInput.text.clear()
            }
        }

    private fun convert(input: String): String {
        try {
            val number = input.toInt()

            if (number !in 1..1000) {
                Toast.makeText(this, "Invalid input. Please enter a valid integer.", Toast.LENGTH_SHORT).show()
                return "ENTER VALID NUMBER"
            }

            val map = mapOf(
                1 to "ერთი", 2 to "ორი", 3 to "სამი", 4 to "ოთხი",
                5 to "ხუთი", 6 to "ექვსი", 7 to "შვიდი", 8 to "რვა", 9 to "ცხრა", 10 to "ათი",
                11 to "თერთმეტი", 12 to "თორმეტი", 13 to "ცამეტი", 14 to "თოთხმეტი",
                15 to "თხუთმეტი", 16 to "თექვსმეტი", 17 to "ჩვიდმეტი", 18 to "თვრამეტი",
                19 to "ცხრამეტი", 20 to "ოც", 40 to "ორმოც", 60 to "სამოც", 80 to "ოთხმოც", 100 to "ას")

            val hundredsInNumb: Int = number / 100
            val reminderOfHundredsInNumber: Int = number % 100
            val dozen: Int = (reminderOfHundredsInNumber / 20) * 20
            val reminderFromDozen: Int = reminderOfHundredsInNumber % 20
            val stringBuilder = StringBuilder()

            if (number == 100) return "ასი"
            if (number == 1000) return "ათასი"

            if (number % 100 == 0) {
                if (hundredsInNumb > 7) {
                    return stringBuilder.insert(0, "${map[hundredsInNumb]}${map[100]}ი").toString()
                }
                return stringBuilder.insert(0, "${map[hundredsInNumb]!!.slice(0 until map[hundredsInNumb]!!.length-1)}${map[100]}ი").toString()
            }

            if (reminderFromDozen == 0) {
                stringBuilder.append("ი")
            } else {
                stringBuilder.append(map[reminderOfHundredsInNumber - dozen])
            }

            if (reminderFromDozen == 0) {
                stringBuilder.insert(0, "${map[dozen]}")
            } else if (dozen != 0) {
                stringBuilder.insert(0, "${map[dozen]}და")
            }

            if (number > 200) {
                if (hundredsInNumb in 2..7) {
                    stringBuilder.insert(0, "${map[hundredsInNumb]!!.slice(0 until map[hundredsInNumb]!!.length-1)}${map[100]} ")
                } else if (hundredsInNumb > 7) {
                    stringBuilder.insert(0, "${map[hundredsInNumb]}${map[100]} ")
                }
            } else if (number in 101..199) {
                stringBuilder.insert(0, "${map[100]} ")
            }

            return stringBuilder.toString()
        } catch (e: Exception) {
            return "ENTER DIGITS"
        }
    }
}
