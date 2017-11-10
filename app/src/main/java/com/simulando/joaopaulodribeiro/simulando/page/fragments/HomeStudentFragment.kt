package com.simulando.joaopaulodribeiro.simulando.page.fragments

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.simulando.joaopaulodribeiro.simulando.R
import com.simulando.joaopaulodribeiro.simulando.page.adapters.HomeStudentPagerAdapter
import kotlinx.android.synthetic.main.fragment_student_home.*


/**
 * Created by joao.paulo.d.ribeiro on 27/09/2017.
 */

class HomeStudentFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         return inflater.inflate(R.layout.fragment_student_home, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabSimulates: TabLayout.Tab = tab_home.newTab()
        tabSimulates.text = getString(R.string.tab_1_name)

        val tabDisciplines: TabLayout.Tab = tab_home.newTab()
        tabDisciplines.text = getString(R.string.tab_2_name)

        tab_home.addTab(tabSimulates)
        tab_home.addTab(tabDisciplines)
        tab_home.tabGravity = TabLayout.GRAVITY_FILL

        home_scroll_view.isFillViewport = true

        val adapter = HomeStudentPagerAdapter(
                childFragmentManager,
                tab_home.tabCount)

        new_home_student_view_pager.adapter = adapter

        new_home_student_view_pager.addOnPageChangeListener(
                TabLayout.TabLayoutOnPageChangeListener(tab_home))
        tab_home.addOnTabSelectedListener(
                getTabSelectedListener(new_home_student_view_pager))

        simulates_swipe_refresh_layout.setOnRefreshListener {
            refreshItens()
        }
    }

    private fun refreshItens() {
        //TODO: Reload itens

        onItensLoadComplete()
    }

    private fun onItensLoadComplete() {
        //TODO: Update de adapter and notify data set changed

        simulates_swipe_refresh_layout.isRefreshing = false

    }

    private fun getTabSelectedListener(viewPager: ViewPager): TabLayout.OnTabSelectedListener {
        return object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        }
    }
}
