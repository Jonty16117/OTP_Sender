package com.example.otp_sender.datastuff

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors


@Database(entities = [ContactEntry::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun contactEntriesTableDao(): ContactEntriesTableDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: HistoryDatabase? = null

        fun getDatabaseInstance(context: Context): HistoryDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context,
                        HistoryDatabase::class.java, "contacts-history-db")
                        // prepopulate the database after onCreate was called
                        .addCallback(object : Callback() {

                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)

                                // insert the data on the IO Thread
                                ioThread {
                                    getDatabaseInstance(context)
                                            .contactEntriesTableDao()
                                            .insertContact(
                                                    ContactEntry(
                                                            "Manpreet",
                                                            "Sundi",
                                                            "9041716117",
                                                            "676523",
                                                            675745
                                                    )
                                            )
                                }
                            }
                        }).build()

        private fun ioThread(f : () -> Unit) {
            Executors.newSingleThreadExecutor().execute(f)
        }
    }
}

