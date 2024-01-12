package com.hoho.assignmentsun.viewmodels

import android.os.CountDownTimer
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hoho.assignmentsun.model.SunDataRequestDTO
import com.hoho.assignmentsun.model.SunDetailsResponseDTO
import com.hoho.assignmentsun.model.WeatherDetails
import com.hoho.assignmentsun.networkService.ApiResult
import com.hoho.assignmentsun.repository.HomeRepository
import com.hoho.assignmentsun.utils.DateUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository) : ViewModel() {
    val sunrise = mutableStateOf("")
    val sunset = mutableStateOf("")
    val isStarted = mutableStateOf(false)

    val totalTime = mutableLongStateOf(0L)
    private val _timerText = mutableStateOf("00:00")
    val timerText: MutableState<String> = _timerText
    private val _remainingTime = mutableFloatStateOf(0f)
    val remainingTime: MutableState<Float> = _remainingTime
    private var countDownTimer: CountDownTimer? = null
    fun startTimer(minutes: Long) {
        totalTime.longValue = minutes
        val totalTimeMillis = minutes * 60 * 1000
        countDownTimer = object : CountDownTimer(totalTimeMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                isStarted.value = true
                val remainingMillis = totalTimeMillis - millisUntilFinished
                _remainingTime.floatValue = (remainingMillis.toFloat() / totalTimeMillis) * 260f
                Log.d("remainigTime", "onTick: ${remainingTime.value}")
                updateTimerText(millisUntilFinished)
            }

            override fun onFinish() {
                isStarted.value = false
                _remainingTime.floatValue = 1f
                updateTimerText(0)
            }
        }

        countDownTimer?.start()
    }

    fun cancelTimer() {
        isStarted.value = false
        _remainingTime.floatValue = 0f
        updateTimerText(0)
        countDownTimer?.cancel()
    }

    private fun updateTimerText(remainingMillis: Long) {
        val secondsLeft = remainingMillis / 1000
        val minutesLeft = secondsLeft / 60
        _timerText.value =
            "${String.format("%02d", minutesLeft)}:${String.format("%02d", secondsLeft % 60)} min"
    }

    private val _weatherData = MutableLiveData<ApiResult<List<WeatherDetails>>>()
    val weatherData: MutableLiveData<ApiResult<List<WeatherDetails>>>
        get() = _weatherData

    fun getWeatherData() {
        _weatherData.value = ApiResult.Loading
        viewModelScope.launch(Dispatchers.IO) {
            _weatherData.postValue(
                repository.getWeatherData()
            )
        }

    }

    override fun onCleared() {
        super.onCleared()
        cancelTimer()
    }
}