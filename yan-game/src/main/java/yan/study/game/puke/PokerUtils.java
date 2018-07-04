package yan.study.game.puke;

import java.util.*;

public class PokerUtils {

    public static final List<String> persons = Arrays.asList("Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven");

    /**
     * 获取一副新的牌
     * @return
     */
    public static List<Card> getNewPokers() {
        List<Card> pukes = new LinkedList<Card>();
        for (String color : Card.POKER_COLOR_LIST) {
            for (String value : Card.POKER_VALUE_LIST) {
                pukes.add(new Card(color, value));
            }
        }
        pukes.add(new Card(Card.POKER_COLOR_LIST.get(1), Card.MAX_KING));
        pukes.add(new Card(Card.POKER_COLOR_LIST.get(0), Card.SMALL_KING));
        return pukes;
    }

    /**
     * 洗牌
     * @param pokers
     * @return
     */
    public static List<Card> Shuffle(List<Card> pokers) {
        Collections.shuffle(pokers);
        return pokers;
    }

    /**
     * 剔除扑克中的某一些牌
     * @param deleteCards
     * @param pokers
     * @return
     */
    public static List<Card> deleteCard(List<Card> deleteCards,List<Card> pokers) {
        pokers.removeAll(deleteCards);
        return pokers;
    }


    /**
     * 打印一副牌
     * @param pokers
     */
    public static void showPokers(List<Card> pokers) {
        for (Card card : pokers) {
            System.out.println(card);
        }
    }


    /**
     * 通用比较一手牌的大小
     * @param one
     * @param two
     * @return
     */
    public static int commCompareTo(List<Card> one, List<Card> two) {
        if (one.size() != two.size()) {
            throw new RuntimeException("两手牌的数量不一样不能比较");
        }

        for (int i = 0; i< one.size(); i++) {
            int valueIndexOne = Card.POKER_VALUE_LIST.indexOf(one.get(i).getValue());
            int valueIndexTwo = Card.POKER_VALUE_LIST.indexOf(two.get(i).getValue());
            if (valueIndexOne != valueIndexTwo) {
                return valueIndexTwo - valueIndexOne;
            }
        }

        for (int i = 0; i< one.size(); i++) {
            int colorIndexOne = Card.POKER_COLOR_LIST.indexOf(one.get(i).getColor());
            int colorIndexTwo = Card.POKER_COLOR_LIST.indexOf(two.get(i).getColor());
            if (colorIndexOne != colorIndexTwo) {
                return colorIndexTwo - colorIndexOne;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
//        List<Card> pokers = getNewPokers();
//        pokers = deleteCard(Arrays.asList(new Card(Card.POKER_COLOR_LIST.get(1), Card.MAX_KING), new Card(Card.POKER_COLOR_LIST.get(0), Card.SMALL_KING)), pukes);
//        Shuffle(pokers);
////        niuniu(pukes, 4);
//        showPokers(pokers);
//        Comparator comparator = Collections.reverseOrder();
//        System.out.println("111111");
//        Collections.sort(pokers, comparator);
//        showPokers(pokers);
//        System.out.println(new Card(Card.POKER_COLOR_LIST.get(1), Card.MAX_KING).compareTo(new Card(Card.POKER_COLOR_LIST.get(0), Card.SMALL_KING)));

    }



}
