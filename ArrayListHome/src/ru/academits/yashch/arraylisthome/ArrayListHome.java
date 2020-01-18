package ru.academits.yashch.arraylisthome;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayListHome {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream("input.txt"))) {
            ArrayList<String> fileLinesList = new ArrayList<>();

            while (scanner.hasNextLine()) {
                fileLinesList.add(scanner.nextLine());
            }

            System.out.println("Список строк файла: ");
            System.out.println(fileLinesList);
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден.");
        }

        ArrayList<Integer> integersList = new ArrayList<>(Arrays.asList(4, 0, 8, 1, 5, 3, 1, 8, 3, 4, 5));

        for (int i = 0; i < integersList.size(); ++i) {
            if (integersList.get(i) % 2 == 0) {
                integersList.remove(i);

                --i;
            }
        }

        System.out.println("Список из целых чисел без четных: ");
        System.out.println(integersList);

        ArrayList<Integer> integersListWithRepetitions = new ArrayList<>(Arrays.asList(9, 1, 3, 6, 8, 1, 6, 3, 7, 8, 9));
        ArrayList<Integer> integersListWithoutRepetitions = new ArrayList<>();

        for (Integer e : integersListWithRepetitions) {
            if (!integersListWithoutRepetitions.contains(e)) {
                integersListWithoutRepetitions.add(e);
            }
        }

        System.out.println("Список без повторений: ");
        System.out.println(integersListWithoutRepetitions);
    }
}
