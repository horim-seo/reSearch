package com.example.research.fragmentlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.research.*
import com.facebook.drawee.view.SimpleDraweeView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_toolbar.*
import kotlinx.android.synthetic.main.fragmentlist_main.*
import kotlinx.android.synthetic.main.fragmentlist_main.view.*
import kotlinx.android.synthetic.main.row_research.view.*
import kotlinx.android.synthetic.main.row_research.view.cTextView
import kotlinx.android.synthetic.main.row_research.view.tTextView

class Fragmentlist : AppCompatActivity() {

    lateinit var datas : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("onclick:", "onclick==============Fragmentlist")

        setContentView(R.layout.fragmentlist_main)

        datas = intent.getSerializableExtra("data") as ArrayList<String>

        var title:String = datas.get(0)
        var description:String = datas.get(1)
        var uri:String = datas.get(2)
        var textPosition:String = datas.get(3)
        var position:Int = Integer.parseInt(textPosition)

        Log.d("화면이동완료:", "이동후 선택한 데이터==============title == "+ title)
        Log.d("화면이동완료:", "이동후 선택한 데이터==============description == "+ description)

        val dTitel = findViewById<TextView>(R.id.dTextViewTitle)
        dTitel.text = title

        val descriptText = findViewById<TextView>(R.id.dTextView)
        descriptText.text = description

        val imageDetail = findViewById<SimpleDraweeView>(R.id.dImageView)
        imageDetail.setImageURI(uri)

        //툴바 설정
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        // toolbar.title = "툴바 타이틀"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dButton = findViewById<Button>(R.id.dButton)
        dButton.setOnClickListener { view ->
            var item: ArrayList<String> = ArrayList()

            //item.put("title", title)
            //item.put("description", description)

            item.add(title)
            item.add(textPosition)

            Intent(MyApplication.ApplicationContext(), ResearchOneFragment::class.java).apply {
                putExtra("data", item)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }.run {
                MyApplication.ApplicationContext().startActivity(this) }
        }

    }

    //ToolBar용 메뉴 호출
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    // 툴바 홈 뒤로가기 및 툴바에 달린 메뉴 설정
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}