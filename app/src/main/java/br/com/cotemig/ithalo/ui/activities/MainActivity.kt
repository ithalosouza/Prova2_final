package br.com.cotemig.ithalo.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.cotemig.ithalo.R
import br.com.cotemig.ithalo.models.Fatura
import br.com.cotemig.ithalo.models.ListaDespesa
import br.com.cotemig.ithalo.services.RetrofitInitializer
import br.com.cotemig.ithalo.ui.adapters.FaturaAdapter
import retrofit2.Call
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getFatura()


    }

    fun getFatura() {

        var s = RetrofitInitializer().serviceFatura()
        var call = s.getFatura()

        var limiteDisponivel = findViewById<TextView>(R.id.limiteDisponivel)
        var valor = findViewById<TextView>(R.id.valorFatura)

        call.enqueue(object : retrofit2.Callback<Fatura>{

            override fun onResponse(call: Call<Fatura>, response: Response<Fatura>) {
                if (response.code() == 200) {
                    response.body()?.let {
                        mostraFatura(it.despesa)

                        valor.text = "R$ " + String.format(Locale.GERMAN, "%,.2f",it.valor.toString().toDouble())
                        limiteDisponivel.text = "Disponível R$ " + String.format(Locale.GERMAN, "%,.2f",it.limiteDisponivel.toString().toDouble())

                    }
                }
            }

            override fun onFailure(call: Call<Fatura>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Ops", Toast.LENGTH_LONG).show()
            }
        })


    }

    fun mostraFatura(list: List<ListaDespesa>) {

        var recyclerViewFatura = findViewById<RecyclerView>(R.id.recyclerViewDespesa)
        recyclerViewFatura.adapter = FaturaAdapter(this, list)
        recyclerViewFatura.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}