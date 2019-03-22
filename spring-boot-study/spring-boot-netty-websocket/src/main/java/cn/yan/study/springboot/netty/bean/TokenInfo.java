package cn.yan.study.springboot.netty.bean;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
public class TokenInfo {
    private Boolean isKLine;
    private String solution;
    private String tradeString;

    public TokenInfo(Boolean isKLine, String solution, String tradeString) {
        this.isKLine = isKLine;
        this.solution = solution;
        this.tradeString = tradeString;
    }

    public Boolean getKLine() {
        return isKLine;
    }

    public void setKLine(Boolean KLine) {
        isKLine = KLine;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getTradeString() {
        return tradeString;
    }

    public void setTradeString(String tradeString) {
        this.tradeString = tradeString;
    }
}
