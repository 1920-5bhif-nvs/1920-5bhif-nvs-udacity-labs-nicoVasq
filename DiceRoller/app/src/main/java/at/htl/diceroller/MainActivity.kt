package at.htl.diceroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var rollButton: Button = findViewById(R.id.roll_button)

        rollButton.setOnClickListener {
            rollDice()
        }


    }

    private fun rollDice() {

        Toast.makeText(this,"Rolled dice", Toast.LENGTH_SHORT).show()

        val resultText: TextView = findViewById(R.id.result_text)
        var rand = Random.nextInt(6) + 1
        resultText.text = rand.toString();

    }
}
