/**
 * Класс FenwickStructure реализует дерево Фенвика (Fenwick Tree / Binary Indexed Tree).
 * Поддерживает:
 *  - построение дерева по массиву
 *  - обновление одного элемента
 *  - вычисление prefixSum
 *  - вычисление суммы на диапазоне
 *
 * В реализации используются только массивы, без коллекций.
 */
public class FenwickStructure {

    private int length;
    private int[] fenwickTree;

    /**
     * Построение Fenwick Tree по исходному массиву.
     * Время: O(n log n).
     *
     * Используется формула:
     * next = i | (i + 1)
     */
    public void build(int [] sourceArray) {

        length = sourceArray.length;
        fenwickTree = new int[length];
        for(int i = 0; i < length; i ++){
            int count = i;

            // Добавляем значение sourceArray[i] во все связанные элементы Fenwick Tree
            while (count < length){
                fenwickTree[count] += sourceArray[i];
                count = (count | (count + 1));
            }
        }
    }

    /**
     * Печать дерева Фенвика (необязательно по заданию, но удобно для визуализации).
     */
    public void getFenwickTree() {
        System.out.println("Дерево Фенвика:");

        System.out.print("index: ");
        for(int i = 0; i < fenwickTree.length; i++){
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.print("value: ");
        for(int i = 0; i < fenwickTree.length; i++){
            System.out.print(fenwickTree[i] + " ");
        }
        System.out.println();
    }

    /**
     * Обновление элемента массива:
     * мы передаём delta — разницу между старым и новым значением.
     *
     * Движение происходит по формуле:
     * next = i | (i + 1)
     */
    public void update(int index, int delta)
    {
        //обновляем элемент в самом массиве
        int count = index;
        while (count < length){
            fenwickTree[count] += delta;
            count = (count | count + 1);
        }

    }

    /**
     * Вычисляет prefix sum на диапазоне [0; index].
     * Использует формулу движения назад:
     * prev = (i & (i + 1)) - 1
     */
    public int prefixSum(int index)
    {
        if(index >= length || index < 0){
            throw new IllegalArgumentException("Индекс выходит за длину массива: "  + index);
        }
        else{
            int result = 0;
            while (index >= 0){
                result += fenwickTree[index];
                index = (index & (index +1)) - 1; // переход к предыдущему узлу Fenwick Tree
            }
            return result;
        }
    }

    /**
     * Сумма на диапазоне [left; right].
     * sum(l, r) = prefixSum(r) - prefixSum(l - 1)
     */
    public int rangeSum(int left, int right) {
        if (left > right || left < 0 || right >= length) {
            throw new IllegalArgumentException("Неверный диапозон: [" + left + ", " + right + "]");
        } else if (left == 0) {
            return prefixSum(right);
        }
        else{
            return prefixSum(right)  - prefixSum(left-1);
        }
    }
}
