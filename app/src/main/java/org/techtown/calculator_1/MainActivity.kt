package org.techtown.calculator_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import org.techtown.calculator_1.databinding.ActivityMainBinding
import java.lang.Math.round
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private var tvResult : TextView? = null
    private var tvInput : TextView? = null
    private var tvResultCheck = false
    private var tvInputCheck = false
    private var FirstNum : Double = 0.0
    private var SecondNum : Double = 0.0
    private var ResultNum : Double = 0.0
    private var ArithOper : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        tvResult = binding.tvResult
        tvInput = binding.tvInput;

    }
    fun NumClick(view: View) {
        if (!tvResultCheck) {
            tvResult?.text = ""
        }
        tvResult?.append((view as Button).text)
        tvResultCheck = true
        var str = tvResult?.text.toString()
        SecondNum = str.toDouble()
    }
    fun BackClick(view : View) {
        var str = tvResult?.text.toString()
        if(str.isNotEmpty()) {
            str = str.substring(0, str.length - 1)
            tvResult?.text = str
        }
    }
    fun ClearClick(view: View) {
        tvResult?.text = ""
        tvResultCheck = false
        SecondNum = 0.0
    }
    fun ACClick(view: View) {
        tvResult?.text = ""
        tvResultCheck = false
        SecondNum = 0.0
        tvInput?.text = ""
        tvInputCheck = false
        FirstNum = 0.0
        ArithOper = ""
    }

    fun DotClick(view: View) {
        var dotCheck = false
        tvResult?.text?.let {
            if(it.contains(".")) {
                dotCheck = true
            }
        }

        if(!tvResultCheck) {
            tvResult?.text = "0."
            tvResultCheck = true
        }
        else if (!dotCheck) {
            tvResult?.append(".")
        }
    }

    fun OperClick(view: View){
        val btn = (view as Button).text

        if(!tvInputCheck){
            tvInput?.text = if(SecondNum % 1.0 == 0.0) "${SecondNum.toLong()} $btn" else "$SecondNum $btn"
            FirstNum = SecondNum
            tvInputCheck = true
            tvResultCheck = false
        }else{
            if(!tvResultCheck){
                tvInput?.text = if(FirstNum % 1.0 == 0.0) "${FirstNum.toLong()} $btn" else "$FirstNum $btn"
            }else{
                ResultNum = when(ArithOper){
                    "+" -> FirstNum + SecondNum
                    "-" -> FirstNum - SecondNum
                    "x" -> FirstNum * SecondNum
                    else -> FirstNum / SecondNum
                }
                ResultNum = ((ResultNum * 1000000000).roundToInt() / 1000000000).toDouble()
                tvInput?.text = if(ResultNum % 1.0 == 0.0) "${ResultNum.toLong()} $btn" else "$ResultNum $btn"
                tvResultCheck = false
                FirstNum = ResultNum
            }
        }
        ArithOper = btn.toString()
    }

    fun EqualClick(view: View){
        tvInput?.text = ""
        tvInput?.append(if(FirstNum % 1.0 == 0.0) "${FirstNum.toLong()} $ArithOper" else "${FirstNum} $ArithOper")
        tvInput?.append(if(SecondNum % 1.0 == 0.0) " ${SecondNum.toLong()} =" else " ${SecondNum} =")
        ResultNum = when(ArithOper){
            "+" -> FirstNum + SecondNum
            "-" -> FirstNum - SecondNum
            "x" -> FirstNum * SecondNum
            else -> FirstNum / SecondNum
        }
        ResultNum = ((ResultNum * 1000000000).roundToInt() / 1000000000).toDouble()
        tvResult?.text = if(ResultNum % 1.0 == 0.0) "${ResultNum.toLong()}" else "${ResultNum}"
        FirstNum = ResultNum
        tvResultCheck = false
    }

}