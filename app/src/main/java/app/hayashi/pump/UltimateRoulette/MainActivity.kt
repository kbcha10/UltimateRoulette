package app.hayashi.pump.UltimateRoulette

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val layout = findViewById<LinearLayout>(R.id.layout)
        var sizeOfList :Int = 2
        val intent: Intent = Intent(this, RouletteActivity::class.java)
        //val layout = LinearLayout(this)
        //layout.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        //layout.gravity = Gravity.CENTER
        //layout.orientation = LinearLayout.VERTICAL
        //setContentView(layout)
        for (i in 0..1) {
            //val text = "for ${i}"
            val mylayout = layoutInflater.inflate(R.layout.one_of_list, null)
            // switchのテキストを変更する
            //val switch = mylayout.findViewById<Switch>(R.id.switch1)
            //switch.text = text
            mylayout.findViewById<TextView>(R.id.editText).hint = "選択肢"+(i+1).toString()
            layout.addView(mylayout)
        }
        plusButton.setOnClickListener {
            if(sizeOfList<6) {
                val mylayout = layoutInflater.inflate(R.layout.one_of_list, null)
                mylayout.findViewById<TextView>(R.id.editText).hint = "選択肢"+(sizeOfList+1).toString()
                layout.addView(mylayout)
                sizeOfList++
                if (sizeOfList == 6) plusButton.setImageResource(R.drawable.plus_off)
                for (i in 0..sizeOfList-1) {
                    val smallLayout = layout.getChildAt(i)
                    smallLayout.findViewById<TextView>(R.id.editText).hint = "選択肢"+(i+1).toString()
                }
            }
            for(i in 0..sizeOfList-1) {
                val smallLayout = layout.getChildAt(i)
                val deleteButton = smallLayout.findViewById<ImageView>(R.id.deleteButton)
                deleteButton.setOnClickListener{
                    if(sizeOfList!=1) {
                        layout.removeView(smallLayout)
                        print(layout)
                        sizeOfList--
                        if (sizeOfList == 5) plusButton.setImageResource(R.drawable.plus_on)
                        for (i in 0..sizeOfList - 1) {
                            val smallLayout = layout.getChildAt(i)
                            smallLayout.findViewById<TextView>(R.id.editText).hint =
                                "選択肢" + (i + 1).toString()
                        }
                    }
                }
            }
        }
        disButton.setOnClickListener {
            val list: ArrayList<String> = arrayListOf()
            for (i in 0..sizeOfList-1) {
                val smallLayout = layout.getChildAt(i)
                val editText = smallLayout.findViewById<TextView>(R.id.editText)
                list.add(editText.text.toString())
            }
            println(list)
            intent.putExtra("result", list)

            println(titleText.text.toString())
            intent.putExtra("title", titleText.text.toString())
            startActivity(intent)
        }
        for(i in 0..sizeOfList-1) {
            val smallLayout = layout.getChildAt(i)
            val deleteButton = smallLayout.findViewById<ImageView>(R.id.deleteButton)
            deleteButton.setOnClickListener{
                layout.removeView(smallLayout)
                print(layout)
                sizeOfList--
                if (sizeOfList == 5) plusButton.setImageResource(R.drawable.plus_on)
                for (i in 0..sizeOfList-1) {
                    val smallLayout = layout.getChildAt(i)
                    smallLayout.findViewById<TextView>(R.id.editText).hint = "選択肢"+(i+1).toString()
                }
            }
        }
        fun reloadSampleText(){
            for (i in 0..sizeOfList-1) {
                val smallLayout = layout.getChildAt(i)
                smallLayout.findViewById<TextView>(R.id.editText).hint = "選択肢"+(sizeOfList+1).toString()
            }
        }
        resetButton.setOnClickListener {
            layout.removeAllViews()
            for (i in 0..1) {
                //val text = "for ${i}"
                val mylayout = layoutInflater.inflate(R.layout.one_of_list, null)
                // switchのテキストを変更する
                //val switch = mylayout.findViewById<Switch>(R.id.switch1)
                //switch.text = text
                mylayout.findViewById<TextView>(R.id.editText).hint = "選択肢"+(i+1).toString()
                layout.addView(mylayout)
            }
            sizeOfList = 2
        }

    }


}
