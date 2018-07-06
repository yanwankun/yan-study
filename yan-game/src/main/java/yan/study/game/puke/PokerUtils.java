package yan.study.game.puke;

import java.util.*;
import java.util.stream.Collectors;

public class PokerUtils {

    public static final Card CARD_MAX_KING = new Card(Card.POKER_COLOR_LIST.get(1), Card.MAX_KING);
    public static final Card CARD_SMALL_KING = new Card(Card.POKER_COLOR_LIST.get(0), Card.SMALL_KING);
    public static final Card CARD_FAN_PIAN_8 = new Card(Card.POKER_COLOR_LIST.get(3), Card.POKER_VALUE_LIST.get(6));
    public static final Card CARD_HEI_TAO_9 = new Card(Card.POKER_COLOR_LIST.get(0), Card.POKER_VALUE_LIST.get(5));
    public static final Card CARD_FAN_PIAN_A = new Card(Card.POKER_COLOR_LIST.get(3), Card.POKER_VALUE_LIST.get(0));
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
        pukes.add(CARD_MAX_KING);
        pukes.add(CARD_SMALL_KING);
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

    /**
     * 是否有对子牌
     */
    public static boolean havePairCard(List<Card> cardList) {
        Collections.sort(cardList, Collections.reverseOrder());
        for (int index = 0; index < cardList.size()-1; index++) {
            if (cardList.get(index).getValue().equalsIgnoreCase(cardList.get(index+1).getValue())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断一首牌中是否有几张相同的牌 count>2 才使用
     */
    public static boolean havePairCount(List<Card> cardList, int count) {
        Collections.sort(cardList, Collections.reverseOrder());
        int count1;
        String value;
        for (int index = 0; index < cardList.size(); index++) {
            value = cardList.get(index).getValue();
            count1 = 0;
            for (int j = index+1; j < cardList.size(); j++) {
                if (cardList.get(j).getValue().equalsIgnoreCase(value)) {
                    count1++;
                    if (count1 == count) {
                        return true;
                    }
                } else {
                    break;
                }
            }
        }
        return false;
    }

    /**
     * 从一手牌中获取指定的相同的牌
     */
    public static List<Card> getPairCard(List<Card> cardList, Integer count) {
        Collections.sort(cardList);
        int count1;
        String value;
        for (int index = 0; index < cardList.size(); index++) {
            value = cardList.get(index).getValue();
            count1 = 0;
            for (int j = index+1; j < cardList.size(); j++) {
                if (cardList.get(j).getValue().equalsIgnoreCase(value)) {
                    count1++;
                    if (count1 == count) {
                        List<Card> list = new ArrayList<>();
                        for (int k = index; k < count; k++) {
                            list.add(list.get(k));
                        }
                        return list;
                    }
                } else {
                    break;
                }
            }
        }
        return null;
    }


    public static void main(String[] args) {
//        List<Card> pokers = getNewPokers();
////        pokers = deleteCard(Arrays.asList(new Card(Card.POKER_COLOR_LIST.get(1), Card.CARD_MAX_KING), new Card(Card.POKER_COLOR_LIST.get(0), Card.SMALL_KING)), pukes);
//        Shuffle(pokers);
////        niuniu(pukes, 4);
//        showPokers(pokers);
//        Comparator comparator = Collections.reverseOrder();
//        System.out.println("111111");
//        Collections.sort(pokers, comparator);
//        showPokers(pokers);
////        System.out.println(new Card(Card.POKER_COLOR_LIST.get(1), Card.CARD_MAX_KING).compareTo(new Card(Card.POKER_COLOR_LIST.get(0), Card.SMALL_KING)));

    }

    // 梭哈的牌型大小比较器
    class MyComparator implements Comparator<List<Card>> {

        @Override
        public int compare(List<Card> o1, List<Card> o2) {
            if (o1.size() != o2.size()) {
                return -1;
            }

            if (o1.size() == 1) {
                return o1.get(0).compareTo(o2.get(0));
            }
            Comparator comparator = Collections.reverseOrder();

            // 对子牌最大
            if (o1.size() == 2) {
                Collections.sort(o1, comparator);
                Collections.sort(o2, comparator);
                return compareTo2(o1, o2);
            }

            // 三条 > 2条 > 高牌
            if (o1.size() == 3) {
                if (havePairCount(o1, 3)) {
                    if (havePairCount(o2, 3)) {
                        return commCompareTo(o1, o2);
                    }
                    return 1;
                } else {
                    if (havePairCount(o2, 3)) {
                        return -1;
                    } else {
                        // 都没有三条
                        if (havePairCard(o1)) {
                            if (havePairCard(o2)) {
                                List<Card> dui1 = getPairCard(o1, 2);
                                List<Card> dui2 = getPairCard(o2, 2);
                                // todo 这个写法不好
                                return -1;
                            }
                            return 1;
                        } else {
                            if (havePairCard(o2)) {
                                return -1;
                            }
                            // 都没有对子
                            return commCompareTo(o1, o2);
                        }
                    }
                }
            }

            return -1;
        }

        private int compareTo2(List<Card> o1, List<Card> o2) {
            if (havePairCard(o1)) {
                if (havePairCard(o2)) {
                    return commCompareTo(o1, o2);
                }
                return 1;
            } else {
                if (havePairCard(o2)) {
                    return -1;
                }
                return commCompareTo(o1, o2);
            }
        }

    }



}
