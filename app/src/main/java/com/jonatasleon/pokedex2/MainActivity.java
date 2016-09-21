package com.jonatasleon.pokedex2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Pokemon> pokemons = new ArrayList<>();
    RecyclerView recyclerView;
    PokemonAdapter pokemonAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.rv_pokemons);

        pokemonAdapter = new PokemonAdapter(pokemons);

        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(getApplicationContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(pokemonAdapter);

        addData();
    }

    private void addData() {
        Pokemon pokemon;

        pokemon = new Pokemon("grama", "Bulbasaur");
        pokemons.add(pokemon);

        pokemon = new Pokemon("fogo", "Charizard");
        pokemons.add(pokemon);

        pokemon = new Pokemon("agua", "Squirtle");
        pokemons.add(pokemon);

        pokemon = new Pokemon("grama", "Bulbasaur");
        pokemons.add(pokemon);

        pokemon = new Pokemon("fogo", "Charizard");
        pokemons.add(pokemon);

        pokemon = new Pokemon("agua", "Squirtle");
        pokemons.add(pokemon);

        pokemon = new Pokemon("grama", "Bulbasaur");
        pokemons.add(pokemon);

        pokemon = new Pokemon("fogo", "Charizard");
        pokemons.add(pokemon);

        pokemon = new Pokemon("agua", "Squirtle");
        pokemons.add(pokemon);

        pokemon = new Pokemon("grama", "Bulbasaur");
        pokemons.add(pokemon);

        pokemon = new Pokemon("fogo", "Charizard");
        pokemons.add(pokemon);

        pokemon = new Pokemon("agua", "Squirtle");
        pokemons.add(pokemon);

        pokemon = new Pokemon("grama", "Bulbasaur");
        pokemons.add(pokemon);

        pokemon = new Pokemon("fogo", "Charizard");
        pokemons.add(pokemon);

        pokemon = new Pokemon("agua", "Squirtle");
        pokemons.add(pokemon);

        pokemonAdapter.notifyDataSetChanged();
    }
}
