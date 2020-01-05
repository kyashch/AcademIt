package ru.academits.yashch.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<String> list1 = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNextLine()) {
                list1.add(scanner.nextLine());
            }
        }

        System.out.println(list1);

        ArrayList<Integer> list2 = new ArrayList<>(Arrays.asList(1, 6, 7, 2, 5, 3, 1, 8, 3, 4, 5));

        for (int i = 0; i < list2.size(); ++i) {
            if (list2.get(i) % 2 == 0) {
                list2.remove(i);
            }
        }

        System.out.println(list2);

        ArrayList<Integer> list3 = new ArrayList<>(Arrays.asList(9, 1, 3, 6, 8, 1, 6, 3, 7, 8, 9));
        ArrayList<Integer> list4 = new ArrayList<>();

        for (int e : list3) {
            if (list4.contains(e)) {
                continue;
            }

            list4.add(e);
        }

        System.out.println(list4);
    }
}
