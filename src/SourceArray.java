import java.util.Scanner;

/**
 * Вспомогательный класс для работы с исходным массивом:
 * - ввод массива
 * - вывод массива
 * - обновление элемента и расчёт delta
 */

public class SourceArray {

    /**
     * Создаёт массив на основе пользовательского ввода.
     * Содержит защиту от некорректного формата данных.
     */
    public static  int[] buildArray() {
        Scanner in = new Scanner(System.in);
        int size;

        // Чтение размера массива
        while (true) {
            System.out.print("Введите размер массива: ");
            try {
                size = in.nextInt();
                if (size <= 0) {
                    System.out.println("Размер должен быть > 0");
                    continue;
                }
                break;
            } catch (Exception e) {
                System.out.println("Ошибка. Введите натуральное число!");
                in.nextLine();
            }
        }

        int[] arr = new int[size];

        // читаем сами элементы
        while (true) {
            System.out.println("Введите " + size + " целых чисел (в ряд через пробел):");
            boolean ok = true;

            for (int i = 0; i < size; i++) {
                try {
                    arr[i] = in.nextInt();
                } catch (Exception e) {
                    System.out.print("Ошибка. ");
                    in.nextLine();
                    ok = false;
                    break;
                }
            }

            if (ok) {
                break;
            }
        }

        return arr;
    }

    /**
     * Печатает индекс и значение элементов массива.
     */
    public static void printArray(int[] array){
        System.out.println("Массив:");

        System.out.print("index: ");
        for(int i = 0; i < array.length; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("value: ");
        for(int i = 0; i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * Обновляет элемент массива и возвращает delta (разницу),
     * которую нужно применить к дереву Фенвика.
     */
    public static int updateArray(int[] array, int index, int value)
    {
        int delta = value - array[index];
        array[index] = value;
        return delta;
    }
}

