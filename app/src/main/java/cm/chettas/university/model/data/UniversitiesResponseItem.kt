package cm.chettas.university.model.data


import com.google.gson.annotations.SerializedName

data class UniversitiesResponseItem(
    val domains: List<String>,
    val country: String,
    @SerializedName("alpha_two_code")
    val alphaTwoCode: String,
    @SerializedName("web_pages")
    val webPages: List<String>,
    @SerializedName("state-province")
    val stateProvince: String,
    val name: String
)