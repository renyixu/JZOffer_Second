package JzOffer_Second;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/6/22 9:39
 */
public class Day02 {
    private static List<Integer> num = new ArrayList<>();

    //    1题号：JZ32
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0)
            return "";
        String[] str = new String[numbers.length];
        for (int i = 0; i < numbers.length; ++i)
            str[i] = numbers[i] + "";

        //排序
        for (int i = numbers.length - 1; i >= 1; --i) {
            for (int j = 0; j < i; ++j) {
                if ((str[j] + str[j + 1]).compareTo(str[j + 1] + str[j]) > 0) {
                    String t = str[j];
                    str[j] = str[j + 1];
                    str[j + 1] = t;
                }
            }
        }

        String res = "";
        for (String s : str)
            res += s;
        return res;
    }

    //2题号:JZ30
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0)
            return 0;
        int[] num = new int[array.length];
        num[0] = array[0];
        for (int i = 1; i < num.length; ++i) {
            if (num[i - 1] < 0)
                num[i] = array[i];
            else
                num[i] = num[i - 1] + array[i];
        }
        int max = num[0];
        for (int i : num) {
            if (i > max)
                max = i;
        }
        return max;
    }

    //3题号:JZ28
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null || array.length == 0)
            return 0;
        int num = array[0], count = 1;
        for (int i = 1; i < array.length; ++i) {
            if (array[i] != num) {
                --count;
                if (count == 0) {
                    num = array[i];
                    count = 1;
                }
            } else {
                ++count;
            }
        }
        count = 0;
        for (int i = 0; i < array.length; ++i)
            if (array[i] == num)
                ++count;
        return count > array.length / 2 ? num : 0;
    }

    //4题号:JZ13
    public void reOrderArray(int[] array) {
        int i = getFirstOuShu(array);
        int j = getFirstJiShuStartI(array, i);
        int len = array.length;
        while (i < len && j < len) {
            int t = array[j];
            //把i~j-1后移一位
            for (int k = j - 1; k >= i; --k) {
                array[k + 1] = array[k];
            }
            array[i] = t;
            i = getFirstOuShu(array);
            j = getFirstJiShuStartI(array, i);
        }
    }

    //5题号:JZ6，本题略

    private int getFirstJiShuStartI(int[] array, int i) {
        for (int j = i + 1; j < array.length; ++j)
            if (array[j] % 2 != 0)
                return j;
        return array.length;
    }

    private int getFirstOuShu(int[] array) {
        for (int i = 0; i < array.length; ++i)
            if (array[i] % 2 == 0)
                return i;
        return array.length;
    }

    //6题号:JZ1
    public boolean Find(int target, int[][] array) {
        if (array == null || array[0].length == 0)
            return false;
        int i = 0, j = array[0].length - 1;
        while (i < array.length && j >= 0) {
            if (target < array[i][j])
                --j;

            else if (target > array[i][j])
                ++i;
            else
                return true;
        }
        return false;
    }

}
