package org.novi.les6FamilyTree;

import java.util.ArrayList;

public class Person {
    private String name;
    private String middleName;
    private String lastName;
    private char sex;
    private int age;
    private Person mother;
    private Person father;
    private Person partner;
    private ArrayList<Person> siblings = new ArrayList<>();
    private ArrayList<Person> children = new ArrayList<>();
    private ArrayList<Pet> pets = new ArrayList<Pet>();

    public Person(String name, String lastname, char sex, int age){
        this.name = name;
        this.lastName = lastname;
        this.sex = sex;
        this.age = age;
    }

    public Person(String name, String middleName, String lastname, char sex, int age){
        this(name, lastname, sex, age);
        this.middleName = middleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person getMother() {
        return mother;
    }

    public void setMother(Person mother) {
        this.mother = mother;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    public Person getPartner() {
        return partner;
    }

    public void setPartner(Person partner) {
        if(this.partner != partner){
            this.partner = partner;
            partner.setPartner(this);
        }

        // share children
        if(this.children.size() > 0){
            for(Person child: this.children){
                partner.addChild(child);
            }
        }
    }

    public ArrayList<Person> getSiblings() {
        return siblings;
    }

    public void setSiblings(ArrayList<Person> siblings) {
        this.siblings = siblings;
    }

    public ArrayList<Person> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Person> children) {
        this.children = children;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    //Methods
    public void addParents(Person father, Person mother){
        this.addParents(father);
        this.addParents(mother);
    }

    //overloading so you can add 1 or 2 parents
    public void addParents(Person parent){
        if(parent.getSex() == 'M'){
            this.setFather(parent);
        }else if(parent.getSex() == 'F'){
            this.setMother(parent);
        }
        // add only when not a child yet. Else it will be an indefinite loop
        if(!parent.getChildren().contains(this)){
            parent.addChild(this);
        }
    }

    public void addChild(Person child){
        if(!this.children.contains(child)) {
            this.children.add(child);
        }
        // make person the parent
        child.addParents(this);

        // make child new sibling
        if(this.children.size() > 1){
            for(Person sib: this.children){
                sib.addSibling(child);
            }
        }
    }

    public void addSibling(Person sibling){
        if(sibling != this && !this.siblings.contains(sibling)){
            this.siblings.add(sibling);
            sibling.addSibling(this);
        }

    }

    public String getParents(){
        return String.format("[%s, %s]", this.father, this.mother);
    }

    public ArrayList<Person> getGrandChildren(){
        ArrayList<Person> grandChildren = new ArrayList<>();

        for(Person child: this.getChildren()){
            for(Person grandChild: child.children) {
                grandChildren.add(grandChild);
            }
        }
        return grandChildren;
    }

    public ArrayList<Pet> getGrandChildrenPets(){
        ArrayList<Pet> grandChildrenPets = new ArrayList<>();

        for(Person child: this.getChildren()){
            for(Person grandChild: child.children) {
                for(Pet pet: grandChild.pets){
                    grandChildrenPets.add(pet);
                }
            }
        }
        return grandChildrenPets;
    }

    public void addPet(Pet animal){
        if(!this.pets.contains(animal)){
            this.pets.add(animal);
        }
    }

    public ArrayList<Person> getNieces(){
        ArrayList<Person> n = new ArrayList<>();

        for(Person siblings: this.siblings){
            for(Person nepOrNiece: siblings.getChildren()){
                if(nepOrNiece.getSex() == 'F'){
                    n.add(nepOrNiece);
                }
            }
        }
        return n;
    }

    public String toString(){
        String fullName;
        if(this.middleName == null){
            fullName = String.format("%s %s", this.getName(), this.getLastName());
        }else{
            fullName = String.format("%s %s %s", this.getName(), this.getMiddleName(), this.getLastName());
        }
        return fullName;
    }

}
