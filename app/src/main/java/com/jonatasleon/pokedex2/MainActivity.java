package com.jonatasleon.pokedex2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Meta lastMeta = null;
    List<Pokemon> pokemons = new ArrayList<>();
    RecyclerView recyclerView;
    PokemonAdapter pokemonAdapter;
    ApiInterface apiService;

    boolean isLoading = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService = ApiClient.getClient().create(ApiInterface.class);

        recyclerView = (RecyclerView) findViewById(R.id.rv_pokemons);
        pokemonAdapter = new PokemonAdapter(pokemons);

        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pokemonAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager lm;
                int pastVisibleItems, visibleItemsCount, totalItemCount;

                if(dy > 0) {
                    lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                    visibleItemsCount = lm.getChildCount();
                    totalItemCount = lm.getItemCount();
                    pastVisibleItems = lm.findFirstVisibleItemPosition();

                    if(!isLoading && (visibleItemsCount + pastVisibleItems) >= totalItemCount) {
                        isLoading = true;
                        Call<PokemonsResponse> call = apiService.getPokemons(lastMeta.getNext());
                        call.enqueue(pokemonsResponseCallBack);
                    }
                }

            }
        });

        recyclerView.addOnItemTouchListener(
            new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
                @Override
                public void onClick(View view, int position) {

                    Intent i = new Intent(
                                MainActivity.this,
                                DetailActivity.class);

                        Pokemon pokemon = pokemons.get(position);
                        int id = pokemon.getPokedexId();

                        i.putExtra("ID", id);

                        startActivity(i);
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                        Pokemon pokemon = pokemons.get(position);

                        String info = "";
                        info += "Name: " + pokemon.getName();
                        info += "\nAttack: " + pokemon.getAttack();
                        info += "\nDefense: " + pokemon.getDefense();
                        info += "\nHealth: " + pokemon.getHealth();
                        info += "\nSpeed: " + pokemon.getSpeed();
                        info += "\nId: " + pokemon.getPokedexId();

                        Toast.makeText(MainActivity.this, info, Toast.LENGTH_LONG).show();

                    }
                }));

        addData();
    }

    private void addData() {

        Call<PokemonsResponse> call = apiService.getPokemons(10, 0);
        call.enqueue(pokemonsResponseCallBack);
    }

    private Callback<PokemonsResponse> pokemonsResponseCallBack = new Callback<PokemonsResponse>() {
        @Override
        public void onResponse(Call<PokemonsResponse> call, Response<PokemonsResponse> response) {
            if(response.isSuccessful()) {
                PokemonsResponse pokemonsResponse = response.body();
                lastMeta = pokemonsResponse.getMeta();
                pokemons.addAll(pokemonsResponse.getPokemons());
                pokemonAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }

        @Override
        public void onFailure(Call<PokemonsResponse> call, Throwable t) {

        }
    };
}
