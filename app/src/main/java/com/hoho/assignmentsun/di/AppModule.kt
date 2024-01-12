package com.hoho.assignmentsun.di



import com.hoho.assignmentsun.BuildConfig
import com.hoho.assignmentsun.networkService.CommonApiService
import com.hoho.assignmentsun.networkService.OkHttpClientHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun providesCommonApiService(): CommonApiService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(OkHttpClientHelper().getOkHttpClient())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CommonApiService::class.java)
    }

}