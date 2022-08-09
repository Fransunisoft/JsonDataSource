package com.test.jsondatasource

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.test.jsondatasource.databinding.ActivityMainBinding
import com.test.jsondatasource.fragments.AllFragment
import com.test.jsondatasource.fragments.CreditFragment
import com.test.jsondatasource.fragments.DebitFragment

const val PAGE_ALL = 0
const val PAGE_CREDIT = 1
const val PAGE_DEBIT = 2
const val PAGE_COUNT = 3

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewPager: ViewPager2
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        initViewPager()

        binding.searchView.doAfterTextChanged { s ->
            Log.d("Check filter","Search Clicked")
//            filter(s);
        }
    }

    fun filter(){

    }

    private fun initViewPager() {
        viewPager = binding.contentDetail.viewpager
        tabLayout = binding.contentDetail.tabs
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return PAGE_COUNT
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    PAGE_ALL -> {
                       Log.d("Check click","All Clicked")
                        AllFragment()
                    }
                    PAGE_CREDIT -> {
                        Log.d("Check click","Credit Clicked")
                        CreditFragment()
                    }
                    PAGE_DEBIT -> {
                        Log.d("Check click","Debit Clicked")
                        DebitFragment()
                    }
                    else -> throw IndexOutOfBoundsException()
                }
            }

        }
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
            Log.d("Tab", tab.text as String)

            tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF3700B3"));

//            if (tab.text == "ALL"){
//                tabLayout.setTabTextColors(Color.parseColor("#ED1C24"), Color.parseColor("#ED1C24"));
//            }
//            if (tab.text == "CREDIT"){
//                tabLayout.setTabTextColors(Color.parseColor("#10E318"), Color.parseColor("#10E318"));
//            }
//            if (tab.text == "DEBIT"){
//                tabLayout.setTabTextColors(Color.parseColor("#FF3700B3"), Color.parseColor("#FF3700B3"));
//            }

        }.attach()
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            PAGE_ALL -> "All"
            PAGE_CREDIT -> "Credit"
            PAGE_DEBIT -> "Debit"
            else -> null
        }
    }

}