package RamenShop;

import java.util.Scanner;

public class RamenShop {

	private String customerName;
	private int totalPrice = 0;
	private Scanner sc;
	private boolean found;

	private int nokori = 5;

	public void showWelcome(Scanner sc, boolean found) {
		this.sc = sc;
		this.found = found;

		System.out.println("おはようございます\n"
				+ "客の名前:人数を知らせてください");

		System.out.println("座席数は" + nokori + "席です");

		String name = sc.next();
		int index = sc.nextInt();
		reserveSeats(name, index);

	}

	public int checkout(int payment, int people) {

		if (payment - totalPrice < 0) {
			System.out.println("お支払いは不可能です");
			System.out.println("不足している金額は" + Math.abs(payment - totalPrice) + "円です");
			nokori += people;
			return -1;
		}
		return payment - totalPrice;
	}

	public void reserveSeats(String name, int people) {
		customerName = name;

		if (people > nokori) {
			System.out.println("座席を確保出来ませんでした");
			return;
		}

		nokori -= people;

		if (nokori < 0) {
			System.out.println("本日の営業は、終了しました");
			found = false;
			return;
		}

		System.out.println(name + "様、" + people + "名の座席を確保しました");
		showMenu();

		System.out.println("メニュー番号と個数を入力してください");

		for (int i = 0; i < people; i++) {
			orderRamen(sc.nextInt(), sc.nextInt());
		}
		showTotal();

		System.out.println("お支払い金額を入力してください");

		//金がない場合は、座席を減らさないようにする
		int pay = checkout(sc.nextInt(), people);

		if (pay == -1) {
			return;
		} else {
			System.out.println("おつりは" + pay + "円でした");
			totalPrice = 0;
		}

	}

	public void showMenu() {
		String textBlock = """
				1,しょうゆラーメン　750円
				2,みそラーメン　850円
				3,塩ラーメン 800円
				""";

		System.out.println(textBlock);
	}

	public void orderRamen(int menuNumber, int quantity) {

		int price = 0;

		switch (menuNumber) {
		case 1 -> price += 750;
		case 2 -> price += 850;
		case 3 -> price += 800;

		}
		System.out.println("小計" + price * quantity + "円");
		totalPrice += price * quantity;
	}

	public void showTotal() {
		System.out.println("合計金額:" + totalPrice + "円");
	}

}
