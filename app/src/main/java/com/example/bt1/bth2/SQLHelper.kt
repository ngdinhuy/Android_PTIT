package com.example.bt1.bth2

import android.app.job.JobInfo
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlinx.coroutines.Job

class SQLHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_TABLE_JOB)
    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onCreate(db)
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }

    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
    }
    fun getAllJob(): ArrayList<JobModel>{
        var list = ArrayList<JobModel>()
        val sqLiteDatabase = readableDatabase
//        val order = "tinhTrang DESC"
//        val cursor = sqLiteDatabase.query(TABLE_NAME,null,null,null,order,null,null)

        val cursor = sqLiteDatabase.query(TABLE_NAME,null,null,null,null,null,null)
        while (cursor!=null && cursor.moveToNext()){
            val id = cursor.getInt(0)
            val ten = cursor.getString(1)
            val noiDung = cursor.getString(2)
            val ngay= cursor.getString(3)
            val tinhTrang = cursor.getString(4)
            val congTac = cursor.getString(5)
            list.add(JobModel(id,ten, noiDung, ngay, tinhTrang, congTac))
        }
        return list
    }

    fun addJob(job: JobModel){
        val sqLiteDatabase = writableDatabase
        val values = ContentValues()
        values.put(id,job.id)
        values.put(ten,job.ten)
        values.put(noiDung,job.noiDung)
        values.put(ngay,job.ngay)
        values.put(tinhTrang,job.tinhTrang)
        values.put(congTac,job.congTac)
        sqLiteDatabase.insert(TABLE_NAME,null,values)
    }

    fun edit(job: JobModel){
        val sqLiteDatabase = writableDatabase
        val values = ContentValues()
        values.put(ten,job.ten)
        values.put(noiDung,job.noiDung)
        values.put(ngay,job.ngay)
        values.put(tinhTrang,job.tinhTrang)
        values.put(congTac,job.congTac)
        val whereClause = "id=?"
        val whereArgs = arrayOf(job.id.toString())
        sqLiteDatabase.update(TABLE_NAME,values,whereClause, whereArgs)
    }

    fun delete(id:Int){
        val sqLiteDatabase = writableDatabase
        val whereClause = "id=?"
        val whereArgs = arrayOf(id.toString())
        sqLiteDatabase.delete(TABLE_NAME,whereClause, whereArgs)
    }

    fun searchByName(s: String): ArrayList<JobModel>{
        val list = ArrayList<JobModel>()
        val whereClause = "ten like ?"
        val whereArgs = arrayOf("%$s%")
        val readDB = readableDatabase
        val cursor = readDB.query(TABLE_NAME, null, whereClause, whereArgs,null, null, null)
//        val cursor = readDB.rawQuery("Select * from ${TABLE_NAME} WHERE ${ten} LIKE '${s}'", null)
        while (cursor!=null && cursor.moveToNext()){
            val id = cursor.getInt(0)
            val ten = cursor.getString(1)
            val noiDung = cursor.getString(2)
            val ngay= cursor.getString(3)
            val tinhTrang = cursor.getString(4)
            val congTac = cursor.getString(5)
            list.add(JobModel(id,ten, noiDung, ngay, tinhTrang, congTac))
        }
        return list
    }
    companion object {
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "BaiThucHanh2.db"
        const val TABLE_NAME = "JobModel"
        const val id = "id"
        const val ten = "ten"
        const val noiDung = "noiDung"
        const val ngay = "ngay"
        const val tinhTrang = "tinhTrang"
        const val congTac = "congTac"
        const val CREATE_TABLE_JOB = "CREATE TABLE ${TABLE_NAME}(" +
                "$id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$ten TEXT, " +
                "$noiDung TEXT, " +
                "$ngay TEXT, " +
                "$tinhTrang TEXT, " +
                "$congTac TEXT)"
    }
}