package yan.study.game.puke.suoha;

import yan.study.game.puke.Card;

import java.util.List;

/**
 * Created
 * User  wankunYan
 * Date  2018/7/6
 * Time  14:31
 */
public class SuoHaCard {
    /**
     * 牌的张数
     */
    private Integer count;
    /**
     * 隐藏牌
     */
    private Card hideCard;
    /**
     * 公开牌
     */
    private List<Card> cardList;
    /**
     * 自己牌的类型
     */
    private Integer cardType;
    /**
     * 对外显示的牌的类型
     */
    private Integer showCardType;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Card getHideCard() {
        return hideCard;
    }

    public void setHideCard(Card hideCard) {
        this.hideCard = hideCard;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Integer getShowCardType() {
        return showCardType;
    }

    public void setShowCardType(Integer showCardType) {
        this.showCardType = showCardType;
    }
}
