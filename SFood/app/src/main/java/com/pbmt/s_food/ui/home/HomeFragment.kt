package com.pbmt.s_food.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asksira.loopingviewpager.LoopingViewPager
import com.pbmt.s_food.R
import com.pbmt.s_food.adapter.MyDealsAdapter
import com.pbmt.s_food.adapter.MyPopularCategoriesAdapter
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    var recyclerView:RecyclerView?=null
    var viewPager:LoopingViewPager?=null

    var layoutAnimationController:LayoutAnimationController?=null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        initView(root)
        homeViewModel.popularList.observe(viewLifecycleOwner, Observer {
            val listData=it
            val adapter=MyPopularCategoriesAdapter(requireContext(),listData)
            recyclerView!!.adapter=adapter
            recyclerView!!.layoutAnimation=layoutAnimationController
        })
        homeViewModel.bestDealList.observe(viewLifecycleOwner, Observer {
            val adapter=MyDealsAdapter(requireContext(),it,false)
            viewPager!!.adapter=adapter
        })
        return root
    }

    private fun initView(root:View) {
        layoutAnimationController=AnimationUtils.loadLayoutAnimation(context,R.anim.layout_item_from_left)
        viewPager=root.findViewById(R.id.viewpager)as LoopingViewPager
        recyclerView =root.findViewById(R.id.recycler_popular)
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager= LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
    }

    override fun onResume() {
        super.onResume()
        viewPager!!.resumeAutoScroll()
    }

    override fun onPause() {
        viewPager!!.pauseAutoScroll()
        super.onPause()
    }
}