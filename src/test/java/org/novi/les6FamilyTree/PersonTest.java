package org.novi.les6FamilyTree;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

class PersonTest {

    private Person person;

    @BeforeEach
    public void setup(){
        person = new Person("John", "Wick", 'M', 62);
    }

    @Test
    public void testGetName() {
        Assertions.assertEquals("John", person.getName());
    }

    @Test
    void setName() {
        person.setName("Ben");
        Assertions.assertEquals("Ben", person.getName());
    }

    @Test
    void getMiddleName() {
        Assertions.assertNull(person.getMiddleName());
    }

    @Test
    void setMiddleName() {
        person.setMiddleName("Benny");
        Assertions.assertEquals("Benny", person.getMiddleName());
    }

    @Test
    void getLastName() {
        Assertions.assertEquals("Wick", person.getLastName());
    }

    @Test
    void setLastName() {
        person.setLastName("Bobberoni");
        Assertions.assertEquals("Bobberoni", person.getLastName());
    }

    @Test
    void getMother() {
        Assertions.assertNull(person.getMother());
    }

    @Test
    void testAddParents(){
        Person mother = new Person("Mary", "Latin", 'F', 100);
        Person father = new Person("Father", "Fatherson", 'M', 120);
        Person extra = new Person("IamAnextra", "Person", 'M', -1);

        person.addParents(father, mother);
        Assertions.assertEquals(father, person.getFather());
        Assertions.assertEquals(mother, person.getMother());

        extra.addParents(person);
        Assertions.assertEquals(person, extra.getFather());
        Assertions.assertNull(extra.getMother());

        // check if person is now also the child of parent
        Assertions.assertTrue(father.getChildren().contains(person));
        Assertions.assertTrue(Arrays.asList(father.getChildren()).size() == 1);
    }

    @Test
    void testAddSibling(){
        Person sibling1 = new Person("Sibling1", "Lastname", 'F', 60);
        Person sibling2 = new Person("Sibling2", "Lastname", 'F', 60);

        person.addSibling(sibling1);

        // check if siblings from each other
        Assertions.assertTrue(person.getSiblings().contains(sibling1));
        Assertions.assertTrue(sibling1.getSiblings().contains(person));
    }

    @Test
    void testAddChild() {
        Person child1 = new Person("Child1", "Children", 'F', 20);
        Person child2 = new Person("Child2", "Children", 'M', 20);

        person.addChild(child1);

        //check if child is set and person as parent
        Assertions.assertTrue(person.getChildren().contains(child1));
        Assertions.assertEquals(person, child1.getFather());

        //add another child and see if the children are set to siblings from each other
        person.addChild(child2);
        Assertions.assertTrue(person.getChildren().contains(child1) && person.getChildren().contains(child2));
        Assertions.assertTrue(child1.getSiblings().contains(child2));
        Assertions.assertTrue(child2.getSiblings().contains(child1));
    }

    @Test
    void TestGetGrandChildren() {
        Person father = new Person("Father", "Fatherson", 'M', 120);
        Person child1 = new Person("Child1", "Children", 'F', 20);
        Person child2 = new Person("Child2", "Children", 'M', 20);

        person.addParents(father);
        person.addChild(child1);
        person.addChild(child2);

        ArrayList<Person> children = new ArrayList<>(Arrays.asList(child1, child2));

        Assertions.assertEquals(children, father.getGrandChildren());
    }

    @Test
    void getGrandChildrenPets() {
        // Create grandfather and grandchildren
        Person father = new Person("Father", "Fatherson", 'M', 120);
        Person child1 = new Person("Child1", "Children", 'F', 20);
        Person child2 = new Person("Child2", "Children", 'M', 20);

        // Create pets
        Pet pet1 = new Pet("Pet1", 3, "cat");
        Pet pet2 = new Pet("Pet2", 2, "Dog");
        Pet pet3 = new Pet("Pet3", 1, "Bird");

        child1.addPet(pet1);
        child1.addPet(pet2);
        child2.addPet(pet3);

        person.addParents(father);
        person.addChild(child1);
        person.addChild(child2);

        // Create ArrayList with the pets
        ArrayList<Pet> pets = new ArrayList<>(Arrays.asList(pet1, pet2, pet3));

        // Check if the pets are correct
        Assertions.assertEquals(pets, father.getGrandChildrenPets());
    }

    @Test
    void testGetNieces() {
        Person sibling1 = new Person("Sibling", "Sibling", 'M', 60);
        Person sibling2 = new Person("Sibling", "Sibling", 'M', 59);

        Person child1 = new Person("Child1", "Children", 'F', 20);
        Person child2 = new Person("Child2", "Children", 'M', 20);
        Person child3 = new Person("Child2", "Children", 'F', 20);
        Person child4 = new Person("Child2", "Children", 'F', 20);

        // set children
        sibling1.addChild(child1);
        sibling1.addChild(child2);
        sibling2.addChild(child3);
        sibling2.addChild(child4);

        // set siblings
        person.addSibling(sibling1);
        person.addSibling(sibling2);

        // ArrayList with the Nieces
        ArrayList<Person> nieces = new ArrayList<>(Arrays.asList(child1, child3, child4));

        Assertions.assertEquals(nieces, person.getNieces());
    }
}