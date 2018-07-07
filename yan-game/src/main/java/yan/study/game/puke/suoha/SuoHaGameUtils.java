package yan.study.game.puke.suoha;

import cn.yan.study.utils.YanObjectUtils;
import cn.yan.study.utils.YanStrUtils;
import cn.yan.study.utils.cache.ConcurrentHashMapCacheUtils;
import yan.study.game.puke.Card;
import yan.study.game.puke.PokerUtils;
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
     * 房间最大玩家数量
     */
    public static final Integer MAX_PLAYER_COUNT = 5;

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

        int count = room.getPlayerList().size() + room.getAddPlayerList().size() - room.getLeavePlayerList().size();
        if (count >= SuoHaGameUtils.MAX_PLAYER_COUNT) {
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

        if (room.getPlaying()) {
            room.getAddPlayerList().add(player);
        } else {
            if (room.getPlayerList().size() < index) {
                return false;
            }

            room.getPlayerList().add(index, player);
        }

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

        room.getPlayerList().addAll(room.getAddPlayerList());

        room.setPlaying(true);
        room.setPokerList(getSuoHaPokers());
        List<SuoHaPlayer> removePlayer = new ArrayList<>();
        for (SuoHaPlayer player : room.getPlayerList()) {
            if (player.getAmount() == 0) {
                removePlayer.add(player);
                continue;
            }
            player.setPlay(true);
            player.setBetAmount(0);
            player.setAllIn(false);
            player.setAllBetAmount(0);
            player.setSuoHaCard(new SuoHaCard());
        }
        if (!YanObjectUtils.isEmpty(removePlayer)) {
            room.getPlayerList().removeAll(removePlayer);
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

        ConcurrentHashMapCacheUtils.setCache(room.getGameRoomNo(), room);
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
            suoHaCard.setCardType(SuoHaCardCompareUtils.highCardToChange(suoHaCard.getCardList(), card));
        } else if (suoHaCard.getCardType().equals(SuoHaCardTypeEnum.SINGLE_COUPLET.getKey())) {
            suoHaCard.setCardType(SuoHaCardCompareUtils.singleCoupleToChange(suoHaCard.getCardList(), card));
        } else if (suoHaCard.getCardType().equals(SuoHaCardTypeEnum.DOUBLE_COUPLET.getKey())) {
            suoHaCard.setCardType(SuoHaCardCompareUtils.doubleCoupleToChange(suoHaCard.getCardList(), card));
        } else if (suoHaCard.getCardType().equals(SuoHaCardTypeEnum.THREE.getKey())) {
            suoHaCard.setCardType(SuoHaCardCompareUtils.threeToChange(suoHaCard.getCardList(), card));
        } else if (suoHaCard.getCardType().equals(SuoHaCardTypeEnum.SEQUENCE.getKey())) {
            suoHaCard.setCardType(SuoHaCardCompareUtils.threeToChange(suoHaCard.getCardList(), card));
        } else if (suoHaCard.getCardType().equals(SuoHaCardTypeEnum.SAME_FLOWER.getKey())) {
            suoHaCard.setCardType(SuoHaCardCompareUtils.threeToChange(suoHaCard.getCardList(), card));
        } else if (suoHaCard.getCardType().equals(SuoHaCardTypeEnum.SAME_FLOWER_AND_SEQUENCE.getKey())) {
            suoHaCard.setCardType(SuoHaCardCompareUtils.threeToChange(suoHaCard.getCardList(), card));
        }

        // 再设置显示类型
        List<Card> showCardList = suoHaCard.getCardList().subList(1, suoHaCard.getCardList().size() -1);
        if (suoHaCard.getShowCardType().equals(SuoHaCardTypeEnum.HIGH_CARD.getKey())) {
            suoHaCard.setShowCardType(SuoHaCardCompareUtils.highCardToChange(showCardList, card));
        } else if (suoHaCard.getShowCardType().equals(SuoHaCardTypeEnum.SINGLE_COUPLET.getKey())) {
            suoHaCard.setShowCardType(SuoHaCardCompareUtils.singleCoupleToChange(showCardList, card));
        } else if (suoHaCard.getShowCardType().equals(SuoHaCardTypeEnum.DOUBLE_COUPLET.getKey())) {
            suoHaCard.setShowCardType(SuoHaCardCompareUtils.doubleCoupleToChange(showCardList, card));
        } else if (suoHaCard.getShowCardType().equals(SuoHaCardTypeEnum.THREE.getKey())) {
            suoHaCard.setShowCardType(SuoHaCardCompareUtils.threeToChange(showCardList, card));
        } else if (suoHaCard.getShowCardType().equals(SuoHaCardTypeEnum.SEQUENCE.getKey())) {
            suoHaCard.setShowCardType(SuoHaCardCompareUtils.threeToChange(showCardList, card));
        } else if (suoHaCard.getShowCardType().equals(SuoHaCardTypeEnum.SAME_FLOWER.getKey())) {
            suoHaCard.setShowCardType(SuoHaCardCompareUtils.threeToChange(showCardList, card));
        } else if (suoHaCard.getShowCardType().equals(SuoHaCardTypeEnum.SAME_FLOWER_AND_SEQUENCE.getKey())) {
            suoHaCard.setShowCardType(SuoHaCardCompareUtils.threeToChange(showCardList, card));
        }

        if (suoHaCard.getCount() < 4) {
            if (suoHaCard.getShowCardType().equals(SuoHaCardTypeEnum.SEQUENCE.getKey())) {
                suoHaCard.setShowCardType(SuoHaCardTypeEnum.HIGH_CARD.getKey());
            } else if (suoHaCard.getShowCardType().equals(SuoHaCardTypeEnum.SAME_FLOWER.getKey())) {
                suoHaCard.setShowCardType(SuoHaCardTypeEnum.HIGH_CARD.getKey());
            } else if (suoHaCard.getShowCardType().equals(SuoHaCardTypeEnum.SAME_FLOWER_AND_SEQUENCE.getKey())) {
                suoHaCard.setShowCardType(SuoHaCardTypeEnum.HIGH_CARD.getKey());
            }
        } else if (suoHaCard.getCount() == 4) {
            if (suoHaCard.getShowCardType().equals(SuoHaCardTypeEnum.HIGH_CARD.getKey())) {
                if (PokerUtils.verifyMayBeSequence(showCardList)) {
                    suoHaCard.setShowCardType(SuoHaCardTypeEnum.SEQUENCE.getKey());
                }
            } else if (suoHaCard.getShowCardType().equals(SuoHaCardTypeEnum.DOUBLE_COUPLET.getKey())) {
                suoHaCard.setShowCardType(SuoHaCardTypeEnum.GOURD.getKey());
            } else if (suoHaCard.getShowCardType().equals(SuoHaCardTypeEnum.THREE.getKey())) {
                suoHaCard.setShowCardType(SuoHaCardTypeEnum.FOUR.getKey());
            }
        }
    }

    /**
     * 获取当前游戏牌最大的人的索引下标
     * @param roomNo 房间编号
     * @return 返回最大牌的索引下标
     */
    public static Integer getNowMaxPersonIndex(String roomNo) {
        SuoHaRoom room = (SuoHaRoom) ConcurrentHashMapCacheUtils.getCache(roomNo);
        if (YanObjectUtils.isEmpty(room)) {
            return -1;
        }

        if (!room.getPlaying()) {
            return -1;
        }

        Integer index = 0;
        for (int i = 1; i < room.getPlayerList().size(); i++) {
            int compare = SuoHaCardCompareUtils.suoHaCardCompareShow(room.getPlayerList().get(index).getSuoHaCard(), room.getPlayerList().get(i).getSuoHaCard());
            if (compare > 0) {
                index = i;
            }
        }
        return index;
    }

    /**
     * 游戏的下注
     * @param roomNo 房间编号
     */
    public static void gameBet(String roomNo) {
        SuoHaRoom room = (SuoHaRoom) ConcurrentHashMapCacheUtils.getCache(roomNo);
        if (YanObjectUtils.isEmpty(room)) {
            throw new RuntimeException("房间号不存在");
        }

        if (!room.getPlaying()) {
            throw new RuntimeException("房间还没开始游戏");
        }

        for (SuoHaPlayer player : room.getPlayerList()) {
            player.setBetAmount(0);
        }

        /**
         * 下注流程 首先循环一圈 判断下注金额
         * 当当前下注人下注金额
         */

        int betAmount = -1;
        Integer index = getNowMaxPersonIndex(roomNo);
        int addBetIndex = index; // 最后一次加注人下标
        for (int i = 0; ; i++) {
            // 当前下注人
            SuoHaPlayer player = room.getAddPlayerList().get((index + i)%room.getPlayerList().size());
            if (player.getPlay()) {
                if (!player.getAllIn()) {
                    int roundAmount = getPlayerBetAmount(roomNo, player.getName());
                    int roundAllAmount = roundAmount + player.getBetAmount();
                    // 当下注总金额小于轮次下注总金额时,如果不是allIn就下注失败
                    if (roundAllAmount < betAmount) {
                        if (player.getAmount() == roundAmount) {
                            player.setAllIn(true);
                            player.setAmount(0);
                            player.setBetAmount(roundAmount);
                        } else {
                            player.setPlay(false);
                            int playerCount = 0;
                            for (SuoHaPlayer player1 : room.getPlayerList()) {
                                if (player1.getPlay()) {
                                    playerCount++;
                                }
                            }

                            if (playerCount == 1) {
                                break;
                            }
                        }
                    } if (roundAllAmount > betAmount) {
                        player.setBetAmount(roundAmount);
                        player.setAmount(player.getAmount() - roundAmount);
                        betAmount = roundAllAmount;
                        addBetIndex = index + i;
                    } else {
                        player.setBetAmount(roundAmount);
                        player.setAmount(player.getAmount() - roundAmount);
                    }
                }

                // 结束循环条件 当下一个下注人为上一次的加注人是结束
                if ((index + i + 1)%room.getPlayerList().size() == addBetIndex) {
                    break;
                }
            }
        }

        ConcurrentHashMapCacheUtils.setCache(room.getGameRoomNo(), room);
    }

    /**
     * 游戏下注 todo
     * @param roomNo
     * @param name
     * @return
     */
    private static int getPlayerBetAmount(String roomNo, String name) {
        // 这个相当于前端调用接口
        return 0;
    }

    /**
     * 判断游戏是否结束
     */
    public static Boolean verifyGameIsEnd(String roomNo) {
        SuoHaRoom room = (SuoHaRoom) ConcurrentHashMapCacheUtils.getCache(roomNo);
        if (YanObjectUtils.isEmpty(room)) {
            throw new RuntimeException("房间号不存在");
        }

        int playerCount = 0;
        for (SuoHaPlayer player : room.getPlayerList()) {
            if (player.getPlay()) {
                playerCount++;
            }
        }

        if (playerCount == 1) {
            return true;
        }
        return null;
    }

    /**
     * 游戏结算
     * @param roomNo 房间编号
     */
    public static void settleAccounts(String roomNo) {
        SuoHaRoom room = (SuoHaRoom) ConcurrentHashMapCacheUtils.getCache(roomNo);
        if (YanObjectUtils.isEmpty(room)) {
            throw new RuntimeException("房间号不存在");
        }

        if (!room.getPlaying()) {
            throw new RuntimeException("房间不在游戏中");
        }

        while(room.getAllAmount() > 0) {
            SuoHaPlayer winPlayer = null;
            for (SuoHaPlayer player : room.getPlayerList()) {
                if (player.getPlay()) {
                    if (YanObjectUtils.isEmpty(winPlayer)) {
                        winPlayer = player;
                    } else {
                        int compare = SuoHaCardCompareUtils.suoHaCardCompare(player.getSuoHaCard(), winPlayer.getSuoHaCard());
                        if (compare > 0) {
                            winPlayer = player;
                        }
                    }
                }
            }

            for (SuoHaPlayer player : room.getPlayerList()) {
                if (player.getAllBetAmount() >= winPlayer.getAllBetAmount()) {
                    player.setAllBetAmount(player.getAllBetAmount() - winPlayer.getAllBetAmount());
                    winPlayer.setAmount(winPlayer.getAmount() + winPlayer.getAllBetAmount());
                    room.setAllAmount(room.getAllAmount() - winPlayer.getAllBetAmount());
                }
            }
            winPlayer.setPlay(false);
        }

    }

    public static void main(String[] args) {
        PokerUtils.showPokers(getSuoHaPokers());
    }
}
