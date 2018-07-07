package yan.study.game.puke.suoha;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created
 * User  wankunYan
 * Date  2018/7/6
 * Time  10:04
 */
public class SuoHaGameTest {

    private static Logger LOGGER = LoggerFactory.getLogger(SuoHaGameTest.class);

    private static final Integer init_amount = 5000;


    public static void main(String[] args) {

        // 创建游戏房间
        String roomId = SuoHaGameUtils.createGameRoom(null);

        // 加入游戏房间
        List<String> gameNameList = Arrays.asList("yan", "he", "wang");
        boolean isEnter;
        for (String name : gameNameList) {
            SuoHaPlayer player = new SuoHaPlayer();
            player.setName(name);
            player.setAmount(init_amount);
            SuoHaCard suoHaCard = new SuoHaCard();
            suoHaCard.setCardList(new ArrayList<>());
            player.setSuoHaCard(suoHaCard);
            isEnter = SuoHaGameUtils.enterGameRoom(player, roomId, null, 0);
            if (!isEnter) {
                // 添加失败，表示游戏玩家数量过多，结束添加
                break;
            }
        }

        boolean start = SuoHaGameUtils.startGame(roomId);
        if (start) {
            LOGGER.error("游戏开始失败,退出");
            System.exit(1);
        }

        for (int round = 0; round < 5; round++) {
            SuoHaGameUtils.sendCard(roomId, round);
            SuoHaGameUtils.gameBet(roomId);
            boolean isEnd = SuoHaGameUtils.verifyGameIsEnd(roomId);
            if (isEnd) {
                SuoHaGameUtils.settleAccounts(roomId);
                break;
            }
        }


    }
}
