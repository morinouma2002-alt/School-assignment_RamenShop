package RamenShop;
import java.util.Scanner;

public class RamenShopApp {

	private RamenShop shop;
	private Scanner sc ;

	public RamenShopApp(RamenShop shop,Scanner sc) {
		this.shop = shop;
		this.sc=sc;

		print();

	}

	public void print() {
		boolean found = true;
		while (found) {
			shop.showWelcome(sc, found);
		}
	}
}
