package ar.edu.unicen.seminario2025.utils

import ar.edu.unicen.seminario2025.ddl.data.remote.api.ApiResult
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(
    apiCall: suspend () -> T
): ApiResult<T> {
    return try {
        ApiResult.Success(apiCall())
    } catch (e: IOException) {
        ApiResult.Error("Error de red, revisá tu conexió",e)
    } catch (e: HttpException) {
        ApiResult.Error("Error del servidor: ${e.code()}", e)
    } catch (e: Exception) {
        ApiResult.Error("Error inesperado", e)
    }
}
