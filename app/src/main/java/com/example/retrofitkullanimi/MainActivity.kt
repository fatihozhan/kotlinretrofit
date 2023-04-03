package com.example.retrofitkullanimi

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.retrofitkullanimi.ui.theme.RetroFitKullanimiTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RetroFitKullanimiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Sayfa()
                }
            }
        }
    }
}

@Composable
fun Sayfa() {
    LaunchedEffect(key1 = true) {
//    tumKisiler()
//        kisiArama()
//        kisiSil()
//        kisiEkle()
        kisiGuncelle()

    }
}

fun tumKisiler() {
    val kisilerDaoInterface = ApiUtlils.getKisilerDaoInterface()
    kisilerDaoInterface.tumKisiler().enqueue(object : Callback<KisilerCevap> {
        override fun onResponse(call: Call<KisilerCevap>, response: Response<KisilerCevap>) {
            val liste = response.body()!!.kisiler
            for (k in liste) {
                Log.e("Kisi Bilgi", "*********************")
                Log.e("Kisi İD", k.kisi_id.toString())
                Log.e("Kisi Ad", k.kisi_ad)
                Log.e("Kisi Tel", k.kisi_tel)
            }
        }

        override fun onFailure(call: Call<KisilerCevap>, t: Throwable) {}
    })

}

fun kisiArama() {
    val kisilerDaoInterface = ApiUtlils.getKisilerDaoInterface()
    kisilerDaoInterface.kisiAra("a").enqueue(object : Callback<KisilerCevap> {
        override fun onResponse(call: Call<KisilerCevap>, response: Response<KisilerCevap>) {
            val liste = response.body()!!.kisiler

            for (k in liste) {
                Log.e("Arama Sonucu", k.kisi_ad)
            }
        }

        override fun onFailure(call: Call<KisilerCevap>, t: Throwable) {}
    })
}

fun kisiSil() {
    val kisilerDaoInterface = ApiUtlils.getKisilerDaoInterface()
    kisilerDaoInterface.kisiSil(2892).enqueue(object : Callback<CRUDCevap> {
        override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
            var mesaj = response.body()!!.message
            var basari = response.body()!!.success

            Log.e("Kişi Sil", "Başari $basari - Mesaj : $mesaj")
        }

        override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}
    })
}

fun kisiEkle() {
    val kisilerDaoInterface = ApiUtlils.getKisilerDaoInterface()
    kisilerDaoInterface.kisiEkle("Fatih", "055555555555").enqueue(object : Callback<CRUDCevap> {
        override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
            var mesaj = response.body()!!.message
            var basari = response.body()!!.success

            Log.e("Kişi Ekle", "Başari $basari - Mesaj : $mesaj")
        }

        override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}
    })
}

fun kisiGuncelle() {
    val kisilerDaoInterface = ApiUtlils.getKisilerDaoInterface()
    kisilerDaoInterface.kisiGuncelle("Fatihhh", "055555555555", 14674)
        .enqueue(object : Callback<CRUDCevap> {
            override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {
                var mesaj = response.body()!!.message
                var basari = response.body()!!.success

                Log.e("Kişi Ekle", "Başari $basari - Mesaj : $mesaj")
            }

            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}
        })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    RetroFitKullanimiTheme {
        Sayfa()
    }
}