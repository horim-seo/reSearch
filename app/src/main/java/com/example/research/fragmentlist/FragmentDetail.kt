package com.example.research.fragmentlist

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.research.MyApplication
import com.example.research.R
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.activity_toolbar.*


/**
 * The number of pages (wizard steps) to show in this demo.
 */
private const val NUM_PAGES = 5

//class FragmentDetail : AppCompatActivity()  {
class FragmentDetail : FragmentActivity()  {

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private lateinit var viewPager: ViewPager2

    lateinit var datas : ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("onclick:", "onclick==============Fragmentlist")

        setContentView(R.layout.fragment_detail)

        datas = intent.getSerializableExtra("data") as ArrayList<String>

        // Instantiate a ViewPager2 and a PagerAdapter.
        viewPager = findViewById(R.id.pager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(this)
        viewPager.adapter = pagerAdapter
        viewPager.setPageTransformer(ZoomOutPageTransformer())

        //initFragmentSearch()
    }

    override fun onBackPressed() {

        Log.d("viewPager.currentItem:", "viewPager.currentItem==============viewPager.currentItem  " + viewPager.currentItem)

        if (viewPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
            viewPager.currentItem = viewPager.currentItem - 1
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            return when(position){
                        2-> ResearchTwoFragment()
                        3-> ResearchThreeFragment()
                        else-> ResearchOneFragment()
                    }
        }
    }

    /*
    private fun initFragmentSearch(){
        changeResearchFagment(fragmentResearchOne)
    }

    private fun changeResearchFagment( fragment: Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fl_detail_container, fragment)
            .commit()
    }
     */
}