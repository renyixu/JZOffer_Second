package JzOffer_Second;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private ArrayList<String> list = new ArrayList<>();
    private TreeSet<String> set = new TreeSet();

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

    public static void main(String[] args) {
//        Scanner s = new Scanner(System.in);
//        n = s.nextInt();
//        int k = s.nextInt();
//        for (int m = 0; m < n; ++m)
//            num.add(s.nextInt());
//        if (n % 2 != 0)
//            mid = n / 2;
//        else
//            mid = n / 2 - 1;
//        xiPai(1, k);
//        for (int nn : num)
//            System.out.print(nn + " ");
        new Day03().shuziAllSort(new int[]{1, 2, 3, 4, 5, 6});

    }

    //1号题：JZ53
    //实现一个函数用来判断一个字符串是否是表示数值（包括整数和小数）
    public boolean isNumeric(char[] str) {
        String pattern = "[+-]?\\d*(\\.\\d*)?([Ee][+-]?\\d+)?";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(String.valueOf(str));
        return m.matches();
    }

    //2号题：JZ49
    //把字符串转换成整数
    public int StrToInt(String str) {
        String p = "[+-]?\\d+";
        Pattern pattern = Pattern.compile(p);
        boolean flag = pattern.matcher(str).matches();
        if (flag == false)
            return 0;
        char[] ch = str.toCharArray(); //转换成char数组
        int position = 0;
        if (ch[0] == '-')
            position = 1;
        int len = ch.length;
        //循环
        int res = 0;
        int pos = -1;
        for (int i = len - 1; i > -1; --i) {
            if (ch[i] == '+' || ch[i] == '-')
                break;
            res += (ch[i] - '0') * Math.pow(10, ++pos);
            if (res < Integer.MIN_VALUE || res > Integer.MAX_VALUE)
                return 0;
        }
        return position == 0 ? res : -res;
    }

    //3号题：JZ43
    //左旋转字符串
    //abcXYZdef，左旋转3后的结果为XYZdefabc
    public String LeftRotateString(String str, int n) {
        if (str == null || n < 0)
            return null;
        if (str.equals(""))
            return "";
        return str.substring(n) + str.substring(0, n);
    }

    //4号题：JZ27
    //字符串的排列（按照字典的顺序对一个自渡序列进行排列）
    //相当于全排列
    public ArrayList<String> Permutation(String str) {
        if ("".equals(str) || str == null || str.length() == 0)
            return list;
        //转换成数组
        char[] ch = str.toCharArray();
        //递归处理
        perm(ch, 0);
        for (String s : set)
            list.add(s);
        return list;
    }

    private void perm(char[] ch, int i) {
        //如果只有一个字符的时候，直接输出即可
        if (i == ch.length - 1) {
            set.add(String.valueOf(ch));
        } else {
            //循环把每个字符与当前i处的字符交换，再递归处理
            for (int j = i; j < ch.length; ++j) {
                char t = ch[i];
                ch[i] = ch[j];
                ch[j] = t;
                //递归处理
                perm(ch, i + 1);
                //再交换回来
                t = ch[i];
                ch[i] = ch[j];
                ch[j] = t;
            }
        }
    }

    //数字全排列
    public void shuziAllSort(int num[]) {
        Arrays.sort(num);
        //递归处理
        permNum(num, 0);
    }

    private void permNum(int[] num, int i) {
        if (i == num.length - 1) {
            for (int k : num)
                System.out.print(k);
            System.out.println();
        } else {
            for (int j = i; j < num.length; ++j) {
                //交换i和j
                int t = num[j];
                num[j] = num[i];
                num[i] = t;
                //递归处理
                permNum(num, i + 1);
                //再交换回来
                t = num[i];
                num[i] = num[j];
                num[j] = t;
            }
        }
    }

    //5号题：JZ1
    //从左到右以及从上到下都递增，查找有无指定的数字
    public boolean Find(int target, int[][] array) {
        int i = 0; //行标
        int j = array.length - 1; //列标
        while (i <= array[0].length - 1 && j >= 0) {
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
