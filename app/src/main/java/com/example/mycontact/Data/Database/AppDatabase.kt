package com.example.mycontact.Data.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mycontact.Data.DAO.UserDao
import com.example.mycontact.Data.Entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun runContact():UserDao

    companion object{
        var instanse : AppDatabase? = null

        fun getInstanse(context:Context):AppDatabase{
            if (instanse == null){
                instanse = Room.databaseBuilder(context,AppDatabase::class.java,"app_db")
                    .allowMainThreadQueries()
                    .build()
            }
            return instanse!!
        }
    }
}