package com.example.pokemon_a_caballero;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class pokemonDetailsFragment extends Fragment {

    private PokemonDetailsViewModel mViewModel;

    public static pokemonDetailsFragment newInstance() {
        return new pokemonDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();

        if (args != null){
            Pokemon pokemon = (Pokemon) args.getSerializable("pokemon");
            
        }

        return inflater.inflate(R.layout.fragment_pokemon_details, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PokemonDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}