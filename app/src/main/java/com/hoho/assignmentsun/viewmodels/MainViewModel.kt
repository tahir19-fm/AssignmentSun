package com.hoho.assignmentsun.viewmodels

import android.content.Context
import android.location.Address
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoho.assignmentsun.model.SunDataRequestDTO
import com.hoho.assignmentsun.model.SunDetailsResponseDTO
import com.hoho.assignmentsun.networkService.ApiResult
import com.hoho.assignmentsun.repository.HomeRepository
import com.hoho.assignmentsun.utils.DateUtil
import com.hoho.assignmentsun.utils.LocationRequestHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
    val stateCountryName = mutableStateOf("")
    private val _userCurrentLocation = MutableLiveData<ApiResult<Address>>()
    val userCurrentLocation: MutableLiveData<ApiResult<Address>>
        get() = _userCurrentLocation

    fun getUserLocation(context: Context) {
        _userCurrentLocation.value = ApiResult.Loading
        viewModelScope.launch(Dispatchers.IO) {
            LocationRequestHandler(context = context) { location ->
                _userCurrentLocation.postValue(location)
                if (location.data != null) {
                    val address = location.data
                    stateCountryName.value =
                        "${address.adminArea ?: "-"}, ${address.countryName ?: "-"}"
                    getSunriseData(address.latitude, address.longitude)
                }
            }
        }
    }

    private val _sunRiseData = MutableLiveData<ApiResult<SunDetailsResponseDTO>>()
    val sunRiseData: MutableLiveData<ApiResult<SunDetailsResponseDTO>>
        get() = _sunRiseData

    private fun getSunriseData(lat: Double, lang: Double) {
        _sunRiseData.value = ApiResult.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _sunRiseData.postValue(
                repository.getSunData(
                    requestData = SunDataRequestDTO(
                        date = DateUtil.getCurrentDateFormatted(),
                        lat = lat,
                        lang = lang
                    )
                )
            )
        }

    }
}