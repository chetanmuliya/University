package cm.chettas.university.repository

import cm.chettas.university.model.remote.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
) {
    suspend fun getUniversities() = apiHelper.getUniversities()
}