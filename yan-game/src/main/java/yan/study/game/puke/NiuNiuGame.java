package yan.study.game.puke;

import cn.yan.study.utils.YanStrUtils;
import cn.yan.study.utils.cache.ConcurrentHashMapCacheUtils;

import java.util.*;

public class NiuNiuGame {

    /**
     * 牛牛玩法
     * @param pukes
     * @param count
     */
    public static void niuniu(List<Card> pukes, int count) {
        List<List<Card>> personCards = new ArrayList<List<Card>>(count);

        for (int cardCount = 0; cardCount < 5; cardCount ++) {
            personCards.add(new ArrayList<Card>());
        }

        for (int cardCount = 0; cardCount < 5; cardCount ++) {
            for (int index = 0; index < 5; index ++) {
                List<Card> cardList = personCards.get(index);
                cardList.add(pukes.get(0));
                pukes.remove(0);
            }
        }

        int index = 0;
        for (List<Card> personCard : personCards) {
            String personName;
            if (index < PokerUtils.persons.size()) {
                personName = PokerUtils.persons.get(index);
                index++;
            } else {
                personName = "person" + index;
            }
            System.out.println(personName + " card is :");
            for (Card card : personCard) {
                System.out.println(card);
            }
        }
    }

    /**
     * 获取一手牌的牛
     * @param personCards
     * @return
     */
    public static NiuResult getNiuResult(List<Card> personCards) {
        /**
         * 先把大于等于10的拿出去
         * 再找三个加起来等于10的倍数的
         * 再找两个加起来等于10的倍数的
         * 最后算牛几
         */
        NiuResult result = new NiuResult();
        List<Card> moreThanNine = new ArrayList<Card>();
        List<Card> lessThanTen = new ArrayList<Card>();
        Card heitao9 = new Card(Card.POKER_COLOR_LIST.get(0), Card.POKER_VALUE_LIST.get(5));
        Card fanpianA = new Card(Card.POKER_COLOR_LIST.get(3), Card.POKER_VALUE_LIST.get(0));
        for (Card card : personCards) {
            if (card.compareTo(heitao9) > 0 && card.compareTo(fanpianA) < 0) {
                moreThanNine.add(card);
            } else {
                lessThanTen.add(card);
            }
        }

        Integer niuNumber = getNiuNumber(lessThanTen);
        result.setNiuNumber(niuNumber);

        List<Card> sortCards = new ArrayList<Card>();
        Collections.sort(moreThanNine, Collections.<Card>reverseOrder());
        sortCards.addAll(moreThanNine);

        Collections.sort(lessThanTen, Collections.<Card>reverseOrder());
        List<Card> aList = new ArrayList<Card>(); // 记录A的牌
        for (Card card : lessThanTen) {
            if (card.getValue().equals(Card.POKER_VALUE_LIST.get(0))) {
                aList.add(card);
            }
        }
        if (aList.size()>0) {
            lessThanTen.removeAll(aList);
            lessThanTen.addAll(aList);
        }

        sortCards.addAll(lessThanTen);
        result.setCardSortList(sortCards);

        return result;
    }


    /**
     * 获取牛的数字
     * @param lessThanTen
     * @return
     */
    private static int getNiuNumber(List<Card> lessThanTen) {
        if (lessThanTen.size() == 0) {
            return 10;
        }
        if (lessThanTen.size() == 1) {
            return getOneCardNum(lessThanTen.get(0));
        }
        if (lessThanTen.size() == 2) {
            return getNiuFrom2(lessThanTen);
        }
        if (lessThanTen.size() == 3) {
            return getNiuFrom3(lessThanTen);
        }
        if (lessThanTen.size() == 4) {
            return getNiuFrom4(lessThanTen);
        }
        if (lessThanTen.size() == 5) {
            return getNiuFrom5(lessThanTen);
        }
        return -1;

    }

    /**
     * 从两张牌里面找牛
     * @param lessThanTen
     * @return
     */
    private static int getNiuFrom2(List<Card> lessThanTen) {
        if (lessThanTen.size() == 2) {
            int niuNumber = (getOneCardNum(lessThanTen.get(0)) + getOneCardNum(lessThanTen.get(1))) % 10;
            if (niuNumber == 0) {
                return 10;
            }
            return niuNumber;

        } else {
            return -1;
        }
    }

    /**
     * 从三张里面找牛
     * @param lessThanTen
     * @return
     */
    private static int getNiuFrom3(List<Card> lessThanTen) {
        if (lessThanTen.size() == 3) {
            // 3
            int niuNumber = (getOneCardNum(lessThanTen.get(0)) + getOneCardNum(lessThanTen.get(1)) + getOneCardNum(lessThanTen.get(2))) % 10;
            if (niuNumber == 0) {
                return 10;
            }

            // 2+1
            List<List<Card>> subListCards2 = YanCollectionUtils.getAllSubList(lessThanTen, 2);
            for (List<Card> listCard : subListCards2) {
                niuNumber = getNiuFrom2(listCard);
                if (niuNumber == 10) {
                    List<Card> subList = YanCollectionUtils.getSubList(lessThanTen, listCard);
                    return getOneCardNum(subList.get(0));
                }
            }

            return -1;

        } else {
            return -1;
        }
    }

    /**
     * 从4账牌里面找牛  3 + 1 2 + 2
     * @param lessThanTen
     * @return
     */
    private static int getNiuFrom4(List<Card> lessThanTen) {
        if (lessThanTen.size() == 4) {
            // 3 + 1
            List<List<Card>> subListCards3 = YanCollectionUtils.getAllSubList(lessThanTen, 3);
            for (List<Card> listCard : subListCards3) {
                int niuNumber = getNiuFrom3(listCard);
                if (niuNumber == 10) {
                    Card card = YanCollectionUtils.getSubList(lessThanTen, listCard).get(0);
                    return getOneCardNum(card);
                }
            }

            // 2 + 2
            List<List<Card>> subListCards2 = YanCollectionUtils.getAllSubList(lessThanTen, 2);
            for (List<Card> listCard : subListCards2) {
                int niuNumber = getNiuFrom2(listCard);
                if (niuNumber == 10) {
                    List<Card> subList = YanCollectionUtils.getSubList(lessThanTen, listCard);
                    return getNiuFrom2(subList);
                }
            }

            return -1;
        } else {
            return -1;
        }
    }

    /**
     * 从五张里面找牛 3+2 2+2+1
     * @param lessThanTen
     * @return
     */
    private static int getNiuFrom5(List<Card> lessThanTen) {
        if (lessThanTen.size() == 5) {
            // 3+2
            List<List<Card>> subListCards3 = YanCollectionUtils.getAllSubList(lessThanTen, 3);
            for (List<Card> listCard : subListCards3) {
                int niuNumber = getNiuFrom3(listCard);
                if (niuNumber == 10) {
                    List<Card> subList = YanCollectionUtils.getSubList(lessThanTen, listCard);
                    return getNiuFrom2(subList);
                }
            }

            // 2+2+1
            List<List<Card>> subListCards2 = YanCollectionUtils.getAllSubList(lessThanTen, 2);
            for (List<Card> listCard : subListCards2) {
                int niuNumber = getNiuFrom2(listCard);
                if (niuNumber == 10) {
                    List<Card> subList = YanCollectionUtils.getSubList(lessThanTen, listCard);
                    niuNumber = getNiuFrom2(subList);
                    if (niuNumber == 10) {
                        Card card = YanCollectionUtils.getSubList(lessThanTen, listCard).get(0);
                        return getOneCardNum(card);
                    }
                }
            }
            return -1;
        } else {
            return -1;
        }
    }

    /**
     * 取一张的值
     * @param card
     * @return
     */
    private static int getOneCardNum(Card card) {
        if (card.getValue().equals(Card.POKER_VALUE_LIST.get(0))) {
            return 1;
        }
        return Integer.valueOf(card.getValue());
    }

    /**
     * 创建游戏
     * @param count
     * @param names
     * @return
     */
    public static String createGame(int count, List<String> names) {

        if (count > names.size()) {
            throw new RuntimeException("params is error");
        }

        String gameUUid = YanStrUtils.getYanString("niuniuGame");
        List<Person> gamePersonList = new ArrayList<Person>();
        for (int i = 0; i< count; i++) {
            Person person = new Person(names.get(i));
            gamePersonList.add(person);
        }

        ConcurrentHashMapCacheUtils.setCache(gameUUid, gamePersonList);
        return gameUUid;
    }


    public static void sendCard(String gameUUid) {
        List<Card> pokers = PokerUtils.getNewPokers();
        pokers = PokerUtils.deleteCard(Arrays.asList(new Card(Card.POKER_COLOR_LIST.get(1), Card.MAX_KING), new Card(Card.POKER_COLOR_LIST.get(0), Card.SMALL_KING)), pokers);
        PokerUtils.Shuffle(pokers);
        List<Person> gamePersonList = (List<Person>) ConcurrentHashMapCacheUtils.getCache(gameUUid);
        int personCount = gamePersonList.size();
        for (int cardCount = 0; cardCount < 5; cardCount++) {
            for (int index = 0; index < personCount; index++) {
                List<Card> cardList = gamePersonList.get(index).getCardList();
                cardList.add(pokers.get(0));
                pokers.remove(0);
            }
        }
        ConcurrentHashMapCacheUtils.setCache(gameUUid, gamePersonList);
    }

    public static void showResult(String gameUUid) {
        List<Person> gamePersonList = (List<Person>) ConcurrentHashMapCacheUtils.getCache(gameUUid);
        Collections.sort(gamePersonList, new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                List<Card> personOneCards = o1.getCardList();
                List<Card> personTwoCards = o2.getCardList();

                NiuResult oneResult = getNiuResult(personOneCards);
                NiuResult twoResult = getNiuResult(personTwoCards);

                return oneResult.compareTo(twoResult);
            }
        });

        Person winPerson = gamePersonList.get(0);
        System.out.println("this is winner ...");
        showPerson(winPerson);


        System.out.println("all person info is ....");
        for (int index =0; index < gamePersonList.size(); index++) {
            Person person = gamePersonList.get(index);
            showPerson(person);
        }
    }


    public static void showPerson(Person person) {
        System.out.println("person name is : " + person.getName());
        System.out.println("card is :");
        for (Card card : person.getCardList()) {
            System.out.println(card);
        }
        System.out.println("result is : " + getNiuResult(person.getCardList()));
    }



    public static void main(String[] args) {

        String gameUUid = createGame(4, Arrays.asList("yan", "test", "root", "system"));
        sendCard(gameUUid);
        showResult(gameUUid);
    }

    private static class NiuResult implements Comparable {
        /**
         * 牛的数目
         */
        private Integer niuNumber;
        /**
         * 有序手牌
         */
        private List<Card> cardSortList;

        public Integer getNiuNumber() {
            return niuNumber;
        }

        public void setNiuNumber(Integer niuNumber) {
            this.niuNumber = niuNumber;
        }

        public List<Card> getCardSortList() {
            return cardSortList;
        }

        public void setCardSortList(List<Card> cardSortList) {
            this.cardSortList = cardSortList;
        }

        /**
         * 先判断牛的大小 在判断牌的大小 最后比最大的牌的花色
         * @param o
         * @return
         */
        public int compareTo(Object o) {
            if (!(o instanceof NiuResult)) {
                return -1;
            }
            NiuResult niuResult = (NiuResult)o;

            if (this.getNiuNumber() > niuResult.getNiuNumber()) {
                return niuResult.getNiuNumber() - this.getNiuNumber();
            }

            return PokerUtils.commCompareTo(this.getCardSortList(), niuResult.getCardSortList());
        }

        @Override
        public String toString() {
            return "NiuResult{" +
                    "niuNumber=" + niuNumber +
                    ", cardSortList=" + cardSortList +
                    '}';
        }
    }
}
