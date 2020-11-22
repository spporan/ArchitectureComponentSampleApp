package com.poran.architecturecomponentsampleapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.poran.architecturecomponentsampleapp.data.db.entities.Quote
import com.poran.architecturecomponentsampleapp.data.db.entities.User

@Database(
    entities =[User::class,Quote::class],
    version = 1
)
abstract class AppDatabase:RoomDatabase() {
    abstract fun userDao():UserDao
    abstract fun quoteDao():QuoteDao

    companion object{
        private var instance:AppDatabase?=null
        private val Lock=Any()
        operator fun invoke(context:Context)= instance?: synchronized(Lock){
            instance?:buildDatabase(context).also {
                instance=it
            }
        }

        private fun buildDatabase(context: Context)=Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "MyDatabase"
        ).build()
    }
}