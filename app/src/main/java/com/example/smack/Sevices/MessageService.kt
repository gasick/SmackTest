package com.example.smack.Sevices

import android.content.Context
import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.smack.Controllers.App
import com.example.smack.Model.Channel
import com.example.smack.Utilities.URL_GET_CHANNELS
import org.json.JSONException

object MessageService {
    val channels = ArrayList<Channel>()

    fun getChannels(complete: (Boolean) -> Unit){
        val channelRequest = object : JsonArrayRequest(Method.GET, URL_GET_CHANNELS, null, Response.Listener
        {
                //Получаем ответ от сервера
                response ->
                    try {
                        //Проходимся по массиву и разбираем каждый из объектов в массиве
                        for (x in 0 until response.length()) {
                            val channel  = response.getJSONObject(x)
                            val  name = channel.getString("name")
                            val chanDesc = channel.getString("description")
                            val channelId = channel.getString("_id")
                            //создаем массив в channel
                            val newChannel = Channel(name, chanDesc, channelId)
                            this.channels.add(newChannel)
                        }

                        complete(true)
                    } catch (e: JSONException){
                        Log.d("JSON", "EXC: " + e.localizedMessage)
                        complete(false)
                    }
        }, Response.ErrorListener {
            Log.d("ERROR", "Could not retrieve channgers")
            complete(false)
        }){
            override fun getBodyContentType(): String {
                return "application/json; charset=utf-8"
            }

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers.put("Authorization", "Bearer ${App.prefs.authToken}")
                return headers
            }
        }
        App.prefs.requestQueue.add(channelRequest)
    }

}