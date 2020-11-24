package com.poran.architecturecomponentsampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.poran.architecturecomponentsampleapp.data.db.entities.CURRENT_UID
import com.poran.architecturecomponentsampleapp.data.db.entities.User


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend  fun insert(user:User):Long

    @Query("SELECT * FROM user WHERE uid= $CURRENT_UID")
    fun getUSer():LiveData<User>
}