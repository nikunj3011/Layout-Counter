package com.example.layoutcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.layoutcounter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val countKey = "num"
    private var count:Int= 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            countdownButton.setOnClickListener {sub()}
            countupButton.setOnClickListener { add() }
            toastButton.setOnClickListener { showtoast(it) }
        }
    }

    private fun showtoast(view: View) {
        Toast.makeText(applicationContext,
            "Number: $count",
            Toast.LENGTH_SHORT).show()
    }

    private fun add() {
        count++;
        update()
    }
    private fun sub() {
        count--;
        update()
    }

    private fun update(){
        binding.apply {
            textView?.text = count.toString()
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState?.putInt(countKey,count)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedNum = savedInstanceState.getInt(countKey)
        count = savedNum
        update()
    }
}