package com.example.pokedex.network;

import com.example.pokedex.network.models.PokemonByIdResponse;
import com.example.pokedex.network.models.PokemonsListResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonAPI {
    @GET("pokemon")
    Call<PokemonsListResponse> getPokemonList();

    @GET("pokemon/{id}")
    Call<PokemonByIdResponse> getPokemonById(@Path("id") String id);
}
