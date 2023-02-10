package com.example.sumatifroom_ivan_arifin_ilham_genap

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sumatifroom_ivan_arifin_ilham_genap.Room.Constant
import com.example.sumatifroom_ivan_arifin_ilham_genap.Room.barangDAO
import com.example.sumatifroom_ivan_arifin_ilham_genap.Room.codepelita
import com.example.sumatifroom_ivan_arifin_ilham_genap.Room.tb_barang
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    val db by lazy { codepelita(this) }
    lateinit var AdapterActivity: AdapterActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListener()
        setupRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        loadbarang()

    }

    fun loadbarang(){
        CoroutineScope(Dispatchers.IO).launch {
            val barangs = db.tbbarDao().tampilsemua()
            Log.d("MainActivity","dbResponse: $barangs")
            withContext(Dispatchers.Main){
                AdapterActivity.setData(barangs)
            }
        }
    }

   fun setupListener(){
        btninput.setOnClickListener {
            intentEdit(0, Constant.TYPE_CREATE)
        }
    }

    fun intentEdit(barangid:Int, intentType:Int){
        startActivity(
            Intent(applicationContext,EditActivity::class.java)
                .putExtra("intent_id",barangid)
                .putExtra("intent_type",intentType)
        )
    }

    fun setupRecyclerView(){
         AdapterActivity = AdapterActivity(arrayListOf(),object : AdapterActivity.OnAdapterListener{
            override fun onClick(barang: tb_barang) {
                intentEdit(barang.id,Constant.TYPE_READ)
            }

            override fun onUpdate(barang: tb_barang) {
                intentEdit(barang.id,Constant.TYPE_UPDATE)

            }

            override fun onDelete(barang: tb_barang) {
                    deleteAlert(barang)

            }
        })
        //id recyclerview
        Listdata.apply{
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = AdapterActivity
        }
    }
    private fun deleteAlert(tbbar:tb_barang){
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi Hapus")
            setMessage("Yakin hapus ${tbbar.namabarang}?")
            setNegativeButton("Batal"){dialogInterface,i ->
                dialogInterface.dismiss()
            }
            setPositiveButton("Hapus"){dialogInterface,i ->
                CoroutineScope(Dispatchers.IO).launch {
                    db.tbbarDao().deltb_barang(tbbar)
                    dialogInterface.dismiss()
                    loadbarang()
                }
            }
        }
        dialog.show()
    }
}