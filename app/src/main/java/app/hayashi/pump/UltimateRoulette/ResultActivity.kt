package app.hayashi.pump.UltimateRoulette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        val intent3: Intent = Intent(this, MainActivity::class.java)
        val resultId :String = intent.getStringExtra("resultI")
        resultText.text = resultId
        backButton.setOnClickListener {
            startActivity(intent3)
        }
    }
}
