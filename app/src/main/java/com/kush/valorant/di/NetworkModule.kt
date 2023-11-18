package com.kush.valorant.di
import android.util.Log
import com.kush.valorant.api.UserApi
import com.kush.valorant.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.UnknownHostException
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val logging = HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                // Log the message to Logcat
                Log.d("Retrofit", message)
            }
        })
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL) // Replace with your actual base URL
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofitApi(retrofit: Retrofit): UserApi {
        val logging = HttpLoggingInterceptor()
        val client = OkHttpClient.Builder()
        // if (BuildConfig.DEBUG) {

            logging.level = HttpLoggingInterceptor.Level.BODY


        client.addInterceptor(logging)


        return retrofit
            .create(UserApi::class.java)


    }
}