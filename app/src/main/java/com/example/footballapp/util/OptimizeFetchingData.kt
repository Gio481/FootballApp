package com.example.footballapp.util

import com.example.footballapp.util.data_mapper.ModelMapper
import retrofit2.Response

inline fun <DTO, DOMAIN> apiDataFetcher(
    modelMapper: ModelMapper<DTO, DOMAIN>,
    apiResponse: () -> Response<DTO>,
): Resources<DOMAIN> {
    return try {
        val response = apiResponse.invoke()
        if (response.isSuccessful) {
            Resources.Success(modelMapper.modelMapper(response.body()!!))
        } else {
            Resources.Error(response.message())
        }
    } catch (e: Exception) {
        Resources.Error(e.message!!)
    }
}

inline fun <T> repositoryDataFetcher(
    call: () -> Resources<T>,
    errorAction: (error: String) -> Unit,
): T? {
    return when (val data = call.invoke()) {
        is Resources.Success -> data.data
        is Resources.Error -> {
            errorAction.invoke(data.message!!)
            null
        }
    }
}