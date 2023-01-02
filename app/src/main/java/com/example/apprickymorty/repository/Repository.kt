package com.example.apprickymorty.repository

import com.example.apprickymorty.RetrofitInstance
import com.example.apprickymorty.apirest.response.CharacterList
import retrofit2.Response

class Repository {

    suspend fun getCharacters(page:Int): Response<CharacterList>{
        return RetrofitInstance.api.getCharacters(page)

        }
    suspend fun getCharacterByStatusAndGender(status:String, gender:String, page: Int):Response<CharacterList>{
        return  RetrofitInstance.api.getCharactersByStatusAndGender(status,gender,page)
    }

    suspend fun getCharacterByStatus(status:String, page: Int):Response<CharacterList>{
        return  RetrofitInstance.api.getCharactersByStatus(status,page)
    }

    suspend fun getCharacterByGender(gender:String, page: Int):Response<CharacterList>{
        return  RetrofitInstance.api.getCharactersByGender(gender,page)
    }

    suspend fun getCharacterByName(name: String): Response<CharacterList>{
        return  RetrofitInstance.api.getCharacterByName(name)
    }
}