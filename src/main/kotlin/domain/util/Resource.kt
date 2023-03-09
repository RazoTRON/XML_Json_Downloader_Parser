package domain.util

sealed class  Resource<T>(val data: T? = null, val message: String? = null) {
    class Loading<T>(message: String? = null) : Resource<T>(message = message)
    class Error<T>(message: String? = null) : Resource<T>(message = message)
    class Success<T>(data: T? = null, message: String? = null) : Resource<T>(data = data, message = message)
}
