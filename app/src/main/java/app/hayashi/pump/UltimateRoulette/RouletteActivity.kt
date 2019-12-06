package app.hayashi.pump.UltimateRoulette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_roulette.*
import kotlin.concurrent.timer

class RouletteActivity : AppCompatActivity() {

    var timerCount: Int = 0
    var timerCount2: Int = 1
    val handler: Handler = Handler()
    var isStart = false
    var p :Int = 50
    var p2 : Int = 10
    var stopText: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_roulette)
        val layout = findViewById<LinearLayout>(R.id.layout)
        val intent2: Intent = Intent(this, ResultActivity::class.java)
        val list = intent.getStringArrayListExtra("result")
        println(list)
        textView.text = intent.getStringExtra("title")
        for (i in 0..list.size-1) {
            //val text = "for ${i}"
            val mylayout = layoutInflater.inflate(R.layout.list_of_roulette, null)
            // switchのテキストを変更する
            val textView = mylayout.findViewById<TextView>(R.id.textView)
            textView.text = list[i]
            layout.addView(mylayout)
        }




        //ここからルーレットアニメーション
        var roulette = timer(period = 10) {

        }
        startButton.setOnClickListener {
            if (!isStart) {
                roulette = timer(period = 100) {
                    handler.post {
                        timerCount++
                        timerText.text = timerCount.toString()
                        for (i in 0..list.size - 1) {
                            val smallLayout = layout.getChildAt(i)
                            val array = smallLayout.findViewById<ImageView>(R.id.deleteButton2)
                            array.visibility = View.INVISIBLE
                        }
                        val smallLayout = layout.getChildAt(timerCount % list.size)
                        val array = smallLayout.findViewById<ImageView>(R.id.deleteButton2)
                        array.visibility = View.VISIBLE
                    }
                }
                startButton.text = "Stop"
                isStart = true
            } else {
                startButton.visibility = View.INVISIBLE
                timerCount2 = timerCount
                roulette.cancel()
                roulette = timer(period = 1) {
                    handler.post {
                        timerText.text = (stopText + 1).toString()
                    }
                    if (timerCount2 % p == 0) {

                        handler.post {
                            timerCount++
                            for (i in 0..list.size - 1) {
                                val smallLayout = layout.getChildAt(i)
                                val array = smallLayout.findViewById<ImageView>(R.id.deleteButton2)
                                array.visibility = View.INVISIBLE
                            }
                            val smallLayout = layout.getChildAt(timerCount % list.size)
                            val array = smallLayout.findViewById<ImageView>(R.id.deleteButton2)
                            array.visibility = View.VISIBLE
                            if (timerCount % list.size == stopText && timerCount2 > 4000) {
                                roulette.cancel()

                                val selectedLayout = layout.getChildAt(stopText)
                                val array1 = smallLayout.findViewById<TextView>(R.id.textView)
                                intent2.putExtra("resultI", array1.text.toString())
                                roulette = timer(period = 1000, initialDelay = 2000) {
                                    startActivity(intent2)
                                    roulette.cancel()
                                }

                            }
                        }
                        p += p2
                        p2 += 50
                    }
                    timerCount2 += 1

                }

            }
            for(i in 0..list.size-1) {
                val smallLayout = layout.getChildAt(i)
                val array = smallLayout.findViewById<TextView>(R.id.textView)
                array.setOnClickListener{
                    stopText = i
                }
            }
        }
    }
}
