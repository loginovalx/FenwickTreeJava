import java.util.Scanner;

/**
 * Основной класс программы.
 * Содержит консольное меню и взаимодействие пользователя с деревом Фенвика.
 */

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FenwickStructure tree = new FenwickStructure(); // структура для работы с Fenwick Tree
        boolean play = true; //true до момента, пока user на нажмет кнопку выход

        int[] array = null; // исходный массив, по которому строится дерево

        while(play){
            System.out.println("_____Меню дерева Фенвика_____");
            System.out.println("___________Функции___________");
            System.out.println("1. build (построить дерево)");
            System.out.println("2. update (обновить значение)");
            System.out.println("3. prefixSum (вывести сумму с начала массива до определенного индекса)");
            System.out.println("4. rangeSum (вывести сумму на определенном отрезке)");
            System.out.println("0. выйти");
            System.out.print("Введите номер команды:");

            // Чтение команды с обработкой ошибок ввода
            int choose;
            while (true){
                try {
                    choose = in.nextInt();
                    in.nextLine();
                    break;
                }catch (Exception e){
                    System.out.print("Ошибка. Введите целое число: ");
                    in.nextLine();
                }
            }

            // Обработка выбора пользователя
            switch (choose){

                //Построение дерева
                case 1:
                    System.out.println("Для начала создадим массив, по которому будет строиться дерево Фенвика:");
                    array = SourceArray.buildArray(); // ввод массива
                    tree.build(array); // построение дерева Fenwick
                    SourceArray.printArray(array); // вывод массива
                    System.out.println();
                    tree.getFenwickTree(); // вывод дерева
                    System.out.println();
                    break;

                //Обновление элемента
                case 2:
                    if (array == null) {
                        System.out.println("Сначала постройте дерево (пункт 1)");
                        System.out.println();
                        break;
                    }
                    System.out.println("Укажите индекс и новое значение.");
                    System.out.print("Введите индекс:"); // Ввод индекса
                    int index;
                    while (true) {
                        try {
                            index = in.nextInt();
                            if (index < 0 || index >= array.length) {
                                System.out.print("Индекс должен принадлежать диапозону [0; " + (array.length - 1) + "]. Введите индекс: ");
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            System.out.print("Ошибка. Введите целое число >= 0: ");
                            in.nextLine();
                        }
                    }

                    System.out.print("Введите новое значение:"); //ввод нового значения
                    int value;
                    while (true) {
                        try {
                            value = in.nextInt();
                            break;
                        } catch (Exception e) {
                            System.out.print("Ошибка. Введите целое  число: ");
                            in.nextLine();
                        }
                    }

                    // Вычисляем изменение delta и обновляем дерево
                    int delta = SourceArray.updateArray(array, index, value);
                    tree.update(index, delta);
                    System.out.println();
                    System.out.println("Обновленный массив и дерево Фенвика:");
                    System.out.println();
                    SourceArray.printArray(array);
                    System.out.println();
                    tree.getFenwickTree();
                    System.out.println();
                    break;


                //prefixSum
                case 3:
                    if (array == null) {
                        System.out.println("Сначала постройте дерево (пункт 1)");
                        System.out.println();
                        break;
                    }
                    int indexPrefix;
                    System.out.print("Введите индекс для prefixSum: ");

                    // Ввод корректного индекса
                    while (true) {
                        try {
                            indexPrefix = in.nextInt();
                            if (indexPrefix < 0 || indexPrefix >= array.length) {
                                System.out.print("Индекс должен принадлежать диапозону [0; " + (array.length - 1) + "]. Введите индекс: ");
                                continue;
                            }
                            break;
                        } catch (Exception e) {
                            System.out.print("Ошибка. Введите целое число >= 0: ");
                            in.nextLine();
                        }
                    }

                    // Вывод результата
                    System.out.println("Сумма отрезка [0;" + indexPrefix + "] = " + tree.prefixSum(indexPrefix));
                    System.out.println();
                    break;


                //rangeSum
                case 4:
                    if (array == null) {
                        System.out.println("Сначала постройте дерево (пункт 1)");
                        System.out.println();
                        break;
                    }

                    int left, right;
                    System.out.print("Введите левую и правую границы(левый и правый индексы) отрезка для rangeSum (через пробел): ");

                    // Ввод корректных границ
                    while (true) {
                        try {
                            left = in.nextInt();
                            right = in.nextInt();

                            // проверка границ и порядка
                            if (left < 0 || right < 0 ||
                                    left >= array.length || right >= array.length ||
                                    left > right) {

                                System.out.print(
                                        "Неверный диапазон. " +
                                                "Индексы должны принадлежать [0; " + (array.length - 1) +
                                                "] и left <= right. Введите границы ещё раз: "
                                );
                                continue;
                            }

                            break;

                        } catch (Exception e) {
                            System.out.print("Ошибка. Введите два целых числа через пробел: ");
                            in.nextLine();
                        }
                    }

                    // Вывод результата
                    System.out.println(
                            "Сумма отрезка [" + left + ";" + right + "] = " +
                                    tree.rangeSum(left, right)
                    );
                    System.out.println();
                    break;

                //выход
                case 0:
                    System.out.println("Выход из программы...");
                    play = false;
                    break;

                //некорректная программа
                default:
                    System.out.println("Неизвестная команда. Введите число от 0 до 4.");
                    break;

            }
        }
    }
}
