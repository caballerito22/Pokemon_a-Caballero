package com.example.pokemon_a_caballero;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceManager;

import com.example.pokemon_a_caballero.databinding.FragmentFirstBinding;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    ArrayList <Pokemon> pokemonlista;
    ArrayAdapter<Pokemon> adapter;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        pokemonlista = new ArrayList<>();
        /*pokemon.add("Ana Maria");
        pokemon.add("Alvaro");
        pokemon.add("Samulea");
*/
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        return binding.getRoot();

    }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new PokemonAdapter(
                getContext(),
                R.layout.pokemon_list_item,
                pokemonlista);
        binding.listaPokemons.setAdapter(adapter);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            ArrayList<Pokemon> pokemons = PokeAPI.buscar();

            getActivity().runOnUiThread(() -> {
                for (Pokemon p : pokemons) {
                    pokemonlista.add(p);
                }
                adapter.notifyDataSetChanged();
            });
        });

        binding.listaPokemons.setOnItemClickListener((adapterView, fragment, i, l) -> {
            Pokemon pokemon = adapter.getItem(i);
            Bundle args = new Bundle();
            args.putSerializable("Pokemon", pokemon);
            Log.d("XXX", pokemon.toString());
            NavHostFragment.findNavController(FirstFragment.this)
                    .navigate(R.id.action_FirstFragment_to_pokemonDetailsFragment, args);
        });
        }

        @Override
        public boolean onOptionsItemSelected (MenuItem item){
            int id = item.getItemId();

            if (id == R.id.action_refresh) {
                refresh();
                Toast.makeText(getContext(), "Click!", Toast.LENGTH_SHORT).show();
                Log.d("XXXMenu", "Click");
            }

            if (id == R.id.action_settings) {
                Toast.makeText(getContext(), "Click!", Toast.LENGTH_SHORT).show();
                Log.d("XXXMenu", "Click");
                Intent i = new Intent(getActivity(), SettingsActivity.class);
                startActivity(i);
                return true;
            }

            return super.onOptionsItemSelected(item);
        }


        void refresh () {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> {
                ArrayList<Pokemon> pokemons = PokeAPI.buscar();

                getActivity().runOnUiThread(() -> {
                    for (Pokemon p : pokemons) {
                        pokemonlista.add(p);
                    }
                    adapter.notifyDataSetChanged();
                });
            });


            binding.listaPokemons.setOnItemClickListener((adapterView, fragment, i, l) -> {
                Pokemon pokemon = adapter.getItem(i);
                Bundle args = new Bundle();
                args.putSerializable("Pokemon", pokemon);
                Log.d("XXX", pokemon.toString());
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_pokemonDetailsFragment, args);
            });
        }


        @Override
        public void onDestroyView () {
            super.onDestroyView();
            binding = null;
        }
    }
