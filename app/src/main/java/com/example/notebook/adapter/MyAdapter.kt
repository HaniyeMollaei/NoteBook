package com.example.notebook.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.notebook.R
import com.example.notebook.activity.Main2Activity
import com.example.notebook.activity.Main3Activity
import com.example.notebook.database.DBManager
import com.example.notebook.dataclass.MyDataClass
import kotlinx.android.synthetic.main.temp_item_list.view.*

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

        view.edit.setOnClickListener {
            var intent = Intent(parent.context , Main2Activity::class.java)
            intent.putExtra("id", data1.id)
            intent.putExtra("title", data1.title)
            intent.putExtra("desc", data1.desc)
            parent.context.startActivity(intent)

        }

        view.delete.setOnClickListener {

            val db = DBManager(parent.context)
            db.delete("ID=?" , arrayOf(data1.id.toString()))
            parent.context.startActivity(Intent(parent.context , Main3Activity::class.java))
        }
        return view
    }

}

class ViewHolder{
    var title : TextView? = null
    var desc : TextView? = null

}