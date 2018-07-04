package yan.study.game.puke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class YanCollectionUtils {

    /**
     * 获取list 的 元素个数为 count的 全部子集
     * @param list
     * @param count
     * @return
     */
    public static <T> List<List<T>> getAllSubList(List<T> list, int count) {
        List<List<T>> dataList = new ArrayList<List<T>>();
        if (list.size() < count) {
            return null;
        }
        if (list.size() == count) {
            dataList.add(list);
            return dataList;
        }
        for (int i = 0; i < list.size(); i++) {

            for (int j = i+count-1; j < list.size(); j ++) {
                List subList = new ArrayList();
                for (int key = i; key < i+count-1; key++) {
                    subList.add(list.get(key));
                }
                subList.add(list.get(j));
                dataList.add(subList);
                if (j ==  i) {
                    break;
                }
            }
        }
        return dataList;
    }

    public static <T> List<T> getSubList(List<T> one, List<T> two) {
        List subList = new ArrayList();
        subList.addAll(one);
        subList.removeAll(two);
        return subList;
    }

    public static <T> void show(List<T> list) {
        for (T t : list) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        List list = Arrays.asList(1,2,3,4,5,6,7,8,9);
        List<List> dataList = getAllSubList(list,8);
        show(dataList);

    }
}
