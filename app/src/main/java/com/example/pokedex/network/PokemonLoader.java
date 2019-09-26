package com.example.pokedex.network;

import com.example.pokedex.network.models.PokemonByIdResponse;
import com.example.pokedex.network.models.PokemonsListResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonLoader implements PokemonAPI{
    PokemonAPI pokemonAPI; //Llamamos a un objeto, pokemonAPI en este caso.
    final String URL_BASE = "https://pokeapi.co/api/v2/";

    public PokemonLoader() {
        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl(URL_BASE)
                                .addConverterFactory(GsonConverterFactory.create())    //Esto le dice que va a recibir un JSON   <-----
                                .build();
        pokemonAPI = retrofit.create(PokemonAPI.class);  //De esta forma le damos la posibilidad a pokemonAPI de hacer llamados a la API
    }

    @Override
    public Call<PokemonsListResponse> getPokemonList() {
        return pokemonAPI.getPokemonList();
    }

    @Override
    public Call<PokemonByIdResponse> getPokemonById(String id) {
        return pokemonAPI.getPokemonById(id);
    }
}
