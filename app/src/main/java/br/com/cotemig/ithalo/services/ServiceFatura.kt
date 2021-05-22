package br.com.cotemig.ithalo.services

import br.com.cotemig.ithalo.models.Fatura
import retrofit2.Call
import retrofit2.http.GET

interface ServiceFatura {
    @GET("fatura")
    fun getFatura() : Call<Fatura>
}