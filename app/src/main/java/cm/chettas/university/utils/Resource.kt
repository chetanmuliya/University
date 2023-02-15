package cm.chettas.university.utils

sealed class Resource<T>(
    val data:T? = null,
    val message: String? = null
) {
    class Success<T>(data: T? = null): Resource<T>(data)
    class Error<T>(message: String, data: T? = null): Resource<T>(data,message)
    class Loading<T>: Resource<T>()

    fun toData(): T? = if(this is Success) this.data else null
}
