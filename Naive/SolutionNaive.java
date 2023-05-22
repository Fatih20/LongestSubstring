import java.util.HashMap;

class SolutionNaive {
    public int lengthOfLongestSubstring(String s) {
        int longest = 0;
        for (int i = 0; i < s.length(); i++) {
            int j = i;
            boolean foundRepeating = false;
            StringBuilder sb = new StringBuilder();
            HashMap<Character, Boolean> table = new HashMap<>();
            while (!foundRepeating && j < s.length()) {
                if (table.containsKey(s.charAt(j))) {
                    foundRepeating = true;
                } else {
                    table.put(s.charAt(j), true);
                    sb.append(s.charAt(j));

                }

                j++;
            }
            if (sb.length() > longest) {
                longest = sb.length();
            }
            sb = new StringBuilder();
        }
        return longest;
    }
}