package com.example.otp_sender.view

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.otp_sender.R
import com.google.android.material.tabs.TabLayout

@Suppress("SpellCheckingInspection")
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create the object of Toolbar, ViewPager and
        // TabLayout and use “findViewById()” method
        val tabToolbar = findViewById<Toolbar>(R.id.toolbar)
        val tabViewpager = findViewById<ViewPager>(R.id.tab_viewpager)
        val tablayout = findViewById<TabLayout>(R.id.tab_tablayout)

        setSupportActionBar(tabToolbar)
        setupViewPager(tabViewpager)
        tablayout.setupWithViewPager(tabViewpager)
    }

    private fun setupViewPager(viewpager: ViewPager) {
        var adapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(HomeFragment(), "Home")
        adapter.addFragment(HistoryFragment(), "History")

        viewpager.adapter = adapter
    }

    class ViewPagerAdapter : FragmentPagerAdapter {

        private var fragmentList1: ArrayList<Fragment> = ArrayList()
        private var fragmentTitleList1: ArrayList<String> = ArrayList()

        public constructor(supportFragmentManager: FragmentManager) : super(supportFragmentManager)

        // returns which item is selected from arraylist of fragments.
        override fun getItem(position: Int): Fragment {
            return fragmentList1[position]
        }

        // returns which item is selected from arraylist of titles.
        @Nullable
        override fun getPageTitle(position: Int): CharSequence {
            return fragmentTitleList1[position]
        }

        // returns the number of items present in arraylist.
        override fun getCount(): Int {
            return fragmentList1.size
        }

        // this function adds the fragment and title in 2 separate arraylist.
        fun addFragment(fragment: Fragment, title: String) {
            fragmentList1.add(fragment)
            fragmentTitleList1.add(title)
        }
    }
}
