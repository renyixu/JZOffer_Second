package JzOffer_Second;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/7/11 20:09
 */
public class Day04 {
    private LinkedList<Integer> list = new LinkedList<>();

    public static void main(String[] args) {
//        System.out.println(new Day04().cutRope(8));
        File file = new File("G:\\abcd.jpg");
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //排序
    //1号题：JZ63
    //读取数据流
    public void Insert(Integer num) {
        list.add(num);
        //排序
        Collections.sort(list);
    }

    //获取当前数据流的中位数
    public Double GetMedian() {
        int size = list.size();
        if (size % 2 != 0)
            return Double.valueOf(list.get((size - 1) / 2));
        else
            return Double.valueOf(list.get(size / 2) + list.get(size / 2 - 1)) / 2;
    }

    //2号题：JZ10
    //矩形覆盖
    public int RectCover(int target) {
        if (target == 0)
            return 0;
        if (target == 1)
            return 1;
        if (target == 2)
            return 2;
        return RectCover(target - 1) + RectCover(target - 2);
    }

    //3号题：JZ8
    public int JumpFloor(int target) {
        if (target == 0)
            return 0;
        if (target == 1)
            return 1;
        if (target == 2)
            return 2;
        return JumpFloor(target - 1) + JumpFloor(target - 2);
    }

    //4号题：JZ7
    //输出斐波那契数列的第n项
    public int Fibonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    //5号题：JZ67
    //剪绳子
    public int cutRope(int target) {
        if (target == 2 || target == 3)
            return 2;
        if (target == 4)
            return 4;
        int res = 1;
        while (target > 4) {
            target -= 3;
            res *= 3;
        }
        return res * target;
    }
}
