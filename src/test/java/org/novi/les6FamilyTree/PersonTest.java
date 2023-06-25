package org.novi.les6FamilyTree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class PersonTest {

    private Person person;

    @BeforeEach
    //arrange
    public void setup(){
        person = new Person("John", "Wick", 'M', 62);
    }

    @Test
    //assert
    public void testGetName() {
        Assertions.assertEquals("John", person.getName());
    }

    @Test
    void setName() {
        //act
        person.setName("Ben");

        //assert
        Assertions.assertEquals("Ben", person.getName());
    }

    @Test
    void getMiddleName() {
        //assert
        Assertions.assertNull(person.getMiddleName());
    }

    @Test
    void setMiddleName() {
        //act
        person.setMiddleName("Benny");

        //assert
        Assertions.assertEquals("Benny", person.getMiddleName());
    }

    @Test
    void getLastName() {
        //assert
        Assertions.assertEquals("Wick", person.getLastName());
    }

    @Test
    void setLastName() {
        //act
        person.setLastName("Bobberoni");

        //assert
        Assertions.assertEquals("Bobberoni", person.getLastName());
    }

    @Test
    void getMother() {
        //assert
        Assertions.assertNull(person.getMother());
    }

    @Test
    void testAddParents(){
        //arrange
        Person mother = new Person("Mary", "Latin", 'F', 100);
        Person father = new Person("Father", "Fatherson", 'M', 120);
        Person extra = new Person("IamAnextra", "Person", 'M', -1);

        //act
        person.addParents(father, mother);

        //assert
        Assertions.assertEquals(father, person.getFather());
        Assertions.assertEquals(mother, person.getMother());

        //act
        extra.addParents(person);

        //assert
        Assertions.assertEquals(person, extra.getFather());
        Assertions.assertNull(extra.getMother());

        // check if person is now also the child of parent
        Assertions.assertTrue(father.getChildren().contains(person));
        Assertions.assertTrue(Arrays.asList(father.getChildren()).size() == 1);
    }

    @Test
    void testAddSibling(){
        //arrange
        Person sibling1 = new Person("Sibling1", "Lastname", 'F', 60);
        Person sibling2 = new Person("Sibling2", "Lastname", 'F', 60);

        //act
        person.addSibling(sibling1);

        //assert
        // check if siblings from each other
        Assertions.assertTrue(person.getSiblings().contains(sibling1));
        Assertions.assertTrue(sibling1.getSiblings().contains(person));
    }

    @Test
    void testAddChild() {
        //arrange
        Person child1 = new Person("Child1", "Children", 'F', 20);
        Person child2 = new Person("Child2", "Children", 'M', 20);

        //act
        person.addChild(child1);

        //assert
        //check if child is set and person as parent
        Assertions.assertTrue(person.getChildren().contains(child1));
        Assertions.assertEquals(person, child1.getFather());

        //act
        //add another child and see if the children are set to siblings from each other
        person.addChild(child2);

        //assert
        Assertions.assertTrue(person.getChildren().contains(child1) && person.getChildren().contains(child2));
        Assertions.assertTrue(child1.getSiblings().contains(child2));
        Assertions.assertTrue(child2.getSiblings().contains(child1));
    }

    @Test
    void TestGetGrandChildren() {
        //arrange
        Person father = new Person("Father", "Fatherson", 'M', 120);
        Person child1 = new Person("Child1", "Children", 'F', 20);
        Person child2 = new Person("Child2", "Children", 'M', 20);

        ArrayList<Person> children = new ArrayList<>(Arrays.asList(child1, child2));

        person.addParents(father);
        person.addChild(child1);
        person.addChild(child2);



        //assert / act
        Assertions.assertEquals(children, father.getGrandChildren());
    }

    @Test
    void getGrandChildrenPets() {
        //arrange
        // Create grandfather and grandchildren
        Person father = new Person("Father", "Fatherson", 'M', 120);
        Person child1 = new Person("Child1", "Children", 'F', 20);
        Person child2 = new Person("Child2", "Children", 'M', 20);

        // Create pets
        Pet pet1 = new Pet("Pet1", 3, "cat");
        Pet pet2 = new Pet("Pet2", 2, "Dog");
        Pet pet3 = new Pet("Pet3", 1, "Bird");

        // Create ArrayList with the pets
        ArrayList<Pet> pets = new ArrayList<>(Arrays.asList(pet1, pet2, pet3));

        child1.addPet(pet1);
        child1.addPet(pet2);
        child2.addPet(pet3);

        person.addParents(father);
        person.addChild(child1);
        person.addChild(child2);

        //assert / act
        // Check if the pets are correct
        Assertions.assertEquals(pets, father.getGrandChildrenPets());
    }

    @Test
    void testGetNieces() {
        //arrange
        Person sibling1 = new Person("Sibling", "Sibling", 'M', 60);
        Person sibling2 = new Person("Sibling", "Sibling", 'M', 59);

        Person child1 = new Person("Child1", "Children", 'F', 20);
        Person child2 = new Person("Child2", "Children", 'M', 20);
        Person child3 = new Person("Child2", "Children", 'F', 20);
        Person child4 = new Person("Child2", "Children", 'F', 20);

        // ArrayList with the Nieces
        ArrayList<Person> nieces = new ArrayList<>(Arrays.asList(child1, child3, child4));

        // set children
        sibling1.addChild(child1);
        sibling1.addChild(child2);
        sibling2.addChild(child3);
        sibling2.addChild(child4);

        // set siblings
        person.addSibling(sibling1);
        person.addSibling(sibling2);



        //assert / act
        Assertions.assertEquals(nieces, person.getNieces());
    }

    @Test
    void testSetPartner(){
        //arrange
        Person partner = new Person("Partner", "Partner", 'M', 60);

        Person child1 = new Person("Child1", "Children", 'F', 20);
        Person child2 = new Person("Child2", "Children", 'M', 20);
        Person child3 = new Person("Child3", "Children", 'F', 20);

        ArrayList<Person> children1 = new ArrayList<>(Arrays.asList(child1, child2, child3));
        ArrayList<Person> children2 = new ArrayList<>(Arrays.asList(child2, child3, child1));

        person.addChild(child1);
        partner.addChild(child2);
        partner.addChild(child3);

        //act
        person.setPartner(partner);

        //assert
        Assertions.assertEquals(children1, person.getChildren());
        Assertions.assertEquals(children2, partner.getChildren());
    }
}