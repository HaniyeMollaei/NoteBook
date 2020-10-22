package com.example.notebook.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.notebook.R
import com.example.notebook.dataclass.MyDataClass

class MyAdapter(private val data : List<MyDataClass>):BaseAdapter(){

    override fun getCount(): Int = data.count()
    override fun getItem(p0: Int):MyDataClass = data[p0]
    override fun getItemId(p0: Int): Long = 0

    override fun getView(p0: Int, convertView: View?, parent: ViewGroup): View {
        val view : View
        val holder : ViewHolder

        if (convertView == null){
            view = LayoutInflater.from(parent.context).inflate(R.layout.temp_item_list , null)
            holder = ViewHolder()
            holder.title = view.findViewById(R.id.head_txt)
            holder.desc = view.findViewById(R.id.txt)
            view.tag = holder
        }else{

            holder = convertView.tag as ViewHolder
            view = convertView
        }

        val data1 = getItem(p0)
        holder.title?.text = data1.title
        holder.desc?.text = data1.desc
        return view
    }

}

class ViewHolder{
    var title : TextView? = null
    var desc : TextView? = null

}