package yan.study.game.puke.suoha;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class game {

	public static void main(String[] args) {
		// **********************hashmap-Message****************************//
		//
		final HashMap<String, Integer> MsgCommand = new HashMap<String, Integer>();
		MsgCommand.put("seat/ ", 1);// seat flag
		MsgCommand.put("blind/ ", 2);// bind flag
		MsgCommand.put("hold/ ", 3);// hold flag
		MsgCommand.put("flop/ ", 4);// flop flag
		MsgCommand.put("turn/ ", 5);// turn flag
		MsgCommand.put("river/ ", 6);// river flag
		MsgCommand.put("inquire/ ", 7);// inquire flag
		MsgCommand.put("showdown/ ", 8);// showdown flag
		MsgCommand.put("pot-win/ ", 9);// pot flag
		int ServerCommand;
		try {
			// Socket link
			Socket socket = new Socket("192.169.43.239", 8080,
					InetAddress.getByName("192.169.43.239"), 8080);
			
			// OutputStream 鍚慡ever鍙戦�佹敞鍐屾秷鎭�
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
		//	pw.write("reg: " + args[4] + " xsfelvis \n");
			pw.flush();
			// InputStream 鎺ュ彈Sever鐨勬秷鎭�
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			// define Msg StringBuffer 瀛樺偍娑堟伅
			StringBuffer SeatMsg = new StringBuffer("");
			StringBuffer BlindMsg = new StringBuffer("");
			StringBuffer CardsMsg = new StringBuffer("");
			StringBuffer InquireMsg = new StringBuffer("");
			StringBuffer ShowdownMsg = new StringBuffer("");
			StringBuffer PotWinMsg = new StringBuffer("");
			String info;
			String ActMsg;
			int Seat_num = 0;
			//寮�濮嬫秷鎭В鏋愬瓨鍌ㄥ埌瀵瑰簲鐨凷tringBuffer涓�
			while (true) {
				info = br.readLine();// get a line of info
				ServerCommand = MsgCommand.get(info);// get hashmap value
				switch (ServerCommand) {
				case 1: {
					while (info != null) {
						info = br.readLine();
						if (info.equals("/seat "))
							break;
						Seat_num++;
						SeatMsg.append(info);
					}
					break;
				}
				case 2: {
					while (info != null) {
						info = br.readLine();
						if (info.equals("/blind "))
							break;
						BlindMsg.append(info);
					}
					break;
				}
				case 3: {
					while (info != null) {
						info = br.readLine();
						if (info.equals("/hold "))
							break;
						CardsMsg.append(info);
					}
					break;
				}
				case 4: {
					while (info != null) {
						info = br.readLine();
						if (info.equals("/flop "))
							break;
						CardsMsg.append(info);
					}
					break;
				}
				case 5: {
					while (info != null) {
						info = br.readLine();
						if (info.equals("/turn "))
							break;
						CardsMsg.append(info);
					}
					break;
				}
				case 6: {
					while (info != null) {
						info = br.readLine();
						if (info.equals("/river "))
							break;
						CardsMsg.append(info);
					}
					break;
				}
				case 7: {
					while (info != null) {
						info = br.readLine();
						if (info.equals("/inquire "))
							break;
						InquireMsg.append(info);
					}
					break;
				}
				case 8: {
					while (info != null) {
						info = br.readLine();
						if (info.equals("/showdown "))
							break;
						ShowdownMsg.append(info);
					}
					break;
				}
				case 9: {
					while (info != null) {
						info = br.readLine();
						if (info.equals("/pot-win "))
							break;
						PotWinMsg.append(info);
					}
					break;
				}
				default:
					break;
				}
				if (info.equals("/inquire ")) {
					//鍑虹墝绠楁硶 鏍稿績鎵�鍦紒
					ActMsg = Alg(CardsMsg.toString(), InquireMsg.toString(),
							args[4], Seat_num);
					InquireMsg = new StringBuffer("");
					pw.write(ActMsg);
					pw.flush();
				}
				if (info.equals("/pot-win ")) {
					Seat_num = 0;
					SeatMsg = new StringBuffer("");
					BlindMsg = new StringBuffer("");
					CardsMsg = new StringBuffer("");
					ShowdownMsg = new StringBuffer("");
					PotWinMsg = new StringBuffer("");
				}
				if (info.equals("game-over ")) {
					br.close();
					isr.close();
					pw.close();
					os.close();
					socket.close();
					break;
				}
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// Algorithm
	private static String Alg(String strCard, String strInquire, String myID,
			int seatNum) {
		// ******************************Hashmap-Card*************************************//
		final HashMap<String, Integer> MsgCard = new HashMap<String, Integer>();
		MsgCard.put("A", 14);
		MsgCard.put("K", 13);
		MsgCard.put("Q", 12);
		MsgCard.put("J", 11);
		MsgCard.put("10", 10);
		MsgCard.put("9", 9);
		MsgCard.put("8", 8);
		MsgCard.put("7", 7);
		MsgCard.put("6", 6);
		MsgCard.put("5", 5);
		MsgCard.put("4", 4);
		MsgCard.put("3", 3);
		MsgCard.put("2", 2);
		String str_card[] = strCard.split(" ");// string division
		String str_inq[] = strInquire.split(" ");// string division
		// ********************get money info begin**********************//
		int maxMoney = 0;
		int myMoney = 0;
		int Money[] = new int[(str_inq.length - 3) / 5];
		int j = 0;
		int fold_num = 0;
		int isMyLast;
		for (int i = 0; i < str_inq.length - 3; i++) {
			if (i % 5 == 3) {
				Money[j] = Integer.parseInt(str_inq[i]);
				j++;
			}
			if (str_inq[i].equals("fold")) {
				fold_num++;
			}
			if (str_inq[i].equals(myID))
				myMoney = Integer.parseInt(str_inq[i + 3]);
		}
		maxMoney = getMax(Money);
		if (fold_num == (seatNum - 1))
			isMyLast = 1;
		else
			isMyLast = 0;
		// ********************get money info end***********************//

		// ****************get card info and call algorithm*************//
		String Msg;
		if (str_card.length == 4) {
			ArrayList<Card> listCard = new ArrayList<Card>();
			for (int i = 0; i < 2; i++) {
				listCard.add(new Card(str_card[2 * i], MsgCard
						.get(str_card[2 * i + 1])));
			}
			Collections.sort(listCard, Collections.reverseOrder());
			Msg = Turn_one(listCard.get(0).getColor(),
					listCard.get(0).getNum(), listCard.get(1).getColor(),
					listCard.get(1).getNum(), maxMoney, myMoney, isMyLast);
		} else if (str_card.length == 10) {
			ArrayList<Card> listCard = new ArrayList<Card>();
			for (int i = 0; i < 5; i++) {
				listCard.add(new Card(str_card[2 * i], MsgCard
						.get(str_card[2 * i + 1])));
			}
			Collections.sort(listCard, Collections.reverseOrder());
			Msg = Turn_two(listCard.get(0).getColor(),
					listCard.get(0).getNum(), listCard.get(1).getColor(),
					listCard.get(1).getNum(), listCard.get(2).getColor(),
					listCard.get(2).getNum(), listCard.get(3).getColor(),
					listCard.get(3).getNum(), listCard.get(4).getColor(),
					listCard.get(4).getNum(), maxMoney, myMoney, isMyLast);
		} else if (str_card.length == 12) {
			ArrayList<Card> listCard = new ArrayList<Card>();
			for (int i = 0; i < 6; i++) {
				listCard.add(new Card(str_card[2 * i], MsgCard
						.get(str_card[2 * i + 1])));
			}
			Collections.sort(listCard, Collections.reverseOrder());
			Msg = Turn_three(listCard.get(0).getColor(), listCard.get(0)
					.getNum(), listCard.get(1).getColor(), listCard.get(1)
					.getNum(), listCard.get(2).getColor(), listCard.get(2)
					.getNum(), listCard.get(3).getColor(), listCard.get(3)
					.getNum(), listCard.get(4).getColor(), listCard.get(4)
					.getNum(), listCard.get(5).getColor(), listCard.get(5)
					.getNum(), maxMoney, myMoney, isMyLast);
		} else {
			ArrayList<Card> listCard = new ArrayList<Card>();
			for (int i = 0; i < 7; i++) {
				listCard.add(new Card(str_card[2 * i], MsgCard
						.get(str_card[2 * i + 1])));
			}
			Collections.sort(listCard, Collections.reverseOrder());
			Msg = Turn_four(listCard.get(0).getColor(), listCard.get(0)
					.getNum(), listCard.get(1).getColor(), listCard.get(1)
					.getNum(), listCard.get(2).getColor(), listCard.get(2)
					.getNum(), listCard.get(3).getColor(), listCard.get(3)
					.getNum(), listCard.get(4).getColor(), listCard.get(4)
					.getNum(), listCard.get(5).getColor(), listCard.get(5)
					.getNum(), listCard.get(6).getColor(), listCard.get(6)
					.getNum(), maxMoney, myMoney, isMyLast);
		}
		return Msg;
	}

	// ****algorithm*****//
	// ****************************The First
	// Turn**********************************//
	private static String Turn_one(String cardmsg1, int cardnum1,
			String cardmsg2, int cardnum2, int PlayerMaxCash, int MyCash,
			int IsMyLast) {
		int flag;
		String msg;
		if (IsMyLast == 1)
			flag = 1;// all_in
		else {
			if (cardnum1 == cardnum2)
				if (cardnum1 == 14)
					flag = 1;// all_in
				else if (cardnum1 == 13 || cardnum1 == 12)
					flag = 2;// raise 100
				else if (cardnum1 == 11 || cardnum1 == 10 || cardnum1 == 9
						|| cardnum1 == 8 || cardnum1 == 7) {
					if (MyCash == 0 && PlayerMaxCash <= 500)
						flag = 3;// call
					else if (MyCash < PlayerMaxCash
							&& PlayerMaxCash <= 3 * MyCash)
						flag = 3;// call
					else if (MyCash >= PlayerMaxCash)
						flag = 4;// check
					else
						flag = 5;// fold
				} else if (cardnum1 == 6 || cardnum1 == 5 || cardnum1 == 4
						|| cardnum1 == 3 || cardnum1 == 2) {
					if (MyCash == 0 && PlayerMaxCash <= 100)
						flag = 3;// call
					else if (MyCash < PlayerMaxCash
							&& PlayerMaxCash <= 2 * MyCash)
						flag = 3;// call
					else if (MyCash >= PlayerMaxCash)
						flag = 4;// check
					else
						flag = 5;// fold

				} else
					flag = 5;// fold
			else if (cardmsg1 == cardmsg2)
				if (cardnum1 == 14 && cardnum2 == 13 || cardnum1 == 13
						&& cardnum2 == 14)
					flag = 2;// raise 100
				else if (cardnum1 == 14 && cardnum2 == 12 || cardnum1 == 12
						&& cardnum2 == 14) {
					if (MyCash == 0 && PlayerMaxCash <= 500)
						flag = 3;// call
					else if (MyCash < PlayerMaxCash
							&& PlayerMaxCash <= 3 * MyCash)
						flag = 3;// call
					else if (MyCash >= PlayerMaxCash)
						flag = 4;// check
					else
						flag = 5;// fold
				} else if (cardnum1 == 14 && cardnum2 == 11 || cardnum1 == 11
						&& cardnum2 == 14 || cardnum1 == 13 && cardnum2 == 12
						|| cardnum1 == 12 && cardnum2 == 13) {
					if (MyCash == 0 && PlayerMaxCash <= 100)
						flag = 3;// call
					else if (MyCash < PlayerMaxCash
							&& PlayerMaxCash <= 2 * MyCash)
						flag = 3;// call
					else if (MyCash >= PlayerMaxCash)
						flag = 4;// check
					else
						flag = 5;// fold
				} else {
					if (MyCash == PlayerMaxCash)
						flag = 4;// check
					else
						flag = 5;// fold
				}
			else if (cardnum1 == 14 && cardnum2 == 13 || cardnum1 == 13
					&& cardnum2 == 14) {
				if (MyCash == 0 && PlayerMaxCash <= 500)
					flag = 3;// call
				else if (MyCash < PlayerMaxCash && PlayerMaxCash <= 2 * MyCash)
					flag = 3;// call
				else if (MyCash >= PlayerMaxCash)
					flag = 4;// check
				else
					flag = 5;// fold
			} else if (cardnum1 == 14 && cardnum2 == 12 || cardnum1 == 12
					&& cardnum2 == 14 || cardnum1 == 13 && cardnum2 == 12
					|| cardnum1 == 12 && cardnum2 == 13) {
				if (MyCash == 0 && PlayerMaxCash <= 300)
					flag = 3;// call
				else if (MyCash < PlayerMaxCash && PlayerMaxCash <= 2 * MyCash)
					flag = 3;// call
				else if (MyCash >= PlayerMaxCash)
					flag = 4;// check
				else
					flag = 5;// fold
			} else {
				if (MyCash >= PlayerMaxCash)
					flag = 4;// check
				else
					flag = 5;// fold
			}
		}

		msg = action(flag);
		return msg;
	}

	// ****************************The Second
	// Turn**********************************//
	private static String Turn_two(String cardmsg1, int cardnum1,
			String cardmsg2, int cardnum2, String cardmsg3, int cardnum3,
			String cardmsg4, int cardnum4, String cardmsg5, int cardnum5,
			int PlayerMaxCash, int MyCash, int IsMyLast) {
		int flag;
		String msg;
		if (IsMyLast == 1)
			flag = 1;// all_in
		else {
			if (cardmsg1 == cardmsg2 && cardmsg2 == cardmsg3
					&& cardmsg3 == cardmsg4 && cardmsg4 == cardmsg5)
				flag = 1;// all_in
			else if ((cardnum1 == cardnum2 && cardnum2 == cardnum3 && cardnum3 == cardnum4)
					|| (cardnum2 == cardnum3 && cardnum3 == cardnum4 && cardnum4 == cardnum5))
				flag = 1;// all_in
			else if ((cardnum1 == cardnum2 && cardnum2 == cardnum3 && cardnum4 == cardnum5)
					|| (cardnum1 == cardnum2 && cardnum3 == cardnum4 && cardnum4 == cardnum5))
				flag = 1;// all_in
			else if (cardnum5 == (cardnum4 - 1) && cardnum4 == (cardnum3 - 1)
					&& cardnum3 == (cardnum2 - 1) && cardnum2 == (cardnum1 - 1))
				flag = 1;// all_in
			else if ((cardnum1 == cardnum2 && cardnum2 == cardnum3)
					|| (cardnum2 == cardnum3 && cardnum3 == cardnum4)
					|| (cardnum3 == cardnum4 && cardnum4 == cardnum5))
				flag = 2;// raise 100
			else if ((cardnum1 == cardnum2 && cardnum3 == cardnum4)
					|| (cardnum1 == cardnum2 && cardnum4 == cardnum5)
					|| (cardnum2 == cardnum3 && cardnum4 == cardnum5)) {
				if ((cardnum2 == 14 && cardnum4 == 13)
						|| (cardnum2 == 14 && cardnum4 == 12)
						|| (cardnum2 == 14 && cardnum4 == 11)
						|| (cardnum2 == 14 && cardnum4 == 10)
						|| (cardnum2 == 13 && cardnum4 == 12)
						|| (cardnum2 == 13 && cardnum4 == 11)
						|| (cardnum2 == 13 && cardnum4 == 10)
						|| (cardnum2 == 12 && cardnum4 == 11)
						|| (cardnum2 == 12 && cardnum4 == 10)
						|| (cardnum2 == 11 && cardnum4 == 10))
					if (MyCash < PlayerMaxCash && PlayerMaxCash <= 2 * MyCash
							&& PlayerMaxCash <= 500)
						flag = 3;// call
					else if (MyCash >= PlayerMaxCash)
						flag = 4;// check
					else
						flag = 5;// fold
				else {
					if (MyCash < PlayerMaxCash && PlayerMaxCash <= 2 * MyCash
							&& PlayerMaxCash <= 200)
						flag = 3;// call
					else if (MyCash >= PlayerMaxCash)
						flag = 4;// check
					else
						flag = 5;// fold
				}
			} else if (cardnum1 == cardnum2 || cardnum2 == cardnum3
					|| cardnum3 == cardnum4 || cardnum4 == cardnum5) {
				if (MyCash < PlayerMaxCash && PlayerMaxCash <= 2 * MyCash
						&& PlayerMaxCash <= 100)
					flag = 3;// call
				else if (MyCash >= PlayerMaxCash)
					flag = 4;// check
				else
					flag = 5;// fold
			} else {
				if (MyCash >= PlayerMaxCash)
					flag = 4;// check
				else
					flag = 5;// fold
			}
		}
		msg = action(flag);
		return msg;
	}

	// ****************************The third
	// Turn**********************************//
	private static String Turn_three(String cardmsg1, int cardnum1,
			String cardmsg2, int cardnum2, String cardmsg3, int cardnum3,
			String cardmsg4, int cardnum4, String cardmsg5, int cardnum5,
			String cardmsg6, int cardnum6, int PlayerMaxCash, int MyCash,
			int IsMyLast) {
		int flag = 0;
		String msg;
		if (IsMyLast == 1)
			flag = 1;// all_in
		else {
			if ((cardmsg1 == cardmsg2 && cardmsg2 == cardmsg3
					&& cardmsg3 == cardmsg4 && cardmsg4 == cardmsg5)
					|| (cardmsg1 == cardmsg2 && cardmsg2 == cardmsg3
							&& cardmsg3 == cardmsg4 && cardmsg4 == cardmsg6)
					|| (cardmsg1 == cardmsg2 && cardmsg2 == cardmsg3
							&& cardmsg3 == cardmsg5 && cardmsg5 == cardmsg6)
					|| (cardmsg1 == cardmsg2 && cardmsg2 == cardmsg4
							&& cardmsg4 == cardmsg5 && cardmsg5 == cardmsg6)
					|| (cardmsg1 == cardmsg3 && cardmsg3 == cardmsg4
							&& cardmsg4 == cardmsg5 && cardmsg5 == cardmsg6)
					|| (cardmsg2 == cardmsg3 && cardmsg3 == cardmsg4
							&& cardmsg4 == cardmsg5 && cardmsg5 == cardmsg6))
				flag = 1;// all_in
			else if ((cardnum1 == cardnum2 && cardnum2 == cardnum3 && cardnum3 == cardnum4)
					|| (cardnum2 == cardnum3 && cardnum3 == cardnum4 && cardnum4 == cardnum5)
					|| (cardnum3 == cardnum4 && cardnum4 == cardnum5 && cardnum5 == cardnum6))
				flag = 1;// all_in
			else if ((cardnum1 == cardnum2 && cardnum2 == cardnum3 && cardnum4 == cardnum5)
					|| (cardnum1 == cardnum2 && cardnum2 == cardnum3 && cardnum5 == cardnum6)
					|| (cardnum2 == cardnum3 && cardnum3 == cardnum4 && cardnum5 == cardnum6)
					|| (cardnum1 == cardnum2 && cardnum3 == cardnum4 && cardnum4 == cardnum5)
					|| (cardnum1 == cardnum2 && cardnum4 == cardnum5 && cardnum5 == cardnum6)
					|| (cardnum2 == cardnum3 && cardnum4 == cardnum5 && cardnum5 == cardnum6))
				flag = 1;// all_in
			else if ((cardnum5 == (cardnum4 - 1) && cardnum4 == (cardnum3 - 1)
					&& cardnum3 == (cardnum2 - 1) && cardnum2 == (cardnum1 - 1))
					|| (cardnum6 == (cardnum5 - 1)
							&& cardnum5 == (cardnum4 - 1)
							&& cardnum4 == (cardnum3 - 1) && cardnum3 == (cardnum2 - 1)))
				flag = 1;// all_in
			else if ((cardnum1 == cardnum2 && cardnum2 == cardnum3)
					|| (cardnum2 == cardnum3 && cardnum3 == cardnum4)
					|| (cardnum3 == cardnum4 && cardnum4 == cardnum5)
					|| (cardnum4 == cardnum5 && cardnum5 == cardnum6))
				flag = 2;// raise 100
			else if ((cardnum1 == cardnum2 && cardnum3 == cardnum4)
					|| (cardnum1 == cardnum2 && cardnum4 == cardnum5)
					|| (cardnum1 == cardnum2 && cardnum5 == cardnum6)
					|| (cardnum2 == cardnum3 && cardnum4 == cardnum5)
					|| (cardnum2 == cardnum3 && cardnum5 == cardnum6)
					|| (cardnum3 == cardnum4 && cardnum5 == cardnum6)) {
				if ((cardnum1 == cardnum2 && cardnum3 == cardnum4)
						|| (cardnum1 == cardnum2 && cardnum4 == cardnum5)
						|| (cardnum2 == cardnum3 && cardnum4 == cardnum5)) {
					if ((cardnum2 == 14 && cardnum4 == 13)
							|| (cardnum2 == 14 && cardnum4 == 12)
							|| (cardnum2 == 14 && cardnum4 == 11)
							|| (cardnum2 == 14 && cardnum4 == 10)
							|| (cardnum2 == 13 && cardnum4 == 12)
							|| (cardnum2 == 13 && cardnum4 == 11)
							|| (cardnum2 == 13 && cardnum4 == 10)
							|| (cardnum2 == 12 && cardnum4 == 11)
							|| (cardnum2 == 12 && cardnum4 == 10)
							|| (cardnum2 == 11 && cardnum4 == 10)) {
						if (MyCash < PlayerMaxCash
								&& PlayerMaxCash <= 2 * MyCash
								&& PlayerMaxCash <= 500)
							flag = 3;// call
						else if (MyCash >= PlayerMaxCash)
							flag = 4;// check
						else
							flag = 5;// fold
					} else {
						if (MyCash < PlayerMaxCash
								&& PlayerMaxCash <= 2 * MyCash
								&& PlayerMaxCash <= 200)
							flag = 3;// call
						else if (MyCash >= PlayerMaxCash)
							flag = 4;// check
						else
							flag = 5;// fold
					}
				}

				else if ((cardnum1 == cardnum2 && cardnum5 == cardnum6)
						|| (cardnum2 == cardnum3 && cardnum5 == cardnum6)) {
					if ((cardnum2 == 14 && cardnum5 == 13)
							|| (cardnum2 == 14 && cardnum5 == 12)
							|| (cardnum2 == 14 && cardnum5 == 11)
							|| (cardnum2 == 14 && cardnum5 == 10)
							|| (cardnum2 == 13 && cardnum5 == 12)
							|| (cardnum2 == 13 && cardnum5 == 11)
							|| (cardnum2 == 13 && cardnum5 == 10)
							|| (cardnum2 == 12 && cardnum5 == 11)
							|| (cardnum2 == 12 && cardnum5 == 10)
							|| (cardnum2 == 11 && cardnum5 == 10)) {
						if (MyCash < PlayerMaxCash
								&& PlayerMaxCash <= 2 * MyCash
								&& PlayerMaxCash <= 500)
							flag = 3;// call
						else if (MyCash >= PlayerMaxCash)
							flag = 4;// check
						else
							flag = 5;// fold
					}

					else {
						if (MyCash < PlayerMaxCash
								&& PlayerMaxCash <= 2 * MyCash
								&& PlayerMaxCash <= 200)
							flag = 3;// call
						else if (MyCash >= PlayerMaxCash)
							flag = 4;// check
						else
							flag = 5;// fold
					}
				} else if (cardnum3 == cardnum4 && cardnum5 == cardnum6) {
					if ((cardnum2 == 14 && cardnum5 == 13)
							|| (cardnum2 == 14 && cardnum5 == 12)
							|| (cardnum2 == 14 && cardnum5 == 11)
							|| (cardnum2 == 14 && cardnum5 == 10)
							|| (cardnum2 == 13 && cardnum5 == 12)
							|| (cardnum2 == 13 && cardnum5 == 11)
							|| (cardnum2 == 13 && cardnum5 == 10)
							|| (cardnum2 == 12 && cardnum5 == 11)
							|| (cardnum2 == 12 && cardnum5 == 10)
							|| (cardnum2 == 11 && cardnum5 == 10)) {
						if (MyCash < PlayerMaxCash
								&& PlayerMaxCash <= 2 * MyCash
								&& PlayerMaxCash <= 500)
							flag = 3;// call
						else if (MyCash >= PlayerMaxCash)
							flag = 4;// check
						else
							flag = 5;// fold
					} else {
						if (MyCash < PlayerMaxCash
								&& PlayerMaxCash <= 2 * MyCash
								&& PlayerMaxCash <= 200)
							flag = 3;// call
						else if (MyCash >= PlayerMaxCash)
							flag = 4;// check
						else
							flag = 5;// fold
					}
				}

			}

			else if (cardnum1 == cardnum2 || cardnum2 == cardnum3
					|| cardnum3 == cardnum4 || cardnum4 == cardnum5
					|| cardnum5 == cardnum6) {
				if (MyCash >= PlayerMaxCash)
					flag = 4;// check
				else
					flag = 5;// fold
			} else {
				if (MyCash >= PlayerMaxCash)
					flag = 4;// check
				else
					flag = 5;// fold
			}
		}
		msg = action(flag);
		return msg;
	}

	// ****************************The Fourth
	// Turn**********************************//
	private static String Turn_four(String cardmsg1, int cardnum1,
			String cardmsg2, int cardnum2, String cardmsg3, int cardnum3,
			String cardmsg4, int cardnum4, String cardmsg5, int cardnum5,
			String cardmsg6, int cardnum6, String cardmsg7, int cardnum7,
			int PlayerMaxCash, int MyCash, int IsMyLast) {
		int flag;
		String msg;
		if (IsMyLast == 1)
			flag = 1;// all_in
		else {
			if ((cardmsg1 == cardmsg2 && cardmsg2 == cardmsg3
					&& cardmsg3 == cardmsg4 && cardmsg4 == cardmsg5)
					|| (cardmsg2 == cardmsg3 && cardmsg3 == cardmsg4
							&& cardmsg4 == cardmsg5 && cardmsg5 == cardmsg6)
					|| (cardmsg3 == cardmsg4 && cardmsg4 == cardmsg5
							&& cardmsg5 == cardmsg6 && cardmsg6 == cardmsg7)
					|| (cardmsg1 == cardmsg2 && cardmsg2 == cardmsg3
							&& cardmsg3 == cardmsg4 && cardmsg4 == cardmsg6)
					|| (cardmsg1 == cardmsg2 && cardmsg2 == cardmsg3
							&& cardmsg3 == cardmsg4 && cardmsg4 == cardmsg7)
					|| (cardmsg1 == cardmsg3 && cardmsg3 == cardmsg4
							&& cardmsg4 == cardmsg5 && cardmsg5 == cardmsg6)
					|| (cardmsg1 == cardmsg4 && cardmsg4 == cardmsg5
							&& cardmsg5 == cardmsg6 && cardmsg6 == cardmsg7)
					|| (cardmsg2 == cardmsg4 && cardmsg4 == cardmsg5
							&& cardmsg5 == cardmsg6 && cardmsg6 == cardmsg7)
					|| (cardmsg1 == cardmsg2 && cardmsg2 == cardmsg3
							&& cardmsg3 == cardmsg5 && cardmsg5 == cardmsg6)
					|| (cardmsg1 == cardmsg2 && cardmsg2 == cardmsg3
							&& cardmsg3 == cardmsg6 && cardmsg6 == cardmsg7)
					|| (cardmsg2 == cardmsg3 && cardmsg3 == cardmsg4
							&& cardmsg4 == cardmsg6 && cardmsg6 == cardmsg7)
					|| (cardmsg1 == cardmsg2 && cardmsg2 == cardmsg4
							&& cardmsg4 == cardmsg5 && cardmsg5 == cardmsg6)
					|| (cardmsg1 == cardmsg2 && cardmsg2 == cardmsg5
							&& cardmsg5 == cardmsg6 && cardmsg6 == cardmsg7)
					|| (cardmsg2 == cardmsg3 && cardmsg3 == cardmsg5
							&& cardmsg5 == cardmsg6 && cardmsg6 == cardmsg7)
					|| (cardmsg1 == cardmsg2 && cardmsg2 == cardmsg4
							&& cardmsg4 == cardmsg5 && cardmsg5 == cardmsg7)
					|| (cardmsg1 == cardmsg2 && cardmsg2 == cardmsg4
							&& cardmsg4 == cardmsg6 && cardmsg6 == cardmsg7)
					|| (cardmsg1 == cardmsg3 && cardmsg3 == cardmsg4
							&& cardmsg4 == cardmsg6 && cardmsg6 == cardmsg7)
					|| (cardmsg2 == cardmsg3 && cardmsg3 == cardmsg4
							&& cardmsg4 == cardmsg5 && cardmsg5 == cardmsg7))
				flag = 1;// all_in
			else if ((cardnum1 == cardnum2 && cardnum2 == cardnum3 && cardnum3 == cardnum4)
					|| (cardnum2 == cardnum3 && cardnum3 == cardnum4 && cardnum4 == cardnum5)
					|| (cardnum3 == cardnum4 && cardnum4 == cardnum5 && cardnum5 == cardnum6)
					|| (cardnum4 == cardnum5 && cardnum5 == cardnum6 && cardnum6 == cardnum7))
				flag = 1;// all_in
			else if ((cardnum1 == cardnum2 && cardnum2 == cardnum3 && cardnum4 == cardnum5)
					|| (cardnum1 == cardnum2 && cardnum2 == cardnum3 && cardnum5 == cardnum6)
					|| (cardnum1 == cardnum2 && cardnum2 == cardnum3 && cardnum6 == cardnum7)
					|| (cardnum2 == cardnum3 && cardnum3 == cardnum4 && cardnum5 == cardnum6)
					|| (cardnum2 == cardnum3 && cardnum3 == cardnum4 && cardnum6 == cardnum7)
					|| (cardnum3 == cardnum4 && cardnum4 == cardnum5 && cardnum6 == cardnum7))
				flag = 1;// all_in
			else if ((cardnum5 == (cardnum4 - 1) && cardnum4 == (cardnum3 - 1)
					&& cardnum3 == (cardnum2 - 1) && cardnum2 == (cardnum1 - 1))
					|| (cardnum6 == (cardnum5 - 1)
							&& cardnum5 == (cardnum4 - 1)
							&& cardnum4 == (cardnum3 - 1) && cardnum3 == (cardnum2 - 1))
					|| (cardnum7 == (cardnum6 - 1)
							&& cardnum6 == (cardnum5 - 1)
							&& cardnum5 == (cardnum4 - 1) && cardnum4 == (cardnum3 - 1)))
				flag = 1;// all_in
			else if ((cardnum1 == cardnum2 && cardnum2 == cardnum3)
					|| (cardnum2 == cardnum3 && cardnum3 == cardnum4)
					|| (cardnum3 == cardnum4 && cardnum4 == cardnum5)
					|| (cardnum4 == cardnum5 && cardnum5 == cardnum6)
					|| (cardnum5 == cardnum6 && cardnum6 == cardnum7))
				flag = 2;// raise 100
			else if ((cardnum1 == cardnum2 && cardnum3 == cardnum4)
					|| (cardnum1 == cardnum2 && cardnum4 == cardnum5)
					|| (cardnum1 == cardnum2 && cardnum5 == cardnum6)
					|| (cardnum1 == cardnum2 && cardnum6 == cardnum7)
					|| (cardnum2 == cardnum3 && cardnum4 == cardnum5)
					|| (cardnum2 == cardnum3 && cardnum5 == cardnum6)
					|| (cardnum2 == cardnum3 && cardnum6 == cardnum7)
					|| (cardnum3 == cardnum4 && cardnum5 == cardnum6)
					|| (cardnum3 == cardnum4 && cardnum6 == cardnum7)
					|| (cardnum4 == cardnum5 && cardnum6 == cardnum7)) {
				if (MyCash >= 1500 && MyCash < PlayerMaxCash)
					flag = 3;// call
				else if (MyCash < 1500 && MyCash >= 1000
						&& MyCash < PlayerMaxCash)
					flag = 3;// call
				else if (MyCash < PlayerMaxCash && PlayerMaxCash <= 2 * MyCash
						&& PlayerMaxCash <= 200)
					flag = 3;// call
				else if (MyCash >= PlayerMaxCash)
					flag = 4;// check
				else
					flag = 5;// fold
			} else if (cardnum1 == cardnum2 || cardnum2 == cardnum3
					|| cardnum3 == cardnum4 || cardnum4 == cardnum5
					|| cardnum5 == cardnum6 || cardnum6 == cardnum7) {
				if (MyCash < PlayerMaxCash && PlayerMaxCash <= 2 * MyCash
						&& PlayerMaxCash <= 100)
					flag = 3;// call
				else if (MyCash >= PlayerMaxCash)
					flag = 4;// check
				else
					flag = 5;// fold
			} else {
				if (MyCash >= PlayerMaxCash)
					flag = 4;// check
				else
					flag = 5;// fold
			}
		}
		msg = action(flag);
		return msg;
	}

	// ************************action**************************//
	private static String action(int Flag) {
		switch (Flag) {
		case 1:
			return "all_in \n";
		case 2:
			return "raise 100 \n";
		case 3:
			return "call \n";
		case 4:
			return "check \n";
		case 5:
			return "fold \n";
		default:
			return "fold \n";
		}
	}

	// *************************get max 閲戦挶纭畾鑷繁鏄惁鍙痗heck杩囧幓***********************//
	private static int getMax(int[] arr) {
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max)
				max = arr[i];
		}
		return max;
	}
}
class Card implements Comparable<Card>{
	private String color;
	private int num;
	
	public Card(String color, int num) {
		this.color = color;
		this.num = num;
	}

	public String getColor(){
		return color;
	}

	public int getNum(){
		return num;
	}

	public int compareTo(Card o) {
		return this.getNum()-o.getNum();
	}

}

