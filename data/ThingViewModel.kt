package com.example.nema.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Credentials
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray

class ThingViewModel : ViewModel() {
    private val db = Room.databaseBuilder(
        MainActivity.applicationContext(),
        AppDatabase::class.java, "nema-db"
    ).build()
    private val thingDao = db.thingDao()

    var suggestions by mutableStateOf(emptyList<Thing>())
        private set

    fun searchThings(query: String) {
        viewModelScope.launch {
            suggestions = withContext(Dispatchers.IO) { thingDao.searchThings("%$query%") }
        }
    }

    fun syncDataFromApi() {
        viewModelScope.launch {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://example.com/api/things")
                .header("Authorization", Credentials.basic("user", "password"))
                .build()
            
            withContext(Dispatchers.IO) {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val jsonArray = JSONArray(response.body?.string())
                    val things = mutableListOf<Thing>()
                    for (i in 0 until jsonArray.length()) {
                        val obj = jsonArray.getJSONObject(i)
                        things.add(
                            Thing(
                                name = obj.getString("name"),
                                description = obj.getString("description"),
                                location = obj.getString("location")
                            )
                        )
                    }
                    thingDao.insertThings(things)
                }
            }
        }
    }
}
