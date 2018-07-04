package yan.study.game.puke;

import java.util.ArrayList;
import java.util.List;

public class Person {
    /**
     * 姓名
     */
    private String name;
    /**
     *
     */
    private List<Card> cardList;

    public Person(String name) {
        this.name = name;
        cardList = new ArrayList<Card>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void setCardList(List<Card> cardList) {
        this.cardList = cardList;
    }
}
