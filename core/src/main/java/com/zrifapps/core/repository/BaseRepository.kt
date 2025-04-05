package com.zrifapps.core.repository

import com.zrifapps.core.network.NetworkResult
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {
    protected suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>,
    ): NetworkResult<T> {
        return try {
            val response = apiCall()
            val body = response.body()

            if (response.isSuccessful && body != null) {
                NetworkResult.Success(body)
            } else {
                val errorMessage = parseError(response)
                NetworkResult.Error(
                    statusCode = response.code(),
                    message = errorMessage
                )
            }
        } catch (e: HttpException) {
            val errorMessage = try {
                val errorJson = e.response()?.errorBody()?.string()
                JSONObject(errorJson ?: "").optString("message", e.message())
            } catch (_: Exception) {
                e.message()
            }

            NetworkResult.Error(
                statusCode = e.code(),
                message = errorMessage ?: "Http error"
            )
        } catch (e: IOException) {
            NetworkResult.Error(
                statusCode = -1,
                message = "Network error, please check your connection."
            )
        } catch (e: Exception) {
            NetworkResult.Error(
                statusCode = -1,
                message = e.localizedMessage ?: "Unknown error occurred"
            )
        }
    }

    private fun parseError(response: Response<*>): String {
        return try {
            val errorBody = response.errorBody()?.string()
            if (!errorBody.isNullOrEmpty()) {
                val jsonObject = JSONObject(errorBody)
                jsonObject.optString("message", "Unknown error")
            } else {
                "Unknown error"
            }
        } catch (e: Exception) {
            "Failed to parse error response"
        }
    }
}
