package yan.study.game.puke.suoha;

import yan.study.game.puke.Card;

import java.util.List;

/**
 * Created 梭哈游戏玩家
 * User  wankunYan
 * Date  2018/7/5
 * Time  17:33
 */
public class SuoHaPlayer {
    /**
     * 玩家名称
     */
    private String name;
    /**
     * 用户金额
     */
    private Integer amount;
    /**
     * 玩家的牌
     */
    private SuoHaCard suoHaCard;
    /**
     * 当前轮次下注金额
     */
    private Integer betAmount;
    /**
     * 所有下注金额
     */
    private Integer allBetAmount;
    /**
     * 是否allIn
     */
    private Boolean isAllIn;
    /**
     * 是否正在玩
     */
    private Boolean isPlay;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(Integer betAmount) {
        this.betAmount = betAmount;
    }

    public Integer getAllBetAmount() {
        return allBetAmount;
    }

    public void setAllBetAmount(Integer allBetAmount) {
        this.allBetAmount = allBetAmount;
    }

    public Boolean getAllIn() {
        return isAllIn;
    }

    public void setAllIn(Boolean allIn) {
        isAllIn = allIn;
    }

    public Boolean getPlay() {
        return isPlay;
    }

    public void setPlay(Boolean play) {
        isPlay = play;
    }

    public SuoHaCard getSuoHaCard() {
        return suoHaCard;
    }

    public void setSuoHaCard(SuoHaCard suoHaCard) {
        this.suoHaCard = suoHaCard;
    }

    @Override
    public String toString() {
        return "SuoHaPlayer{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", suoHaCard=" + suoHaCard +
                ", betAmount=" + betAmount +
                ", allBetAmount=" + allBetAmount +
                ", isAllIn=" + isAllIn +
                ", isPlay=" + isPlay +
                '}';
    }
}
