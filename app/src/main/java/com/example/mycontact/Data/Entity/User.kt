package com.example.mycontact.Data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0,
    var name:String,
    var number:String
)