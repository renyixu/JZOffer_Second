package JzOffer_Second;

import java.util.ArrayList;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/7/12 14:36
 */
public class Day05 {
    public static void main(String[] args) {
        System.out.println(new Day05().Add(5, 6));
    }

    //1号题：JZ-9
    //变态跳台阶问题
    public int JumpFloorII(int target) {
        if (target == 0)
            return 0;
        if (target == 1)
            return 1;
        int res = 0;
        if (target >= 2) {
            for (int i = 1; i < target; ++i)
                res += JumpFloorII(i);
        }
        return res + 1;
    }

    //2号题：JZ-33
    //求一个连续的正数其和为s
    //思路：复杂度低一点的方法有滑动窗口，设置两个指针
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        if (sum <= 0)
            return list;
        int low = 1, high = 2;
        while (low < high) {
            //等差数列求和公式为(a0+an)*n/2
            double currentSum = (low + high) * (high - low + 1) / 2;
            if (currentSum == sum) {
                ArrayList<Integer> curList = new ArrayList<>();
                for (int i = low; i <= high; ++i)
                    curList.add(i);
                list.add(curList);
                ++low;
            } else if (currentSum < sum)
                ++high;
            else if (currentSum > sum)
                ++low;
        }
        return list;
    }

    //3号题:JZ-29
    //找出一个数组中最小的k个数字
    //思路：使用堆排序
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || input.length == 0 || k <= 0 || k > input.length)
            return list;
        int mid = -1;
        if (input.length % 2 == 0)
            mid = input.length - 1 / 2 - 1;
        else
            mid = input.length - 1 / 2;
        //调整成小顶锥
        for (int i = mid; i >= 0; --i) {
            sift(input, i, input.length - 1);
        }
        int size = input.length - 1;
        for (int i = 1; i <= k; ++i) {
            list.add(input[0]);
            input[0] = input[size--];
            sift(input, 0, size);
        }
        return list;
    }

    //调整low位置处的数字，将其调整成小顶锥
    private void sift(int[] input, int low, int high) {
        int i = low, j = 2 * i + 1;
        int t = input[low]; //暂存low
        while (j <= high) {
            //将小的那一个赋给j
            if (j + 1 <= high && input[j + 1] < input[j])
                ++j;
            if (input[j] < t) {
                input[i] = input[j];
                i = j;
                j = 2 * i + 1;
            } else
                break;
        }
        input[i] = t;
    }

    //4号题：JZ-65
    //矩阵中的路径，已在路径中的单个字符不能再次在路径中
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        //将一维数组转换成二维数组
        char[][] chArray = new char[rows][cols];
        int pos = 0;
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j) {
                chArray[i][j] = matrix[pos++];
            }
        int visit[][] = new int[chArray.length][chArray[0].length];
        for (int i = 0; i < rows; ++i)
            for (int j = 0; j < cols; ++j) {
                //回溯法
                if (getAnswer(chArray, i, j, 0, str, visit))
                    return true;
            }
        return false;
    }

    private boolean getAnswer(char[][] chArray, int row, int col, int p, char[] str, int visit[][]) {
        if (p == str.length)
            //表示str前p-1个字符全部匹配，即存在
            return true;
        //下标范围合法性判断以及字符是否相等判断
        if (row < 0 || row > chArray.length - 1 || col < 0 || col > chArray[0].length - 1 || str[p] != chArray[row][col] || visit[row][col] == 1)
            return false;

        //标记已访问
        visit[row][col] = 1;

        int pos[][] = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        for (int[] m : pos) {
            if (getAnswer(chArray, row + m[0], col + m[1], p + 1, str, visit))
                return true;
        }
        //还原标记，因为不匹配
        visit[row][col] = 0;
        return false;
    }

    //5号题：JZ-48
    //写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
    public int Add(int num1, int num2) {
        int nowNum = 0;
        while (num1 != 0) {
            //计算不进位的结果
            nowNum = num1 ^ num2;//异或
            //计算进位
            num1 = (num1 & num2) << 1; //按位与后左移一位
            num2 = nowNum;
        }
        return num2;
    }
}
