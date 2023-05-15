package test.java.wordchain;

import main.java.wordchain.WordChains;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordChainsTest {

  @Test
  void testMinimumWithDistinctIntegers() {
    int i1 = 3;
    int i2 = 5;
    int i3 = 7;

    int expected = 3;
    int actual = WordChains.minimum(i1, i2, i3);

    assertEquals(expected, actual);
  }

  @Test
  void testMinimumWithEqualIntegers() {
    int i1 = 5;
    int i2 = 5;
    int i3 = 5;

    int expected = 5;
    int actual = WordChains.minimum(i1, i2, i3);

    assertEquals(expected, actual);
  }

  @Test
  public void testEditDistanceWithEmptyStrings() {
    int result = WordChains.editDistance("", "");
    assertEquals(0, result);
  }

  @Test
  public void testEditDistanceWithOneEmptyString() {
    int result1 = WordChains.editDistance("", "test");
    int result2 = WordChains.editDistance("test", "");
    assertEquals(4, result1);
    assertEquals(4, result2);
  }

  @Test
  public void testEditDistanceWithSameStrings() {
    int result = WordChains.editDistance("alma", "alma");
    assertEquals(0, result);
  }

  @Test
  public void testEditDistanceWithDifferentLengthStrings() {
    int result = WordChains.editDistance("barack", "alma");
    assertEquals(5, result);
  }

  @Test
  public void testEditDistanceWithCompletelyDifferentStrings() {
    int result = WordChains.editDistance("abc", "xyz");
    assertEquals(3, result);
  }

  @Test
  public void testBuildMapWithEmptyInput() {
    String[] words = {};
    Map<Integer, Set<Integer>> result = WordChains.buildMap(words);
    assertEquals(0, result.size());
  }

  @Test
  public void testBuildMapWithOneWord() {
    String[] words = {"hello"};
    Map<Integer, Set<Integer>> result = WordChains.buildMap(words);
    assertEquals(0, result.size());
  }

  @Test
  public void testBuildMapWithTwoWords() {
    String[] words = {"hello", "hallo"};
    Map<Integer, Set<Integer>> result = WordChains.buildMap(words);
    assertEquals(2, result.size());
    assertEquals(1, result.get(0).size());
    assertEquals(1, result.get(1).size());
  }

  @Test
  public void testBuildMapWithMultipleWords() {
    String[] words = {"hello", "hallo", "hullo", "holla", "helloo", "hell"};
    Map<Integer, Set<Integer>> result = WordChains.buildMap(words);
    assertEquals(6, result.size());
    assertEquals(4, result.get(0).size());
    assertEquals(1, result.get(5).size());
  }

  @Test
  public void testWordChains() {
    String[] words1 = {"coat", "hat", "hot", "dog", "cat", "hog", "cot", "oat"};
    ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent1));
    WordChains.wordChains(words1);
    String expectedOutput1 =
            "1. megoldás: cat cot coat oat hat hot hog dog" + System.lineSeparator() +
                    "2. megoldás: cat hat oat coat cot hot hog dog" + System.lineSeparator() +
                    "3. megoldás: coat cot cat oat hat hot hog dog" + System.lineSeparator() +
                    "4. megoldás: coat oat hat cat cot hot hog dog" + System.lineSeparator() +
                    "5. megoldás: cot cat coat oat hat hot hog dog" + System.lineSeparator() +
                    "6. megoldás: cot coat cat oat hat hot hog dog" + System.lineSeparator() +
                    "7. megoldás: cot coat oat cat hat hot hog dog" + System.lineSeparator() +
                    "8. megoldás: dog hog hot cot cat coat oat hat" + System.lineSeparator() +
                    "9. megoldás: dog hog hot cot cat hat oat coat" + System.lineSeparator() +
                    "10. megoldás: dog hog hot cot coat cat hat oat" + System.lineSeparator() +
                    "11. megoldás: dog hog hot cot coat cat oat hat" + System.lineSeparator() +
                    "12. megoldás: dog hog hot cot coat oat cat hat" + System.lineSeparator() +
                    "13. megoldás: dog hog hot cot coat oat hat cat" + System.lineSeparator() +
                    "14. megoldás: dog hog hot hat cat cot coat oat" + System.lineSeparator() +
                    "15. megoldás: dog hog hot hat cat oat coat cot" + System.lineSeparator() +
                    "16. megoldás: dog hog hot hat oat cat coat cot" + System.lineSeparator() +
                    "17. megoldás: dog hog hot hat oat cat cot coat" + System.lineSeparator() +
                    "18. megoldás: dog hog hot hat oat coat cat cot" + System.lineSeparator() +
                    "19. megoldás: dog hog hot hat oat coat cot cat" + System.lineSeparator() +
                    "20. megoldás: hat cat oat coat cot hot hog dog" + System.lineSeparator() +
                    "21. megoldás: hat oat cat coat cot hot hog dog" + System.lineSeparator() +
                    "22. megoldás: hat oat coat cat cot hot hog dog" + System.lineSeparator() +
                    "23. megoldás: oat coat cot cat hat hot hog dog" + System.lineSeparator() +
                    "24. megoldás: oat hat cat coat cot hot hog dog" + System.lineSeparator();
    assertEquals(expectedOutput1, outContent1.toString());
  }

  @Test
  public void testWordChains2() {
    String[] words2 = {"A01", "1000", "ABC", "101", "A0C", "1001", "ABD", "AB"};
    ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent2));
    WordChains.wordChains(words2);
    String expectedOutput2 = "1. megoldás: 1000 1001 101 A01 A0C ABC AB ABD" + System.lineSeparator() +
            "2. megoldás: 1000 1001 101 A01 A0C ABC ABD AB" + System.lineSeparator() +
            "3. megoldás: AB ABD ABC A0C A01 101 1001 1000" + System.lineSeparator() +
            "4. megoldás: ABD AB ABC A0C A01 101 1001 1000" + System.lineSeparator();
    assertEquals(expectedOutput2, outContent2.toString());
  }

  @Test
  public void testWordChains3() {
    String[] words3 = {"alma", "körte", "barack"};
    ByteArrayOutputStream outContent3 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent3));
    WordChains.wordChains(words3);
    String expectedOutput3 = "hiba: a megadott szavakból nem lehetséges szóláncot építeni!" + System.lineSeparator();
    assertEquals(expectedOutput3, outContent3.toString());

  }

}
