package com.poran.architecturecomponentsampleapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.poran.architecturecomponentsampleapp.data.db.entities.Quote
import com.poran.architecturecomponentsampleapp.data.db.entities.User


@Database(
        entities = [User::class, Quote::class],
        version = 2
)
abstract class AppDatabase:RoomDatabase() {
    abstract fun userDao():UserDao
    abstract fun quoteDao():QuoteDao

    companion object{
        private var instance:AppDatabase?=null
        private val Lock=Any()
        operator fun invoke(context: Context)= instance?: synchronized(Lock){
            instance?:buildDatabase(context).also {
                instance=it
            }
        }
        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                       /* database.execSQL("CREATE TABLE `Fruit` (`id` INTEGER, "
                                + "`name` TEXT, PRIMARY KEY(`id`))")*/
                database.execSQL("CREATE TABLE IF NOT EXISTS `Quote` " +
                        "(`id` INTEGER NOT NULL, " +"`quote` TEXT,"+
                        "`author` TEXT ,"+
                        "`thumbnail` TEXT ,"+
                        "`created_at` TEXT,"+
                        "`updated_at` TEXT,"+
                        "PRIMARY KEY(`id`))")
            }
        }

        private fun buildDatabase(context: Context)=Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "MyDatabase"
        ).addMigrations(MIGRATION_1_2)
                .build()
    }
}