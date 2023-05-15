package test.java.wordchain;

import main.java.wordchain.StandardInput;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class StandardInputTest {

  @Test
  public void testStandardInput() {
    InputStream inputStream = new ByteArrayInputStream("hello world".getBytes());
    System.setIn(inputStream);

    String[] words = assertDoesNotThrow(StandardInput::standardInput);

    assertEquals(2, words.length);
    assertEquals("hello", words[0]);
    assertEquals("world", words[1]);
  }

}
