package JzOffer_Second;

import java.util.TreeSet;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/7/12 15:59
 */
public class TongJi {
    public static void main(String[] args) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        int[] num = {51, 50, 40, 37, 35,
                     32, 30, 28, 13, 6,
                     1, 53, 49, 43, 27,
                     1, 63, 10, 8, 7, 67};
        for (int n : num)
            treeSet.add(n);
        System.out.println("已刷"+treeSet.size()+"道题，具体如下：");
        for(Integer i:treeSet)
            System.out.print(i+" ");
        System.out.println("还有"+(67-treeSet.size())+"道题没刷，具体如下：");
        for(int i=1;i<=67;++i){
            if(!treeSet.contains(i))
                System.out.print(i+" ");
        }
    }
}
