package JzOffer_Second;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/6/23 14:27
 */
public class Day03 {
    //洗牌复盘
    private static ArrayList<Integer> num = new ArrayList<>();
    private static int mid = -1;
    private static int n = -1;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        int k = s.nextInt();
        for (int m = 0; m < n; ++m)
            num.add(s.nextInt());
        if (n % 2 != 0)
            mid = n / 2;
        else
            mid = n / 2 - 1;
        xiPai(1, k);
        for (int nn : num)
            System.out.print(nn + " ");
    }

    private static void xiPai(int i, int k) {
        if (i > k)
            return;
        int left = mid, right = n - 1;
        ArrayList<Integer> list = new ArrayList<>();
        if (i % 2 != 0) {
            //奇数次，先右后左
            boolean r = true;
            while (left >= 0 && right > mid) {
                if (r == true) {
                    list.add(num.get(right--));
                    r = false;
                } else {
                    list.add(num.get(left--));
                    r = true;
                }
            }

            //处理剩余的元素
            while (left >= 0) {
                list.add(num.get(left--));
            }
        } else {
            //偶数次，先左后右
            boolean r = false;
            while (left >= 0 && right > mid) {
                if (r == true) {
                    list.add(num.get(right--));
                    r = false;
                } else {
                    list.add(num.get(left--));
                    r = true;
                }
            }

            //处理剩余的元素
            while (left >= 0) {
                list.add(num.get(left--));
            }
            while (right > mid) {
                list.add(num.get(right--));
            }
        }
        num.clear();
        for (int j = n - 1; j >= 0; --j)
            num.add(list.get(j));
        //递归
        xiPai(++i, k);
    }
}
