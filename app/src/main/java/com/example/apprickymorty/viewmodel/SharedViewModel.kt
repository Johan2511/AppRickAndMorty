package com.example.apprickymorty.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apprickymorty.apirest.response.CharacterList
import com.example.apprickymorty.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class SharedViewModel(val repository: Repository):ViewModel() {

    var listCharacters = MutableLiveData<Response<CharacterList>>()
    var filterValue = MutableLiveData<Array<Int>>()
    var isFilter = MutableLiveData<Boolean>()

    init {
        filterValue.value = arrayOf(0, 0)
        isFilter.value = false
    }

    fun getCharacters(page:Int){
        viewModelScope.launch {
            val characters = repository.getCharacters(page)
            listCharacters.value = characters
            isFilter.value = false

        }
    }

    fun getByStatusAndGender(status: String, gender: String, page: Int){
        viewModelScope.launch {
            val characters = repository.getCharacterByStatusAndGender(status, gender, page)
            listCharacters.value = characters
            isFilter.value = true

        }
    }

    fun getByStatus(status: String, page: Int){
        viewModelScope.launch {
            val characters = repository.getCharacterByStatus(status, page)
            listCharacters.value = characters
            isFilter.value = true

        }
    }

    fun getByGender(gender: String, page: Int){
        viewModelScope.launch {
            val characters = repository.getCharacterByGender(gender, page)
            listCharacters.value = characters
            isFilter.value = true

        }
    }

    fun getByName(name: String){
        viewModelScope.launch {
            val characters = repository.getCharacterByName(name)
            listCharacters.value = characters
            isFilter.value = true
        }
    }
}