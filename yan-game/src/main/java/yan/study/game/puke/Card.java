package yan.study.game.puke;

import java.util.Arrays;
import java.util.List;

public class Card implements Comparable {

    public static final List<String> POKER_VALUE_LIST = Arrays.asList("A","K","Q","J","10","9","8","7","6","5","4","3","2");
    public static final List<String> POKER_COLOR_LIST = Arrays.asList("黑桃","红桃","梅花","方片");
    public static final String MAX_KING = "大王";
    public static final String SMALL_KING = "小王";

    private String color;
    private String value;

    public Card(String color, String value) {
        this.color = color;
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Card{" +
                "color='" + color + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    /**
     * 判断牌是不是王
     * @return
     */
    private boolean isKing() {
        if (this.getValue().equals(Card.MAX_KING) || this.getValue().equals(Card.SMALL_KING)) {
            return true;
        }
        return false;
    }

    /**
     * 牌比较大小的方法
     * @param o
     * @return
     */
    public int compareTo(Object o) {
        if (! (o instanceof Card) ) {
            return -1;
        }
        Card card = (Card)o;
        /**
         * 相等
         */
        if (card.getValue().equals(this.getValue()) && card.getColor().equals(this.getColor())) {
            return 0;
        }
        /**
         * 分两种判断方式，有王和没有王
         */
        if (this.isKing()) {
            if (card.isKing()) {
                return compareToHaveKing(this);
            }
            return 1;
        } else {
            return compareToNoKing(this, card);
        }

    }

    /**
     * 有王的比较大小
     * @param one
     * @return
     */
    private int compareToHaveKing(Card one) {
        if (one.getColor().equals(Card.POKER_COLOR_LIST.get(1))) {
            return 1;
        } else {
            return -1;
        }
    }

    /**
     * 没有王的比较大小
     * @param one
     * @param two
     * @return
     */
    private int compareToNoKing(Card one, Card two) {
        int valueOne = Card.POKER_VALUE_LIST.indexOf(one.getValue());
        int valueTwo = Card.POKER_VALUE_LIST.indexOf(two.getValue());
        if (valueOne == valueTwo) {
            int colorOne = Card.POKER_COLOR_LIST.indexOf(one.getColor());
            int colorTwo = Card.POKER_COLOR_LIST.indexOf(two.getColor());
            return colorTwo - colorOne;
        } else {
            return valueTwo - valueOne;
        }
    }
}
