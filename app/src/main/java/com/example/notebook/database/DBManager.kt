package com.example.notebook.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.widget.Toast
import java.nio.ByteOrder

class DBManager(private val context : Context) {

    private val dbName = "notesDB"
    private val tableName = "tblNotes"
    private val colID = "ID"
    private val colTitle = "Title"
    private val colDescription = "Description"
    private val dbVersion = 1

    private val sqlCreateTable =
        "CREATE TABLE IF NOT EXISTS $tableName ($colID INTEGER PRIMARY KEY , $colTitle VARCHAR , $colDescription TEXT);"

    private val db = DataBaseHelper(context)
    private val sqlDB = db.writableDatabase
    inner class DataBaseHelper(private val context2: Context) : SQLiteOpenHelper(context2 , dbName , null , dbVersion){
        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(sqlCreateTable)
            Toast.makeText(context2 , "DB Created" , Toast.LENGTH_SHORT).show()

        }

        override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
            db.execSQL("DROP TABLE IF EXISTS $tableName ;")
        }

    }

    fun insert(values : ContentValues ) : Long = sqlDB.insert(tableName , "" , values)

    fun runQuery(columns : Array<String> , selection : String , selectionArgs :Array<String> , sortOrder: String) : Cursor {
        val db = SQLiteQueryBuilder()
        db.tables = tableName
        return db.query(sqlDB , columns , selection , selectionArgs , null , null , sortOrder)

    }

    fun delete( selection: String , selectionArgs: Array<String>):Int = sqlDB.delete(tableName , selection , selectionArgs)

    fun update( selection: String , selectionArgs: Array<String>):Int = sqlDB.delete(tableName ,  selection , selectionArgs)
}