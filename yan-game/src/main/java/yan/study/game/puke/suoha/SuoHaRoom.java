package yan.study.game.puke.suoha;


import yan.study.game.puke.Card;

import java.util.Date;
import java.util.List;

/**
 * Created 游戏房间
 * User  wankunYan
 * Date  2018/7/5
 * Time  17:31
 */
public class SuoHaRoom {
    /**
     * 游戏房间编号
     */
    private String gameRoomNo;
    /**
     * 是否需要密码
     */
    private Boolean isNeedPassWord;
    /**
     * 房间密码
     */
    private String passWord;
    /**
     * 是否正在游戏中
     */
    private Boolean isPlaying;
    /**
     * 当前扑克牌
     */
    private List<Card> pokerList;
    /**
     * 所有的已知牌
     */
    private List<Card> allHaveShowCardList;
    /**
     * 游戏玩家列表
     */
    private List<SuoHaPlayer> playerList;
    /**
     * 新加游戏玩家列表
     */
    private List<SuoHaPlayer> addPlayerList;
    /**
     * 离开游戏玩家列表
     */
    private List<SuoHaPlayer> leavePlayerList;
    /**
     * 当前游戏投注总金额
     */
    private Integer allAmount;
    /**
     * 房间开始时间
     */
    private Date startTime;

    public String getGameRoomNo() {
        return gameRoomNo;
    }

    public void setGameRoomNo(String gameRoomNo) {
        this.gameRoomNo = gameRoomNo;
    }

    public Boolean getNeedPassWord() {
        return isNeedPassWord;
    }

    public void setNeedPassWord(Boolean needPassWord) {
        isNeedPassWord = needPassWord;
    }

    public Boolean getPlaying() {
        return isPlaying;
    }

    public void setPlaying(Boolean playing) {
        isPlaying = playing;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public List<Card> getPokerList() {
        return pokerList;
    }

    public void setPokerList(List<Card> pokerList) {
        this.pokerList = pokerList;
    }

    public List<SuoHaPlayer> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<SuoHaPlayer> playerList) {
        this.playerList = playerList;
    }

    public Integer getAllAmount() {
        return allAmount;
    }

    public void setAllAmount(Integer allAmount) {
        this.allAmount = allAmount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public List<Card> getAllHaveShowCardList() {
        return allHaveShowCardList;
    }

    public void setAllHaveShowCardList(List<Card> allHaveShowCardList) {
        this.allHaveShowCardList = allHaveShowCardList;
    }

    public List<SuoHaPlayer> getAddPlayerList() {
        return addPlayerList;
    }

    public void setAddPlayerList(List<SuoHaPlayer> addPlayerList) {
        this.addPlayerList = addPlayerList;
    }

    public List<SuoHaPlayer> getLeavePlayerList() {
        return leavePlayerList;
    }

    public void setLeavePlayerList(List<SuoHaPlayer> leavePlayerList) {
        this.leavePlayerList = leavePlayerList;
    }
}
