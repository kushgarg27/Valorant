package com.kush.valorant.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kush.valorant.api.UserApi
import com.kush.valorant.data.response.ValorantAgents
import com.kush.valorant.utils.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(private val userApi: UserApi) {
    private val agentsLiveData = MutableLiveData<NetworkResult<ValorantAgents>>()
    val agentsResponseLiveData: LiveData<NetworkResult<ValorantAgents>>
        get() = agentsLiveData

    suspend fun getAgents(
        language: String, isPlayableCharacter: Boolean
    ) {
        try {
            agentsLiveData.postValue(NetworkResult.Loading())
            val response = userApi.getAgents(
                "en-US", true
            )
            agentsResponse(response)
        } catch (e: Exception) {
            agentsLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }

    private fun agentsResponse(response: Response<ValorantAgents>) {
        if (response.isSuccessful && response.body() != null) {
            agentsLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            agentsLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            agentsLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }

    }

}