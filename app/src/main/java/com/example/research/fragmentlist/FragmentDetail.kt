package com.example.research.fragmentlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.research.MyApplication
import com.example.research.R
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.activity_toolbar.*

class FragmentDetail : AppCompatActivity()  {

    lateinit var datas : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("onclick:", "onclick==============Fragmentlist")

        setContentView(R.layout.fragment_detail)

        datas = intent.getSerializableExtra("data") as ArrayList<String>

    }
}