package co.edu.udea.compumovil.gr03_20232.lab1.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

const val BASE_URL = "https://countriesnow.space/api/v0.1/"

@JsonClass(generateAdapter = true)
data class CitiesResponse(
    @Json(name = "data") val data: Set<String>
)

data class CountryRequest(
    @Json(name = "country") val country: String
)

interface CountriesnowAPI {
    @POST("countries/cities")
    suspend fun getCities(@Body request: CountryRequest): CitiesResponse
}

suspend fun getCitiesApi(
    country: String,
    citiesLiveData: MutableLiveData<CitiesResponse>
) {
    withContext(Dispatchers.IO) {
        try {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val api = retrofit.create(CountriesnowAPI::class.java)
            val request = CountryRequest(country)
            val response = api.getCities(request)

            if (response != null) {
                if (response.data.isNotEmpty()) {
                    citiesLiveData.postValue(response)
                } else {
                    Log.d("Main", "La respuesta no contiene ciudades.")
                }
            } else {
                Log.e("Main", "La respuesta es nula.")
            }
        } catch (e: Exception) {
            Log.e("Main", "Failed: ${e.message}")
        }
    }
}
