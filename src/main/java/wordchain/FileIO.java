package main.java.wordchain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileIO {

  public static void main(String[] args) {
    String fileName = "src/main/java/wordchain/resources/input.txt";
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        processLine(line);
        printSeperator();
      }
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }
  }

  public static void processLine(String line) {
    WordChains.wordChains(line.split(" "));
  }

  private static void printSeperator() {
    for (int i = 0; i < 72; i++) {
      System.out.print("-");
    }
    System.out.println();
  }

}
