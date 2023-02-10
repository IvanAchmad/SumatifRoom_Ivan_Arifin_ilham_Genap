package com.example.sumatifroom_ivan_arifin_ilham_genap.Room

import androidx.room.*

@Dao
interface barangDAO {
    @Insert
    fun addtb_barang(tb_bar: tb_barang)
    @Update
    fun updatebarang(tb_bar: tb_barang)
    @Delete
    fun deltb_barang(tb_bar: tb_barang)
    @Query("SELECT * FROM tb_barang")
    fun tampilsemua():List<tb_barang>
    @Query("SELECT * FROM tb_barang where id = :nis_id")
    fun tampilsemua(nis_id:Int):List<tb_barang>
}