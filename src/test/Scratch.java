package test;

import java.util.*;

public class Scratch {

	public static void main(String[] args) {
        Arrays.asList("Dingus", "Dongus", "Kingus", "Kongus").stream()
            .map(name -> name.charAt(0))
            .forEach(character -> System.out.println(character));
	}

}