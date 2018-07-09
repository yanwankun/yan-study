package yan.study.game.puke.suoha;

import cn.yan.study.utils.YanObjectUtils;
import yan.study.game.puke.Card;
import yan.study.game.puke.PokerUtils;
import yan.study.game.puke.YanCollectionUtils;
import yan.study.game.puke.suoha.enums.SuoHaCardTypeEnum;

import java.net.CookieHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created
 * User  wankunYan
 * Date  2018/7/7
 * Time  8:50
 */
public class SuoHaCardCompareUtils {
    /**
     * 同花顺加一张牌的变化
     */
    public static Integer sameFlowerAndSequenceToChange(List<Card> cardList, Card card) {
        List<Card> sequeenceList = new ArrayList<>();
        sequeenceList.addAll(cardList);
        sequeenceList.add(card);

        // 先看看有没有可能是同花
        if (PokerUtils.verifyIsSameFlower(sequeenceList)) {
            // 同花顺
            if (PokerUtils.verifyIsSequence(sequeenceList)) {
                return SuoHaCardTypeEnum.SAME_FLOWER_AND_SEQUENCE.getKey();
            }

            return SuoHaCardTypeEnum.SAME_FLOWER.getKey();
        }

        // 只是顺子
        if (PokerUtils.verifyIsSequence(sequeenceList)) {
            return SuoHaCardTypeEnum.SEQUENCE.getKey();
        }

        // 单对
        if (PokerUtils.havePairCount(sequeenceList, 2)) {
            return SuoHaCardTypeEnum.SINGLE_COUPLET.getKey();
        }

        // 高牌
        return SuoHaCardTypeEnum.HIGH_CARD.getKey();
    }

    /**
     * 同花加一张牌的变化
     * @param cardList
     * @param card
     * @return
     */
    public static Integer sameFlowerToChange(List<Card> cardList, Card card) {
        List<Card> sequeenceList = new ArrayList<>();
        sequeenceList.addAll(cardList);
        sequeenceList.add(card);

        // 先看看有没有可能是同花
        if (PokerUtils.verifyIsSameFlower(sequeenceList)) {
            // 同花顺
            if (PokerUtils.verifyIsSequence(sequeenceList)) {
                return SuoHaCardTypeEnum.SAME_FLOWER_AND_SEQUENCE.getKey();
            }

            return SuoHaCardTypeEnum.SAME_FLOWER.getKey();
        }

        // 单对
        if (PokerUtils.havePairCount(sequeenceList, 2)) {
            return SuoHaCardTypeEnum.SINGLE_COUPLET.getKey();
        }

        // 高牌
        return SuoHaCardTypeEnum.HIGH_CARD.getKey();
    }

    /**
     * 顺子加一张牌的变化
     * @param cardList
     * @param card
     * @return
     */
    public static Integer sequenceToChange(List<Card> cardList, Card card) {
        List<Card> sequeenceList = new ArrayList<>();
        sequeenceList.addAll(cardList);
        sequeenceList.add(card);

        // 先看看有没有可能还是顺子
        if (PokerUtils.verifyIsSequence(sequeenceList)) {
            return SuoHaCardTypeEnum.SEQUENCE.getKey();
        }

        // 单对
        if (PokerUtils.havePairCount(sequeenceList, 2)) {
            return SuoHaCardTypeEnum.SINGLE_COUPLET.getKey();
        }

        // 高牌
        return SuoHaCardTypeEnum.HIGH_CARD.getKey();
    }

    /**
     * 三条添加一张牌后的拍醒变化
     * @param cardList
     * @param card
     * @return
     */
    public static Integer threeToChange(List<Card> cardList, Card card) {
        // 先看看有没有可能变成4条
        List<Card> threeCard = PokerUtils.getPairCard(cardList, 3);
        if (threeCard.get(0).getValue().equalsIgnoreCase(card.getValue())) {
            return SuoHaCardTypeEnum.FOUR.getKey();
        }
        // 再看看有没有可能变成葫芦
        List<Card> otherCard = YanCollectionUtils.getSubList(cardList, threeCard);
        if (!YanObjectUtils.isEmpty(otherCard)) {
            if (otherCard.get(0).getValue().equalsIgnoreCase(card.getValue())) {
                return SuoHaCardTypeEnum.GOURD.getKey();
            }
        }

        return SuoHaCardTypeEnum.THREE.getKey();
    }

    /**
     * 两对添加一张牌后的
     * @param cardList
     * @param card
     * @return
     */
    public static Integer doubleCoupleToChange(List<Card> cardList, Card card) {
        // 看看有没有可能变成葫芦
        List<Card> coupleCard = PokerUtils.getPairCard(cardList, 2);
        if (coupleCard.get(0).getValue().equalsIgnoreCase(card.getValue())) {
            return SuoHaCardTypeEnum.GOURD.getKey();
        }

        // 先看看有没有可能变成两对
        List<Card> otherCardList = YanCollectionUtils.getSubList(cardList, coupleCard);
        if (otherCardList.get(0).getValue().equalsIgnoreCase(card.getValue())) {
            return SuoHaCardTypeEnum.GOURD.getKey();
        }

        return SuoHaCardTypeEnum.DOUBLE_COUPLET.getKey();
    }

    /**
     * 单对添加牌后的牌型变化
     */
    public static Integer singleCoupleToChange(List<Card> cardList, Card card) {
        // 先看看有没有可能变成3条
        List<Card> coupleCard = PokerUtils.getPairCard(cardList, 2);
        if (coupleCard.get(0).getValue().equalsIgnoreCase(card.getValue())) {
            return SuoHaCardTypeEnum.THREE.getKey();
        }

        // 再看看有没有可能变成两对
        List<Card> otherCardList = YanCollectionUtils.getSubList(cardList, coupleCard);
        if (!YanObjectUtils.isEmpty(otherCardList)) {
            for (Card card1 : otherCardList) {
                if (card.getValue().equalsIgnoreCase(card1.getValue())) {
                    return SuoHaCardTypeEnum.DOUBLE_COUPLET.getKey();
                }
            }
        }

        return SuoHaCardTypeEnum.SINGLE_COUPLET.getKey();
    }

    /**
     * 高牌添加牌后牌型变化
     */
    public static Integer highCardToChange(List<Card> cardList, Card card) {
        // 考虑变对子的情况
        for (Card card1 : cardList) {
            if (card1.getValue().equalsIgnoreCase(card.getValue())) {
                return SuoHaCardTypeEnum.SINGLE_COUPLET.getKey();
            }
        }

        List<Card> sequeenceList = new ArrayList<>();
        sequeenceList.addAll(cardList);
        sequeenceList.add(card);
        if (PokerUtils.verifyIsSequence(sequeenceList)) {
            return SuoHaCardTypeEnum.SEQUENCE.getKey();
        }

        return SuoHaCardTypeEnum.HIGH_CARD.getKey();
    }

    /**
     * 更具显示牌型比较大小 todo
     * @param o1
     * @param o2
     * @return
     */
    public static Integer suoHaCardCompareShow(SuoHaCard o1, SuoHaCard o2) {
        if (o1.getShowCardType() != o2.getShowCardType()) {
            return o2.getShowCardType() - o1.getShowCardType();
        }

        List<Card> list1 = o1.getCardList().subList(1, o1.getCardList().size());
        List<Card> list2 = o2.getCardList().subList(1, o2.getCardList().size());

        if (SuoHaCardTypeEnum.SAME_FLOWER_AND_SEQUENCE.getKey().equals(o1.getShowCardType())) {
            return sameFlowerAndSequenceShowCompare(list1, list2);
        }

        if (SuoHaCardTypeEnum.FOUR.getKey().equals(o1.getShowCardType())) {
            return fourCompare(list1, list2);
        }

        if (SuoHaCardTypeEnum.GOURD.getKey().equals(o1.getShowCardType())) {
            return threeCompare(list1, list2);
        }

        if (SuoHaCardTypeEnum.SAME_FLOWER.getKey().equals(o1.getShowCardType())) {
            return PokerUtils.commCompareTo(list1, list2);
        }

        if (SuoHaCardTypeEnum.SEQUENCE.getKey().equals(o1.getShowCardType())) {
            return PokerUtils.commCompareTo(list1, list2);
        }

        if (SuoHaCardTypeEnum.THREE.getKey().equals(o1.getShowCardType())) {
            return threeCompare(list1, list2);
        }

        if (SuoHaCardTypeEnum.DOUBLE_COUPLET.getKey().equals(o1.getShowCardType())) {
            return doubleCoupletCompare(list1, list2);
        }

        return -1;
    }

    /**
     * 两对比较大小
     * @param list1
     * @param list2
     * @return
     */
    private static Integer doubleCoupletCompare(List<Card> list1, List<Card> list2) {
        Collections.sort(list1, Collections.reverseOrder());
        Collections.sort(list2, Collections.reverseOrder());

        List<Card> twoCard11 = PokerUtils.getPairCard(list1, 2);
        List<Card> twoCard21 = PokerUtils.getPairCard(list2, 2);

        int compare = Card.POKER_VALUE_LIST.indexOf(twoCard21.get(0).getValue()) - Card.POKER_VALUE_LIST.indexOf(twoCard11.get(0).getValue());
        if (compare != 0) {
            return compare;
        }

        List<Card> twoCard12 = PokerUtils.getPairCard(list1.subList(2, list1.size()-1), 2);
        List<Card> twoCard22 = PokerUtils.getPairCard(list2.subList(2, list2.size()-1), 2);
        compare = Card.POKER_VALUE_LIST.indexOf(twoCard22.get(0).getValue()) - Card.POKER_VALUE_LIST.indexOf(twoCard12.get(0).getValue());
        if (compare != 0) {
            return compare;
        }

        List<Card> lastCard1 = YanCollectionUtils.getSubList(list1, twoCard11, twoCard12);
        List<Card> lastCard2 = YanCollectionUtils.getSubList(list2, twoCard21, twoCard22);

        if (!YanObjectUtils.isEmpty(lastCard1)) {
            compare = Card.POKER_VALUE_LIST.indexOf(lastCard2.get(0).getValue()) - Card.POKER_VALUE_LIST.indexOf(lastCard1.get(0).getValue());
            if (compare != 0) {
                return compare;
            }
        }


        return PokerUtils.commCompareTo(list1, list2);
    }

    /**
     * 根据真实牌型比较大小 todo
     * @param o1
     * @param o2
     * @return
     */
    public static Integer suoHaCardCompare(SuoHaCard o1, SuoHaCard o2) {
        return -1;
    }

    /**
     *
     * @param o1
     * @param o2
     * @return
     */
    private static Integer threeCompare(List<Card> o1, List<Card> o2) {
        Collections.sort(o1, Collections.reverseOrder());
        Collections.sort(o2, Collections.reverseOrder());

        List<Card> threeCard1 = PokerUtils.getPairCard(o1, 3);
        List<Card> threeCard2 = PokerUtils.getPairCard(o2, 3);

        int compare = Card.POKER_VALUE_LIST.indexOf(threeCard2.get(0).getValue()) - Card.POKER_VALUE_LIST.indexOf(threeCard1.get(0).getValue());
        if (compare != 0) {
            return compare;
        }

        List<Card> ot1 = YanCollectionUtils.getSubList(o1, threeCard1);
        List<Card> ot2 = YanCollectionUtils.getSubList(o2, threeCard2);

        if (!YanObjectUtils.isEmpty(ot1)) {
            if (ot1.size() == 1) {
                compare = Card.POKER_VALUE_LIST.indexOf(ot2.get(0).getValue()) - Card.POKER_VALUE_LIST.indexOf(ot1.get(0).getValue());
                if (compare != 0) {
                    return compare;
                }
            } else if (ot1.size() == 2){
                compare = twoCardCompareNoColor(ot1, ot2);
                if (compare != 0) {
                    return compare;
                }
            }

        }

        return PokerUtils.commCompareTo(o1, o2);
    }

    /**
     * 两张牌比较大小不管花色
     * @param ot1
     * @param ot2
     * @return
     */
    private static int twoCardCompareNoColor(List<Card> ot1, List<Card> ot2) {
        return 0;
    }

    /**
     * 同花顺比较大小
     * @param o1
     * @param o2
     * @return
     */
    private static Integer sameFlowerAndSequenceShowCompare(List<Card> o1, List<Card> o2) {
        Card maxO1 = findNayMaxCard(o1);
        Card maxO2= findNayMaxCard(o2);
        return maxO1.compareTo(maxO2);
    }

    /**
     * 四条的完整大小比较
     * @param o1
     * @param o2
     * @return
     */
    private static Integer fourCompare(List<Card> o1, List<Card> o2) {
        List<Card> fourCard1 = PokerUtils.getPairCard(o1, 4);
        List<Card> fourCard2 = PokerUtils.getPairCard(o2, 4);

        Collections.sort(fourCard1, Collections.reverseOrder());
        Collections.sort(fourCard2, Collections.reverseOrder());

        int compare = Card.POKER_VALUE_LIST.indexOf(fourCard2.get(0).getValue()) - Card.POKER_VALUE_LIST.indexOf(fourCard1.get(0).getValue());
        if (compare != 0) {
            return compare;
        }

        List<Card> ot1 = YanCollectionUtils.getSubList(o1, fourCard1);
        List<Card> ot2 = YanCollectionUtils.getSubList(o2, fourCard2);

        if (!YanObjectUtils.isEmpty(ot1)) {
            compare = Card.POKER_VALUE_LIST.indexOf(ot2.get(0).getValue()) - Card.POKER_VALUE_LIST.indexOf(ot1.get(0).getValue());
            if (compare != 0) {
                return compare;
            }
        }

        compare = PokerUtils.commCompareTo(fourCard1, fourCard2);
        if (compare != 0) {
            return compare;
        }

        if (!YanObjectUtils.isEmpty(ot1)) {
            return ot1.get(0).compareTo(ot2.get(0));
        }

        return compare;

    }
    /**
     * 找到一个顺子可能的最大牌
     * @param cardList
     * @return
     */
    private static Card findNayMaxCard(List<Card> cardList) {
        Collections.sort(cardList);
        int subValue = Card.POKER_VALUE_LIST.indexOf(cardList.get(0).getValue()) - Card.POKER_VALUE_LIST.indexOf(cardList.get(cardList.size()-1).getValue());
        if (subValue == cardList.size()) {
            return cardList.get(0);
        }

        int index = Card.POKER_VALUE_LIST.indexOf(cardList.get(0).getValue());
        if (index > 0) {
            return new Card(cardList.get(0).getColor(), Card.POKER_VALUE_LIST.get(index -1));
        }

        return cardList.get(0);
    }

}
