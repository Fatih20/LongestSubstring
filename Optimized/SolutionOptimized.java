import java.util.HashMap;

class SolutionOptimized {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() <= 1) {
            return s.length();
        }
        int longest = 0;
        boolean reachedTheEnd = false;
        int i = 0;
        int j = i;
        int currentLength = 1;
        HashMap<Character, Integer> table = new HashMap<>();
        table.put(s.charAt(i), 0);
        while (!reachedTheEnd) {
            j++;
            reachedTheEnd = j >= s.length() - 1;
            boolean sameCharacterFound = false;
            Character observed = s.charAt(j);
            Integer charValue = table.get(observed);
            if (charValue == null || charValue == -1) {
                table.put(observed, j);
                currentLength++;
            } else if (!reachedTheEnd) {
                int newI = table.get(observed) + 1;
                for (Character c : table.keySet()) {
                    if (table.get(c) < newI) {
                        table.put(c, -1);
                    }
                }
                i = newI;
                System.out.println(s.charAt(i));
                table.put(observed, j);
                sameCharacterFound = true;
            }

            if (currentLength > longest) {
                longest = currentLength;
            }

            if (sameCharacterFound) {
                currentLength = j - i + 1;
            }

            reachedTheEnd = j >= s.length() - 1;

        }
        return longest;
    }
}