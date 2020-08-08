package LeetCode;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

//广联达编程题复盘
public class GLD_FP {
    public static void main(String[] args) {
         /*
        题1：给出你n条长度不一的边，请你从中选择四条边，
        组成一个最大的平行四边形。请你输出最大的平行四边形的面积。
        输入描述：第一行输入的是有多少个数
        第二行输入的是这n个数字
         */
//        Scanner scanner = new Scanner(System.in);
//        int n = scanner.nextInt();
//        int num[] = new int[n];
//        for (int i = 0; i < n; ++i)
//            num[i] = scanner.nextInt();
//        Arrays.sort(num);  //排序
//        int a = 0, b = 0;
//        for (int i = n - 1; i >= 1; --i) {
//            if (a != 0 && b != 0) {
//                break;
//            }
//
//            if (num[i] == num[i - 1]) {
//                if (a == 0)
//                    a = num[i];
//                else if (b == 0)
//                    b = num[i];
//                --i;
//            }
//        }
//        if (a != 0 && b != 0)
//            System.out.println(a * b);
//        else
//            System.out.println(-1);
        /*题2：有一种排序算法定义如下，该排序算法每次只能把一个元素提到序列的开头，
        例如2，1，3，4，只需要一次操作把1提到序列起始位置就可以使得原序列从小到大有序。
        现在给你个乱序的1-n的排列，请你计算最少需要多少次操作才可以使得原序列从小到大有序。
         */
//        Scanner s = new Scanner(System.in);
//        int n = s.nextInt();
//        int data[] = new int[n];
//        int num[] = new int[n];
//        for (int i = 0; i < n; ++i) {
//            data[i] = num[i] = s.nextInt();
//        }
//        Arrays.sort(num); //对num排序
//        //将data与num数组进行比较，统计从后向前data中有几个数字是按照num数组中的顺序排列的
//        int i = n - 1, j = n - 1;
//        int count = 0;
//        while (i >= 0 && j >= 0) {
//            //寻找data中与当前num[j]相等的数字，找到即可退出
//            while (i >= 0) {
//                if (data[i] == num[j]) {
//                    ++count;
//                    break;
//                } else
//                    --i;
//            }
//            --j;
//        }
//        System.out.println(n - count);

        /*
        题3：在一个2D横版游戏中，所有的怪物都是在X轴上的，每个怪物有两个属性X和HP，
        分别代表怪物的位置和生命值。
        玩家控制的角色有一个AOE（范围攻击）技能，玩家每次释放技能可以选择一个位置x，
        技能会对[x-y,x+y]范围内的所有怪物造成1点伤害，请问，玩家最少需要使用多少次技能，
        才能杀死所有怪物，怪物血量清0即视为被杀死。

        思路：先对怪物从小到大排序，每次从消灭最左的怪物，
        在[最左怪物的位置，最左怪物位置 + 2 * y]区间的怪物也一起掉血。
        然后再找下一个血量大于0的怪物，直到消灭所有怪物。
        原理：你肯定需要消灭最左的怪物->消灭最左怪物的点肯定在[最左怪物位置，
        最左怪物位置+y]之间 -> 所以选择[最左怪物的位置，
        最左怪物位置 + 2 * y]区间的怪物也一起掉血，直到消灭所有怪物
         */
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int y = s.nextInt();
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < n; ++i) {
            treeMap.put(s.nextInt(), s.nextInt());
        }
        //思路：贪心算法，从最左边的怪物开始，范围是[x1,x1+2y]，每次都从最左边的怪物开始
        //杀死最左边的怪物后，寻找下一个“最左边”血量大于0的怪物，直到消灭所有的怪物
        int count = 0;
        while (!isKillAllGW(treeMap)) {
            //寻找最左边的怪物
            int x = findLeftGW(treeMap);
            boolean flag = false;
            for (int key : treeMap.keySet()) {
                if (key >= x && key <= x + 2 * y && treeMap.get(key) > 0) {
                    flag = true;
                    int value = treeMap.get(key);
                    --value;
                    treeMap.put(key, value > 0 ? value : 0);
                }
            }
            if (flag == true)
                ++count;
        }
        System.out.println(count);
    }

    private static int findLeftGW(TreeMap<Integer, Integer> map) {
        for (int key : map.keySet()) {
            if (map.get(key) > 0)
                return key;
        }
        return -1;
    }

    public static boolean isKillAllGW(TreeMap<Integer, Integer> map) {
        for (int key : map.keySet()) {
            if (map.get(key) > 0)
                return false;
        }
        return true;

        
    }
}
