package main.java.wordchain;

import java.util.*;

public class WordChains {

  public static void wordChains(String[] words) {
    Map<Integer, Set<Integer>> map = buildMap(words);
    Set<List<Integer>> result = new HashSet<>();
    for (Map.Entry<Integer, Set<Integer>> e : map.entrySet()) {
      List<Integer> processed = new ArrayList<>();
      processed.add(e.getKey());
      recurseWordChains(map, e.getValue(), processed, result);
    }

    if (result.size() == 0) {
      System.out.println("hiba: a megadott szavakból nem lehetséges szóláncot építeni!");
    }
    List<String> output = new ArrayList<>();
    for (List<Integer> list : result) {
      StringBuilder sb = new StringBuilder();
      for (int i : list) {
        sb.append(words[i]).append(" ");
      }
      output.add(sb.toString().trim());
    }
    Collections.sort(output);

    int count = 1;
    for (String str : output) {
      System.out.println(count + ". megoldás: " + str);
      count++;
    }
  }

  public static Map<Integer, Set<Integer>> buildMap(String[] words) {
    Map<Integer, Set<Integer>> map = new HashMap<>();
    for (int i = 0; i < words.length; i++) {
      if (words.length <= 1) {
        continue;
      }
      map.put(i, new HashSet<>());
    }
    for (int i = 0; i < words.length; i++) {
      for (int j = 0; j < words.length; j++) {
        if (words[i].equalsIgnoreCase(words[j])) {
          continue;
        }
        if (editDistance(words[i].toLowerCase(), words[j].toLowerCase()) == 1) {
          map.get(i).add(j);
        }
      }
    }
    return map;
  }

  public static int editDistance(String str1, String str2) {
    str1 = str1.toLowerCase();
    str2 = str2.toLowerCase();
    int lenStr1 = str1.length();
    int lenStr2 = str2.length();
    if (lenStr1 == 0) {
      return lenStr2;
    }
    if (lenStr2 == 0) {
      return lenStr1;
    }
    int cost = 0;
    if (!str1.substring(lenStr1 - 1).equals(str2.substring(lenStr2 - 1))) {
      cost = 1;
    }
    return minimum(editDistance(str1.substring(0, lenStr1 - 1), str2) + 1,
            editDistance(str1, str2.substring(0, lenStr2 - 1)) + 1,
            editDistance(str1.substring(0, lenStr1 - 1), str2.substring(0, lenStr2 - 1)) + cost);
  }

  public static int minimum(int i1, int i2, int i3) {
    return Math.min(Math.min(i1, i2), i3);
  }

  public static void recurseWordChains(Map<Integer, Set<Integer>> map,
                                       Set<Integer> set, List<Integer> processed, Set<List<Integer>> result) {
    if (processed.size() == map.size()) {
      result.add(processed);
    }

    for (int i : set) {
      if (processed.contains(i)) {
        continue;
      }
      List<Integer> newList = new ArrayList<>(processed);
      newList.add(i);
      recurseWordChains(map, map.get(i), newList, result);
    }
  }

}
