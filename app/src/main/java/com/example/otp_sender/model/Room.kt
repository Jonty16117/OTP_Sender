package com.example.otp_sender.model

import android.content.Context
import androidx.room.*
import com.example.otp_sender.model.DataModel

@Database(entities = arrayOf(DataModel::class), version = 1, exportSchema = false)
abstract class Database : RoomDatabase() {

    abstract fun OTPDao() : DAO

    companion object {

        @Volatile
        private var INSTANCE: Database? = null

        fun getDataseClient(context: Context) : Database {

            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {

                INSTANCE = Room
                    .databaseBuilder(context, Database::class.java, "DATABASE")
                    .fallbackToDestructiveMigration()
                    .build()

                return INSTANCE!!

            }
        }

    }

}