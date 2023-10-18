package com.example.phillipinetouristaide

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SQLiteDB(context:Context) : SQLiteOpenHelper(context,DATABASE_NAME, null, DATABASE_VERSION) {


    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "UsersDB.db"
        private const val TABLE_NAME = "Users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_EMAIL TEXT, " +
                "$COLUMN_PASSWORD TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }


    fun insertUser(email: String, password: String): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("email", email)
        contentValues.put("password", password)

        val success = db.insert("Users", null, contentValues)
        if(success == (-1).toLong()){
            return false
        }
        return true
    }

    fun authUserPass(email: String, password: String): Boolean {
        val db = this.writableDatabase
        val selectQuery = "SELECT * FROM Users WHERE email = '$email' and password = '$password'"
        val cursor = db.rawQuery(selectQuery,null)

        if(cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    fun updatePassword(email: String, newPassword: String): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_PASSWORD, newPassword)

        return db.update(TABLE_NAME, values, "$COLUMN_EMAIL = ?", arrayOf(email))
    }

    fun isEmailExists(email: String): Boolean {
        val db = readableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL=?", arrayOf(email))
        val emailExists = cursor.count > 0
        cursor.close()
        return emailExists
    }
}
