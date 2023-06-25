package familytree;

import java.util.ArrayList;

public class Persons {
    private String name;
    private String middleName;
    private String lastName;
    private char sex;
    private int age;
    private Persons mother;
    private Persons father;
    private Persons partner;
    private ArrayList<Persons> siblings = new ArrayList<>();
    private ArrayList<Persons> children = new ArrayList<>();
    private ArrayList<Pet> pets = new ArrayList<Pet>();

    public Persons(String name, String lastname, char sex, int age){
        this.name = name;
        this.lastName = lastname;
        this.sex = sex;
        this.age = age;
    }

    public Persons(String name, String middleName, String lastname, char sex, int age){
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

    public Persons getMother() {
        return mother;
    }

    public void setMother(Persons mother) {
        this.mother = mother;
    }

    public Persons getFather() {
        return father;
    }

    public void setFather(Persons father) {
        this.father = father;
    }

    public Persons getPartner() {
        return partner;
    }

    public void setPartner(Persons partner) {
        this.partner = partner;
    }

    public ArrayList<Persons> getSiblings() {
        return siblings;
    }

    public void setSiblings(ArrayList<Persons> siblings) {
        this.siblings = siblings;
    }

    public ArrayList<Persons> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Persons> children) {
        this.children = children;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    //Methods
    public void addParents(Persons father, Persons mother){
        this.addParents(father);
        this.addParents(mother);
    }

    //overloading so you can add 1 or 2 parents
    public void addParents(Persons parent){
        if(parent.getSex() == 'M'){
            this.setFather(parent);
            parent.addChild(this);
        }else if(parent.getSex() == 'F'){
            this.setMother(parent);
            parent.addChild(this);
        }
    }

    public void addChild(Persons child){
        if(!this.children.contains(child)) {
            this.children.add(child);
        }

        // make child new sibling
        if(this.children.size() > 1){
            for(Persons sib: this.children){
                sib.addSibling(child);
            }
        }
    }

    public void addSibling(Persons sibling){
        if(sibling != this && !this.siblings.contains(sibling)){
            this.siblings.add(sibling);
            sibling.addSibling(this);
        }

    }

    public String getParents(){
        return String.format("[%s, %s]", this.father, this.mother);
    }

    public ArrayList<Persons> getGrandChildren(){
        ArrayList<Persons> grandChildren = new ArrayList<>();

        for(Persons child: this.getChildren()){
            for(Persons grandChild: child.children) {
                grandChildren.add(grandChild);
            }
        }
        return grandChildren;
    }

    public ArrayList<Pet> getGrandChildrenPets(){
        ArrayList<Pet> grandChildrenPets = new ArrayList<>();

        for(Persons child: this.getChildren()){
            for(Persons grandChild: child.children) {
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

    public ArrayList<Persons> getNieces(){
        ArrayList<Persons> n = new ArrayList<>();

        for(Persons siblings: this.siblings){
            for(Persons nepOrNiece: siblings.getChildren()){
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
