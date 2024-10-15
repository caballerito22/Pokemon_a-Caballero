package com.example.pokemon_a_caballero;

public class Pokemon {
   private double id;
   private String name;
   private String species;
   private Integer weight;
   private String sprite;

    public Pokemon(double id, String name, String species, Integer weight, String sprite) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.weight = weight;
        this.sprite = sprite;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
}
