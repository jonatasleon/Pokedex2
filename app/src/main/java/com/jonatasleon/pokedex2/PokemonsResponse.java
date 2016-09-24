package com.jonatasleon.pokedex2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jonatasleon on 24/09/16.
 */
public class PokemonsResponse {

    @SerializedName("meta")
    private Meta meta;

    @SerializedName("objects")
    private List<Pokemon> pokemons;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }
}
