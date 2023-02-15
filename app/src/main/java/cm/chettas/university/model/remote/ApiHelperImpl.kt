package cm.chettas.university.model.remote

import cm.chettas.university.model.data.UniversitiesResponseItem
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: UniversityService
): ApiHelper {

    override suspend fun getUniversities(): Response<List<UniversitiesResponseItem>> = apiService.getUniversities()
}