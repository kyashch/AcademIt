package ru.academits.yashch.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        ArrayList<String> recordList = new ArrayList<>();

        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            while (scanner.hasNextLine()) {
                recordList.add(scanner.nextLine());
            }

            System.out.println(recordList);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        }

        ArrayList<Integer> integersListWithoutEvenNumbers = new ArrayList<>(Arrays.asList(4, 0, 8, 1, 5, 3, 1, 8, 3, 4, 5));

        for (int i = 0; i < integersListWithoutEvenNumbers.size(); ++i) {
            if (integersListWithoutEvenNumbers.get(i) % 2 == 0) {
                integersListWithoutEvenNumbers.remove(i);

                --i;
            }
        }

        System.out.println(integersListWithoutEvenNumbers);

        ArrayList<Integer> integersListWithRepetitions = new ArrayList<>(Arrays.asList(9, 1, 3, 6, 8, 1, 6, 3, 7, 8, 9));
        ArrayList<Integer> integersListWithoutRepetitions = new ArrayList<>();

        for (Integer e : integersListWithRepetitions) {
            if (!integersListWithoutRepetitions.contains(e)) {
                integersListWithoutRepetitions.add(e);
            }
        }

        System.out.println(integersListWithoutRepetitions);
    }
}
