package cm.chettas.university.model.remote

import cm.chettas.university.model.data.UniversitiesResponseItem
import retrofit2.Response
import retrofit2.http.GET

interface UniversityService {

    @GET("/search?country=india")
    suspend fun getUniversities(): Response<List<UniversitiesResponseItem>>

}