package com.example.pokedex;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pokedex.network.PokeCallBack;
import com.example.pokedex.network.models.Pokemon;
import com.example.pokedex.network.models.PokemonsListResponse;
import com.example.pokedex.pokemon.PokemonAdapter;
import com.example.pokedex.utils.Constant;
import com.example.pokedex.utils.DialogManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PokedexActivity extends BaseActivity {

    List<Pokemon> pokemonList;
    PokemonAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);

        final EditText etPokeAdd = findViewById(R.id.etPokeAdd);
        Button btPokeAdd = findViewById(R.id.btPokeAdd);

        btPokeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String poke = etPokeAdd.getText().toString();
                if (poke.isEmpty()){
                    return;
                }
                Pokemon pokemon = new Pokemon(poke, "");
                pokemonList.add(0, pokemon);
                adapter.notifyDataSetChanged();//Esto va a decir que la lista tuvo un cambio

            }
        });

        final RecyclerView rvPokemonList = findViewById(R.id.rvPokemonList);

        Call<PokemonsListResponse> call = loader.getPokemonList();

        call.enqueue(new PokeCallBack<PokemonsListResponse>(PokedexActivity.this, true) {
            @Override
            public void onResponse(Call<PokemonsListResponse> call, Response<PokemonsListResponse> response) {
                super.onResponse(call, response);

                if(response.isSuccessful()){

                    pokemonList = response.body().getPokemonList(); //Nos devuelve la lista de pokemons

                    adapter = new PokemonAdapter(pokemonList, PokedexActivity.this);

                    rvPokemonList.setAdapter(adapter);

                    rvPokemonList.setHasFixedSize(true);

                    RecyclerView.LayoutManager manager = new LinearLayoutManager(PokedexActivity.this);

                    rvPokemonList.setLayoutManager(manager);
                } else {
                    showDialogError();
                }


            }

            @Override
            public void onFailure(Call<PokemonsListResponse> call, Throwable t) {
                super.onFailure(call, t);
                showDialogError();
            }
        });

    }
}
