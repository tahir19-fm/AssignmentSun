package com.hoho.assignmentsun.repository

import com.hoho.assignmentsun.model.SunDataRequestDTO
import com.hoho.assignmentsun.model.SunDetailsResponseDTO
import com.hoho.assignmentsun.model.WeatherDetails
import com.hoho.assignmentsun.networkService.ApiResult
import com.hoho.assignmentsun.networkService.CommonApiService
import com.hoho.assignmentsun.networkService.networkCall
import com.hoho.assignmentsun.utils.DummyData
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: CommonApiService) {

    suspend fun getSunData(requestData: SunDataRequestDTO): ApiResult<SunDetailsResponseDTO> {
        return networkCall(
            api.getSunData(
                requestData.date,
                requestData.lat,
                requestData.lang
            )
        )
    }
    fun getWeatherData():ApiResult<List<WeatherDetails>>{
        return ApiResult.Success(DummyData.weatherReportList)
    }

}