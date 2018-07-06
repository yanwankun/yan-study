package yan.study.game.puke.suoha.enums;

/**
 * Created
 * User  wankunYan
 * Date  2018/7/6
 * Time  14:32
 */
public enum SuoHaCardTypeEnum {

    SAME_FLOWER_AND_SEQUENCE(1, "同花顺"),
    FOUR(2, "四条"),
    GOURD(3, "葫芦"),
    SAME_FLOWER(4, "同花"),
    SEQUENCE(5, "顺子"),
    THREE(6, "三条"),
    DOUBLE_COUPLET(7, "两对"),
    SINGLE_COUPLET(8, "单对"),
    HIGH_CARD(9, "高牌"),
    ;


    private Integer key;
    private String value;

    SuoHaCardTypeEnum(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    public Integer getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
