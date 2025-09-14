package ar.edu.unicen.seminario2025.ddl.data.remote.api
open class ApiResult<out T> {
    data class Success<out T>(val data: T) : ApiResult<T>()
    data class Error(val error: String , val exception: Exception) : ApiResult<Nothing>()

}
