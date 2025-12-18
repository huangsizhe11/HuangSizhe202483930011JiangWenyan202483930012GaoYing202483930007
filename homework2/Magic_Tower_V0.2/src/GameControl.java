import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameControl {
	GameData gameData;
	Menu menu;
	GUI gui;

	GameControl(GameData gameData, Menu menu, GUI gui) {
		this.gameData = gameData;
		this.menu = menu;
		this.gui = gui;
	}

	void gameStart() {
		gui.f.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c=='a'||c=='s'||c=='d'||c=='w') {
					handleInput(c);
					gameData.printMap();
					gui.refreshGUI();
				}
			}
			public void keyPressed(KeyEvent e) {}
			public void keyReleased(KeyEvent e) {}
		});

		Scanner keyboardInput = new Scanner(System.in);
		while (true) {
			String input = keyboardInput.next();
			if (input.length() != 1 || (input.charAt(0) != 'a' && input.charAt(0) != 's' && input.charAt(0) != 'd'
					&& input.charAt(0) != 'w' && input.charAt(0) != '0')) {
				System.out.println("Wrong Input.");
				continue;
			}
			if (input.charAt(0) == '0')
				menu.enterMenu();
			else
				handleInput(input.charAt(0));

			gameData.printMap();
			gui.refreshGUI();
		}
	}

	void handleInput(char inC) {
		int tX = gameData.pX;
		int tY = gameData.pY;

		// 修正坐标初始化，避免冗余赋值
		if (inC == 'a') {
			tY = gameData.pY - 1;
		}
		if (inC == 's') {
			tX = gameData.pX + 1;
		}
		if (inC == 'd') {
			tY = gameData.pY + 1;
		}
		if (inC == 'w') {
			tX = gameData.pX - 1;
		}


		if (tX < 0 || tX >= gameData.H || tY < 0 || tY >= gameData.W) {
			System.out.println("Can't move out of the map!");
			return;
		}

		int targetCell = gameData.map[gameData.currentLevel][tX][tY];


		if (targetCell == 9) {
			gameData.swordNum++;
			System.out.println("Got a sword! Total swords: " + gameData.swordNum);
			moveHero(tX, tY);
		}

		else if (targetCell < 0) {
			if (gameData.swordNum >= 1) {

				gameData.swordNum--;
				System.out.println("Used a sword to defeat the monster! Swords left: " + gameData.swordNum);
				moveHero(tX, tY);
			} else {

				gameData.heroHealth += targetCell;
				System.out.println("Attacked by monster! Health " + targetCell + ". No sword to defend! Current health: " + gameData.heroHealth);
				if (gameData.heroHealth <= 0) {
					System.out.println("You have no sword to fight! You Lose!!");
					System.exit(0);
				} else {

					moveHero(tX, tY);
				}
			}
		}

		else if (gameData.map[gameData.currentLevel][tX][tY] == 11) {
			gameData.map[gameData.currentLevel][tX][tY] = 1;


			if (gameData.heroHealth < 150) {

				System.out.println("Health is less than 150, you were teleported back to the first floor after eating the fruit!");

				gameData.map[gameData.currentLevel][gameData.pX][gameData.pY] = 1;

				gameData.currentLevel = 0;

				for (int i = 0; i < gameData.H; i++)
					for (int j = 0; j < gameData.W; j++)
						if (gameData.map[gameData.currentLevel][i][j] == 6) {
							gameData.pX = i;
							gameData.pY = j;
						}
			} else if (gameData.heroHealth > 200) {
				System.out.println("Health exceeds 200, you were teleported directly to the next floor after eating the fruit!");

				if (gameData.currentLevel >= gameData.L - 1) {
					System.out.println("This is already the last floor, teleportation is not possible!");
				} else {
					gameData.map[gameData.currentLevel][gameData.pX][gameData.pY] = 1;
					gameData.currentLevel++;
					for (int i = 0; i < gameData.H; i++)
						for (int j = 0; j < gameData.W; j++)
							if (gameData.map[gameData.currentLevel][i][j] == 6) {
								gameData.pX = i;
								gameData.pY = j;
							}
				}
			} else {

				System.out.println("Health is between 150-200, eating the fruit has no effect!");

				moveHero(tX, tY);
			}
		}
		else if (gameData.map[gameData.currentLevel][tX][tY] == 2) {
			gameData.keyNum++;
			moveHero(tX, tY);
		}
		else if (gameData.map[gameData.currentLevel][tX][tY] == 3 && gameData.keyNum > 0) {
			gameData.keyNum--;
			moveHero(tX, tY);
		}

		else if (gameData.map[gameData.currentLevel][tX][tY] == 4) {

			if (gameData.currentLevel == 5) {

				System.out.println("\033[31m===== Game Over! You Win！ =====\033[0m");
				System.exit(0); // 退出程序，避免报错
			} else {

				gameData.map[gameData.currentLevel][gameData.pX][gameData.pY] = 1;
				gameData.currentLevel++;
				for (int i = 0; i < gameData.H; i++)
					for (int j = 0; j < gameData.W; j++)
						if (gameData.map[gameData.currentLevel][i][j] == 6) {
							gameData.pX = i;
							gameData.pY = j;
						}
			}
		}
		else if (targetCell == 5 && gameData.currentLevel == 5) {
			System.out.println("\033[31mYou Win!! Game Over!\033[0m");
			System.exit(0);
		}
		else if (gameData.map[gameData.currentLevel][tX][tY] > 10) {
			gameData.heroHealth += gameData.map[gameData.currentLevel][tX][tY];
			moveHero(tX, tY);
		}
		else if (gameData.map[gameData.currentLevel][tX][tY] == 1) {
			moveHero(tX, tY);
		}


	}

	void moveHero(int tX, int tY) {
		gameData.map[gameData.currentLevel][gameData.pX][gameData.pY] = 1;
		gameData.map[gameData.currentLevel][tX][tY] = 6;
		gameData.pX = tX;
		gameData.pY = tY;
	}
}