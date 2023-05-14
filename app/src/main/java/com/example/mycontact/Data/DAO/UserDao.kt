package com.example.mycontact.Data.DAO

import androidx.room.*
import com.example.mycontact.Data.Entity.User

@Dao
interface UserDao {

    @Query("select * from User")
    fun getUsers():List<User>

    @Insert
    fun addContact(user: User)

    @Query("select * from User where id = :id")
    fun getUserById(id:Int):User

    @Query("update User set name = :name, number = :number where id = :id ")
    fun updateUser(name:String, number:String,id:Int)

    @Delete
    fun deleteUser(user: User)
}