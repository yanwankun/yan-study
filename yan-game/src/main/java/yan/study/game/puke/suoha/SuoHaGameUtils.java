package yan.study.game.puke.suoha;

import cn.yan.study.utils.YanObjectUtils;
import cn.yan.study.utils.YanStrUtils;
import cn.yan.study.utils.cache.ConcurrentHashMapCacheUtils;
import yan.study.game.puke.Card;
import yan.study.game.puke.PokerUtils;
import yan.study.game.puke.YanCollectionUtils;
import yan.study.game.puke.suoha.enums.SuoHaCardTypeEnum;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created
 * User  wankunYan
 * Date  2018/7/5
 * Time  17:31
 */
public class SuoHaGameUtils {

    /**
     * 创建游戏房间
     */
    public static String createGameRoom(String passWord) {

        String suoHaRoomNo = YanStrUtils.getYanString("suoha");
        SuoHaRoom room = new SuoHaRoom();
        room.setGameRoomNo(suoHaRoomNo);
        if (YanObjectUtils.isEmpty(passWord)) {
            room.setNeedPassWord(false);
        } else {
            room.setNeedPassWord(true);
            room.setPassWord(passWord);
        }

        room.setPlayerList(new ArrayList<>());
        room.setAllAmount(0);
        room.setStartTime(new Date());

        ConcurrentHashMapCacheUtils.setCache(suoHaRoomNo, room);
        return suoHaRoomNo;

    }

    /**
     * 加入游戏房间
     */
    public static Boolean enterGameRoom(SuoHaPlayer player, String roomNo, String passWord, Integer index) {

        if (YanObjectUtils.isEmpty(player)) {
            return false;
        }

        SuoHaRoom room = (SuoHaRoom) ConcurrentHashMapCacheUtils.getCache(roomNo);
        if (YanObjectUtils.isEmpty(room)) {
            return false;
        }

        // 判断密码是否正确
        if (room.getNeedPassWord()) {
            if (YanObjectUtils.isEmpty(passWord)) {
                return false;
            }
            if (!passWord.equalsIgnoreCase(room.getPassWord())) {
                return false;
            }
        }

        if (room.getPlayerList().size() < index) {
            return false;
        }

        room.getPlayerList().add(index, player);
        ConcurrentHashMapCacheUtils.setCache(room.getGameRoomNo(), room);
        return true;
    }

    /**
     * 房间开始游戏 设置房间正在游戏状态，获取一副牌,将每个人的游戏状态设置为正在玩
     */
    public static Boolean startGame(String roomNo) {
        SuoHaRoom room = (SuoHaRoom) ConcurrentHashMapCacheUtils.getCache(roomNo);
        if (YanObjectUtils.isEmpty(room)) {
            return false;
        }

        if (room.getPlaying()) {
            return false;
        }

        if (room.getPlayerList().size() < 2) {
            return false;
        }

        room.setPlaying(true);
        room.setPokerList(getSuoHaPokers());
        for (SuoHaPlayer player : room.getPlayerList()) {
            player.setPlay(true);
        }

        ConcurrentHashMapCacheUtils.setCache(room.getGameRoomNo(), room);
        return true;
    }

    /**
     * 获取一副扑克的梭哈牌
     */
    public static List<Card> getSuoHaPokers() {
        List<Card> allPokers = PokerUtils.getNewPokers();
        Card fanPian8 = PokerUtils.CARD_FAN_PIAN_8;
        List<Card> lessThan8 = new ArrayList<>(50);
        for (Card card : allPokers) {
            if (card.compareTo(fanPian8) < 0) {
                lessThan8.add(card);
            }
        }

        lessThan8.add(PokerUtils.CARD_MAX_KING);
        lessThan8.add(PokerUtils.CARD_SMALL_KING);
        allPokers.removeAll(lessThan8);
        return allPokers;
    }

    /**
     * 梭哈发牌
     * @param roomNo 房间编号
     * @param count 发牌轮数
     */
    public static Boolean sendCard(String roomNo, Integer count) {
        SuoHaRoom room = (SuoHaRoom) ConcurrentHashMapCacheUtils.getCache(roomNo);
        if (YanObjectUtils.isEmpty(room)) {
            return false;
        }

        if (!room.getPlaying()) {
            return false;
        }

        if (count > 4) {
            return false;
        }

        if (count == 1) {
            sendCardRound1(room);
        } else {
            sendCardRound(room);
        }

        return true;
    }

    /**
     * 第一轮发牌 其实就是发了两轮排
     */
    public static void sendCardRound1(SuoHaRoom room) {
        sendCardRound(room);
        sendCardRound(room);
    }

    /**
     * 后几轮发牌
     */
    public static void sendCardRound(SuoHaRoom room) {
        for (int index = 0; index < room.getPlayerList().size(); index++) {
            if (!room.getPlayerList().get(index).getPlay()) {
                continue;
            }
            Card card = room.getPokerList().remove(0);
            room.getAllHaveShowCardList().add(card);
            addCard(room.getPlayerList().get(index).getSuoHaCard(), card);
        }
    }

    /**
     * 给一首牌发一张牌
     */
    public static void addCard(SuoHaCard suoHaCard,Card card) {
        // 判断牌的类型
        if (suoHaCard.getCount() == 1) {
            if (suoHaCard.getHideCard().getValue().equalsIgnoreCase(card.getValue())) {
                suoHaCard.setCardType(SuoHaCardTypeEnum.SINGLE_COUPLET.getKey());
            } else if (suoHaCard.getHideCard().getColor().equalsIgnoreCase(card.getColor())) {
                suoHaCard.setCardType(SuoHaCardTypeEnum.SAME_FLOWER.getKey());
            } else {
                suoHaCard.setCardType(SuoHaCardTypeEnum.HIGH_CARD.getKey());
            }
            suoHaCard.setShowCardType(SuoHaCardTypeEnum.HIGH_CARD.getKey());
        }

        // 当前只有高牌 对子 同花
        if (suoHaCard.getCount() >= 2) {
            addCardTypeChange(suoHaCard, card);
        }

        // 添加牌
        if (suoHaCard.getCount() == 0) {
            suoHaCard.setHideCard(card);
            suoHaCard.getCardList().add(card);
        } else {
            suoHaCard.getCardList().add(card);
        }
    }

    public static void addCardTypeChange(SuoHaCard suoHaCard,Card card) {
        // 先设置自己的牌类型
        if (suoHaCard.getCardType().equals(SuoHaCardTypeEnum.HIGH_CARD.getKey())) {
            suoHaCard.setCardType(highCardToChange(suoHaCard.getCardList(), card));
        } else if (suoHaCard.getCardType().equals(SuoHaCardTypeEnum.SINGLE_COUPLET.getKey())) {
            suoHaCard.setCardType(singleCoupleToChange(suoHaCard.getCardList(), card));
        } else if (suoHaCard.getCardType().equals(SuoHaCardTypeEnum.DOUBLE_COUPLET.getKey())) {
            suoHaCard.setCardType(doubleCoupleToChange(suoHaCard.getCardList(), card));
        }

        // 再设置显示类型

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

        // 先看看有没有可能变成两对
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
        // todo 变成顺子牌的情况还未考虑

        return SuoHaCardTypeEnum.HIGH_CARD.getKey();
    }
    /**
     * 获取当前游戏牌最大的人的索引下标
     * @param suoHaGameUUid 游戏id
     * @return 返回最大牌的索引下标
     */
    public static Integer getNowMaxPersonIndex(String suoHaGameUUid) {
        return 1;
    }

    /**
     * 游戏的下注
     * @param suoHaGameUUid 游戏id
     */
    public static void gameBet(String suoHaGameUUid) {

    }

    /**
     * 判断游戏是否结束
     * @param suoHaGameUUid 游戏id
     * @return 是否结束
     */
    public static Boolean verifyGameIsEnd(String suoHaGameUUid) {
        return null;
    }

    /**
     * 游戏结算
     * @param suoHaGameUUid 游戏id
     */
    public static void settleAccounts(String suoHaGameUUid) {

    }

    public static void main(String[] args) {
        PokerUtils.showPokers(getSuoHaPokers());
    }
}
