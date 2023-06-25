package familytree;

import java.util.ArrayList;
import java.util.Arrays;

public class Treemaker {
    public static void main(String[] args) {
        Persons mark = new Persons("Mark", "Veldhoven", 'M', 68);
        Persons ben = new Persons("Ben", "Veldhoven", 'M', 34);
        Persons jet = new Persons("Jet","Roos",  "Veldhoven", 'F', 1);
        Persons rob = new Persons("Rob", "Veldhoven", 'M', 4);
        Persons lea = new Persons("Lea", "Veldhoven", 'F', 9);
        Persons jeff = new Persons("Jeff", "Veldhoven", 'M', 0);
        Persons mary = new Persons("Mary", "Bakker", 'F', 32);
        Pet a = new Pet("a", 2, "Cat");
        Pet b = new Pet("b", 1, "Dog");
        Pet c = new Pet("c", 5, "Bird");

        ArrayList<Persons> childs = new ArrayList<>(Arrays.asList(jet,rob,lea));

        ben.addChild(rob);
        ben.addChild(lea);
        rob.addPet(c);
        lea.addPet(a);
        lea.addPet(b);
        System.out.println(lea.getPets());
        ben.addParents(mark);
        System.out.println(rob.getSiblings());
        System.out.println(mark.getGrandChildren());
        System.out.println(mark.getGrandChildrenPets());
        ben.addSibling(mary);
        System.out.println(mary.getNieces());
    }
}
