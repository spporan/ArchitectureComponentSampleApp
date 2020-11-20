package com.poran.architecturecomponentsampleapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_UID=0;
@Entity
data class User(
    var id:Int?=null,
    var name:String?=null,
    var email:String?=null,
    var emailVerifiedAt: String?=null,
    var createdAt:String?=null,
    var updatedAt:String?=null
    ){
    @PrimaryKey(autoGenerate = false)
    var uid:Int= CURRENT_UID
}