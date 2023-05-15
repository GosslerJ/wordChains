package main.java.wordchain;

import java.util.Scanner;

public class StandardInput {
  public static void main(String[] args) {
    WordChains.wordChains(standardInput());
  }

  public static String[] standardInput() {
    System.out.println("Kérem, adja meg a szavakat szóközzel elválasztva:");
    Scanner scanner = new Scanner(System.in);
    String input = scanner.nextLine();
    String[] words = input.split(" ");
    while (words.length < 2) {
      System.out.println("Legalább két szót kell megadni.");
      input = scanner.nextLine();
      words = input.split(" ");
    }
    scanner.close();
    return words;
  }

}
