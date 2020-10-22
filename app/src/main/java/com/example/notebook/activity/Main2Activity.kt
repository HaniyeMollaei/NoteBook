package com.example.notebook.activity

import android.content.ContentValues
import com.example.notebook.R
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.notebook.database.DBManager
import kotlinx.android.synthetic.main.add_page.*
import kotlinx.android.synthetic.main.temp_item_list.*

class Main2Activity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_page)
        var id = -1

        if (intent.extras != null) {
            id = intent.getIntExtra("id", -1)
            title_txt.setText(intent.getStringExtra("title"))
            desc2_txt.setText(intent.getStringExtra("desc"))
        }

        done.setOnClickListener {
            if (title_txt.text == null || desc2_txt.text == null) {
                Toast.makeText(this, "لطفا هر دو فیلد را پر کنید", Toast.LENGTH_SHORT).show()
            } else {

                val db = DBManager(this)
                val values = ContentValues()
                values.put("Title", title_txt.text.toString())
                values.put("Description", desc2_txt.text.toString())

                if (id == -1) {            //new note
                    val result = db.insert(values)

                    if (result > 0) {
                        Toast.makeText(this, "یادداشت ذخیره شد", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "مشکلی در ذخیره سازی به وجود آمد", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {

                    val result = db.update(values, "ID=?", arrayOf(id.toString()))

                    if (result > 0) {
                        Toast.makeText(this, "یادداشت به روز رسانی شد", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "مشکل در بروزرسانی یادداشت", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }

    }
}