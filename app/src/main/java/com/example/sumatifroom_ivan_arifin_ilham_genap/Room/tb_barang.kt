package com.example.sumatifroom_ivan_arifin_ilham_genap.Room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class tb_barang(
    @PrimaryKey
    val id: Int,
    val namabarang: String,
    val harga:Int,
    val QTY : Int

)
