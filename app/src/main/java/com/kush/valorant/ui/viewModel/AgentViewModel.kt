package com.kush.valorant.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kush.valorant.data.response.ValorantAgents
import com.kush.valorant.repository.UserRepository
import com.kush.valorant.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel

class AgentViewModel @Inject constructor(private val userRepository: UserRepository):ViewModel(){
    val liveDataScope:LiveData<NetworkResult<ValorantAgents>>
        get() = userRepository.agentsResponseLiveData
    fun getAgents(language: String ,isPlayableCharacter :Boolean) {
        viewModelScope.launch {
            userRepository.getAgents(language,isPlayableCharacter)
        }
    }

}