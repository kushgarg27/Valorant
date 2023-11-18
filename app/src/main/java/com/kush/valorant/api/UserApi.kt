package com.kush.valorant.api

import com.kush.valorant.data.response.ValorantAgents
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {
    //get Agents
    @GET("agents")
    suspend fun getAgents(
        @Query("language") language: String,
        @Query("isPlayableCharacter") isPlayableCharacter: Boolean,
    ):Response<ValorantAgents>
}