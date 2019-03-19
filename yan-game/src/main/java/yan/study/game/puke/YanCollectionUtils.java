package yan.study.game.puke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class YanCollectionUtils {

    /**
     * 获取list 的 元素个数为 count的 全部子集
     */
    @SuppressWarnings("unchecked")
    public static <T> List<List<T>> getAllSubList(List<T> list, int count) {
        List<List<T>> dataList = new ArrayList<List<T>>();
        if (count == 0) {
            return dataList;
        }
        if (list.size() < count) {
            return dataList;
        }
        if (list.size() == count) {
            dataList.add(list);
            return dataList;
        }
        List<T> subList;
        if (count == 1) {
            for (T t : list) {
                subList = new ArrayList<>();
                subList.add(t);
                dataList.add(subList);
            }
            return dataList;
        }

        List<T> deleteList = new ArrayList<>();
        for (T t : list) {
            deleteList.add(t);
            List<T> tList = getSubList(list, deleteList);
            List<List<T>> subLists = getAllSubList(tList, count-1);
            if (subLists.isEmpty()) {
                return dataList;
            }
            for (List<T> tSubList : subLists) {
                subList = new ArrayList<>();
                subList.add(t);
                subList.addAll(tSubList);
                dataList.add(subList);
            }
        }
        return dataList;
    }


    /**
     * 这个方法做差 只会把差集中的值有多少次去除多少次，不会去除所有
     * @param one
     * @param <T>
     * @return
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <T> List<T> getSubList(List<T> ...one) {
        if (one.length < 2) {
            throw new RuntimeException("parameter length is error");
        }
        List subList = new ArrayList();

        subList.addAll(one[0]);
        for (int i = 1; i < one.length; i++) {
            subList = _getSubList(subList, one[i]);
        }
        return subList;
    }

    /**
     * 两个集合做差 不是去除差集中得所有相同值
     * @param aList
     * @param bList
     * @param <T>
     * @return
     */
    public static <T> List<T> _getSubList(List<T> aList, List<T> bList) {
        for (T bT : bList) {
            if (aList.contains(bT)) {
                aList.remove(bT);
            }
        }
        return aList;
    }

    public static <T> void show(List<T> list) {
        for (T t : list) {
            System.out.println(t);
        }
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1,2,3,2,4,5,6,7,8,9);
        List<Integer> dataList = getSubList(list, Arrays.asList(1,2));
        show(dataList);

    }
}
