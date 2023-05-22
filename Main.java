import Naive.SolutionNaive;
import Optimized.SolutionOptimized;

import java.io.*;
import java.util.*;

public class Main {
    public static String randomGenerator(int length) {

            int leftLimit = 48; // numeral '0'
            int rightLimit = 122; // letter 'z'
//        int leftLimit = 97; // numeral '0'
//        int rightLimit = 122; // letter 'z'

        Random random = new Random();

            return random.ints(leftLimit, rightLimit + 1)
                    .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                    .limit(length)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();
    }

    public static ArrayList<String> randomGenerators(int length, int nOfString) {
        ArrayList<String> test = new ArrayList<>();
        for (int i = 0; i < nOfString; i++) {
            test.add(randomGenerator(length));
        }
        return test;
    }

    public static void randomTest() {
        HashMap<Integer,ArrayList<String>> test = null;
        boolean successfulRead = true;
        try {
            FileInputStream fileInput = new FileInputStream(
                    "test.txt");

            ObjectInputStream objectInput
                    = new ObjectInputStream(fileInput);

            test = (HashMap) objectInput.readObject();

            objectInput.close();
            fileInput.close();
        } catch (IOException e) {
            successfulRead = false;
        } catch (ClassNotFoundException obj2) {
            System.out.println("Class not found");
            obj2.printStackTrace();
            return;
        }


        if (!successfulRead) {
            int sampleSize = 10;
            test = new HashMap<>();

            int[] lengths = new int[]{1, 10, 50, 100, 1000, 10000, 100000, 1000000};

            for (int i = 0; i < lengths.length; i++) {
                test.put(lengths[i], randomGenerators(lengths[i], sampleSize));
            }

            try {
                FileOutputStream myFileOutStream
                        = new FileOutputStream(
                        "test.txt");

                ObjectOutputStream myObjectOutStream
                        = new ObjectOutputStream(myFileOutStream);

                myObjectOutStream.writeObject(test);

                // closing FileOutputStream and
                // ObjectOutputStream
                myObjectOutStream.close();
                myFileOutStream.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        SolutionOptimized solutionOptimized = new SolutionOptimized();
        SolutionNaive solutionNaive = new SolutionNaive();
        int sampleSize = test.get(1).size();

        ArrayList<Integer> a = (new ArrayList<>(test.keySet().stream().toList()));
        Collections.sort(a);

        for (Integer i : a) {
            System.out.format("Testing string %d characters long\n", i);
            long startTime = System.nanoTime();
            ArrayList<String> tested = test.get(i);
            for (String s : tested) {
                solutionNaive.lengthOfLongestSubstring(s);
            }
            long average = (System.nanoTime() - startTime) / sampleSize;
            System.out.format("Average of naive : %d\n", average);

            startTime = System.nanoTime();
            for (String s : tested) {
                solutionOptimized.lengthOfLongestSubstring(s);
            }

            average = (System.nanoTime() - startTime) / sampleSize;
            System.out.format("Average of optimized : %d\n" , average);

            for (String s : tested) {
                for (int j = 0; j < s.length(); j++) {
                    char k = s.charAt(j);
                }
            }
            average = (System.nanoTime() - startTime) / sampleSize;

            System.out.format("Average of just traversal : %d\n" , average);



            System.out.println();
        }
    }

    public static void englishTest() {

    }

    public static void main(String[] args) {
        randomTest();
    }
}
