package JzOffer_Second;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/6/21 7:56
 */
public class Day01 {
    private static int count = 0;

    public static void main(String[] args) {
        new Day01().InversePairs(new int[]{1, 2, 3, 4, 5, 6, 7, 0});
        System.out.println(count);
    }

    //1、题号：JZ51
    public int[] multiply(int[] A) {
        int B[] = new int[A.length];
        for (int i = 0; i < A.length; ++i) {
            B[i] = getRes(A, i);
        }
        return B;
    }

    private int getRes(int[] a, int i) {
        int res = 0;
        res = conMultiply(a, 0, i - 1);
        res *= conMultiply(a, i + 1, a.length - 1);
        return res;
    }

    private int conMultiply(int[] a, int low, int high) {
        if (low <= high)
            return a[low] * conMultiply(a, low + 1, high);
        return 1;
    }

    //2、题号：JZ50
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        if (numbers == null || numbers.length == 0 || length == 0)
            return false;
        TreeSet<Integer> set = new TreeSet<>();
        for (int i : numbers) {
            if (set.add(i) == false) {
                duplication[0] = i;
                return true;
            }
        }
        return false;
    }

    //3、题号:JZ40-常规解法
    public void FindNumsAppearOnce_V1(int[] array, int num1[], int num2[]) {
        int c = -1;
        int num[] = new int[2];
        Arrays.sort(array);
        for (int i = 0; i < array.length - 1; ++i) {
            if (array[i] != array[i + 1]) {
                num[++c] = array[i];
            } else
                ++i;
            if (c == 1)
                break;
        }
        num1[0] = num[0];
        if (c == 0) {
            num2[0] = array[array.length - 1];
        } else {
            num2[0] = num[1];
        }
    }

    //4、题号:JZ40-技术流解法，位运算
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        int res = 0;
        for (int i = 0; i < array.length; ++i) {
            res ^= array[i];
        }
        int pos = findFirstOne(res); //寻找res最低位1的位置
        //根据pos为是否为1进行分组
        for (int i = 0; i < array.length; ++i) {
            if (isPosBit(array[i], pos)) {
                num1[0] ^= array[i];
            } else {
                num2[0] ^= array[i];
            }
        }
    }

    //判断num的pos位是否为1
    private boolean isPosBit(int num, int pos) {
        if (((num >> pos) & 1) == 1)
            return true;
        return false;
    }

    private int findFirstOne(int res) {
        int index = 0;
        while ((res & 1) == 0) {
            res >>= 1;
            ++index;
        }
        return index;
    }

    //5、题号:JZ37，本题略


    //6、题号:JZ35
    public int InversePairs(int[] array) {
        if (array == null || array.length == 0)
            return 0;
        gbSort(array, 0, array.length - 1); //归并排序
        return count % 1000000007;
    }

    private void gbSort(int num[], int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            gbSort(num, low, mid);
            gbSort(num, mid + 1, high);
            merge(num, low, mid, high); //合并
        }
    }

    private void merge(int[] num, int low, int mid, int high) {
        int k = low - 1;
        int a[] = new int[num.length];
        int i = low, j = mid + 1;
        while (i <= mid && j <= high) {
            if (num[i] <= num[j]) {
                a[++k] = num[i++];
            } else {
                //逆序
                count += mid + 1 - i;
                a[++k] = num[j++];
            }
        }
        while (i <= mid)
            a[++k] = num[i++];
        while (j <= high)
            a[++k] = num[j++];
        for (i = low; i <= high; ++i)
            num[i] = a[i];
    }
}
