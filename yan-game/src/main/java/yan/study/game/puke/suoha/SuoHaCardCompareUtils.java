package yan.study.game.puke.suoha;

import cn.yan.study.utils.YanObjectUtils;
import yan.study.game.puke.Card;
import yan.study.game.puke.PokerUtils;
import yan.study.game.puke.YanCollectionUtils;
import yan.study.game.puke.suoha.enums.SuoHaCardTypeEnum;

import java.util.ArrayList;
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

        return -1;
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

}
