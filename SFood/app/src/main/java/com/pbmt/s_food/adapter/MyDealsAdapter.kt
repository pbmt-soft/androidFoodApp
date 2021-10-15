package com.pbmt.s_food.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.bumptech.glide.Glide
import com.pbmt.s_food.R
import com.pbmt.s_food.eventbus.BestDealItemClick
import com.pbmt.s_food.model.BestDealModel
import org.greenrobot.eventbus.EventBus

class MyDealsAdapter (context: Context,itemList:List<BestDealModel>?,
isInfinite:Boolean):LoopingPagerAdapter<BestDealModel>(context, itemList!!, isInfinite) {
    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        val imageView=convertView.findViewById<ImageView>(R.id.img_best_deal)
        val textView=convertView.findViewById<TextView>(R.id.txt_best_deal)

        Glide.with(context).load(itemList!![listPosition].image).into(imageView)
        textView.text=itemList!![listPosition].name

        convertView.setOnClickListener {
            EventBus.getDefault().postSticky(BestDealItemClick(itemList!![listPosition]))
        }
    }

    override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
        return LayoutInflater.from(context).inflate(R.layout.layout_best_deals_item,container,false)
    }
}