package com.jonatasleon.pokedex2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailActivity extends AppCompatActivity {
    private TextView tvName, tvTypes, tvAttack, tvDefense, tvSpeed;
    private ImageView ivPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvName = (TextView) findViewById(R.id.tv_detail_name);
        tvTypes = (TextView) findViewById(R.id.tv_detail_types);
        tvAttack = (TextView) findViewById(R.id.tv_detail_attack);
        tvDefense = (TextView) findViewById(R.id.tv_detail_defense);
        tvSpeed = (TextView) findViewById(R.id.tv_detail_speed);
        ivPokemon = (ImageView) findViewById(R.id.iv_detail_pokemon);

        Intent i = getIntent();

        int id = i.getIntExtra("ID", -1);

        if (id != -1) {
            prepareData(id);
        }
    }

    private void prepareData(int id) {
        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Pokemon> call = apiService.getPokemon(id);
        call.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon pokemon;

                if(response.isSuccessful()) {
                    pokemon = response.body();

                    tvName.setText(pokemon.getName());
                    tvTypes.setText(pokemon.typesToString());
                    tvAttack.setText("Attack: " + pokemon.getAttack().toString());
                    tvDefense.setText("Defense: " + pokemon.getDefense().toString());
                    tvSpeed.setText("Speed: " + pokemon.getSpeed().toString());

                    Call<SpriteResponse> callSprite;
                    Sprite sprite = pokemon.getSprites().get(0);
                    callSprite = apiService.getSprite(sprite.getResourceUri());
                    callSprite.enqueue(new Callback<SpriteResponse>() {
                        @Override
                        public void onResponse(Call<SpriteResponse> call, Response<SpriteResponse> response) {
                            SpriteResponse spriteResponse;
                            if(response.isSuccessful()) {
                                spriteResponse = response.body();
                                Picasso.with(ivPokemon.getContext())
                                        .load("http://pokeapi.co" + spriteResponse.getImage())
                                        .resize(128,128)
                                        .into(ivPokemon);
                            }
                        }

                        @Override
                        public void onFailure(Call<SpriteResponse> call, Throwable t) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {

            }
        });
    }
}
