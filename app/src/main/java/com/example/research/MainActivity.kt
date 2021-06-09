package com.example.research

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val fragmentOne by lazy { SelectorOneFragment() }
    private val fragmentTwo by lazy { SelectorTwoFragment() }
    private val fragmentThree by lazy { SelectorThreeFragment() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initNavigationBar()
    }

    private fun initNavigationBar() {

        val bnv_main = findViewById<BottomNavigationView>(R.id.bnv_main)

        bnv_main.run {
            setOnNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.first -> {
                        changeFagment(fragmentOne)
                    }
                    R.id.second -> {
                        changeFagment(fragmentTwo)
                    }
                    R.id.third -> {
                        changeFagment(fragmentThree)
                    }
                }
                true
            }
            selectedItemId = R.id.first
        }
    }

    private fun changeFagment( fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_container, fragment)
            .commit()
    }
}