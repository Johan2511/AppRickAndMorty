package com.example.apprickymorty.apirest.service

import com.example.apprickymorty.apirest.response.Character
import com.example.apprickymorty.apirest.response.CharacterList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SimpleApi {

    @GET("api/character")
    suspend fun getCharacters(@Query("page") page: Int): Response<CharacterList>

    @GET("api/character")
    suspend fun getCharactersByStatusAndGender(@Query("status") status: String,
                                                @Query("gender") gender:String,
                                                @Query("page") page: Int):Response<CharacterList>

    @GET("api/character")
    suspend fun getCharactersByStatus(@Query("status") status: String,
                                      @Query("page") page: Int):Response<CharacterList>

    @GET("api/character")
    suspend fun getCharactersByGender(@Query("gender") gender: String,
                                      @Query("page") page: Int):Response<CharacterList>

    @GET("api/character")
    suspend fun getCharacterByName(@Query("name") name: String):Response<CharacterList>
}