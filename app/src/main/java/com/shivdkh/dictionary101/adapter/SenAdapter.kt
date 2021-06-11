package com.shivdkh.dictionary101.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.shivdkh.dictionary101.R

class SenAdapter(private var senList: ArrayList<String>): BaseAdapter() {
    override fun getCount(): Int {
        return senList.size
    }

    override fun getItem(p0: Int): Any {
        return senList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val context= p2?.context
        val inflater: LayoutInflater= context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var rollView: View? = p1
        if (rollView==null){
            rollView= inflater.inflate(R.layout.item_sen,p2,false)
        }

        val tvSingleDef= rollView?.findViewById<TextView>(R.id.tv_single_sen)
        tvSingleDef?.text=senList[p0]

        return rollView!!
    }
}