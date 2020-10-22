package com.example.notebook.activity

import android.content.Intent
import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.notebook.R
import com.example.notebook.adapter.MyAdapter
import com.example.notebook.database.DBManager
import com.example.notebook.dataclass.MyDataClass
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData("%")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu , menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item?.itemId == R.id.add_menu){

            startActivity(Intent(this , Main2Activity::class.java ))
        }
        return super.onOptionsItemSelected(item)
    }
    fun loadData(title: String){
        val list = arrayListOf<MyDataClass>()
        val DB = DBManager(this)
        val column = arrayOf("ID" , "Title" , "Description")
        val selectionArgs = arrayOf(title)
        val result = DB.runQuery(column ,"Title like ?" , selectionArgs , "ID")

        if (result.moveToFirst()){ //agar avalin meqdari vojoud dashte bashe in if ejraa mishe age na ejra nemikone
            do{
                val id = result.getInt(result.getColumnIndex("ID"))
                val title2 = result.getString(result.getColumnIndex("Title"))
                val desc = result.getString(result.getColumnIndex("Description"))
                list.add(MyDataClass(id , title2 , desc))
            }while (result.moveToNext())
        }

        val adapterTest =  MyAdapter(list)
        note_list.adapter = adapterTest
    }
}