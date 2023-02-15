package cm.chettas.university.model.remote

import cm.chettas.university.model.data.UniversitiesResponseItem
import retrofit2.Response

interface ApiHelper {
    suspend fun getUniversities(): Response<List<UniversitiesResponseItem>>
}