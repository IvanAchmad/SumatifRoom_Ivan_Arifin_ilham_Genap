package com.example.sumatifroom_ivan_arifin_ilham_genap.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [tb_barang::class],version = 1)
abstract class codepelita: RoomDatabase() {
    abstract fun tbbarDao() : barangDAO

    companion object{


        @Volatile private var instance : codepelita?= null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: builDatabase(context).also{
                instance = it
            }
        }
        private fun builDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
           codepelita::class.java,
            "perpustakaan.db"
        ).build()
    }
}