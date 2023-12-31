package org.novi.les6FamilyTree;

public class Pet {

    // state
    private String name;
    private int age;
    private String species;
    private Person owner;

    //constructor
    public Pet(String name, int age, String species){
        this.name = name;
        this.age = age;
        this.species = species;
    }

    // get and set
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
        owner.addPet(this);
    }

    // methods
    public String toString(){
        return String.format("%s the %s", this.getName(), this.getSpecies());
    }
}
