package fr.iut.beerrater.data.data_source.api

import fr.iut.beerrater.data.data_source.api.dto.BeerDTO
import fr.iut.beerrater.domain.model.Beer
import retrofit2.http.GET
import retrofit2.http.Path


interface BeerApi {
    @GET("beers")
    suspend fun getBeers(): List<BeerDTO>

    @GET("beers/random")
    suspend fun getRandomBeer(): BeerDTO

    @GET("beers/{id}")
    suspend fun getBeerById(@Path("id") beerId: Int): BeerDTO?
}