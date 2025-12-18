import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;
import java.lang.reflect.Field;

public class GameData implements Serializable {
	public int swordNum;
	int L, H, W, currentLevel;
	int pX, pY, heroHealth, keyNum;
	int[][][] map;
	void readMapFromFile(String filePath) {
		currentLevel = 0;
		heroHealth = 105;
		keyNum = 0;
		pX = 6;
		pY = 6;
		try {
			Scanner in = new Scanner(new File(filePath));
			L = in.nextInt();
			H = in.nextInt();
			W = in.nextInt();
			map = new int[L][H][W];
			for (int i = 0; i < L; i++)
				for (int j = 0; j < H; j++)
					for (int k = 0; k < W; k++)
						map[i][j][k] = in.nextInt();
		} catch (IOException e) {
			System.out.println("Error with files:" + e.toString());
		}
	}
	void printMap() {
		// 扩展字符数组，新增索引9对应剑（J）
		char C[] = { 'W', '_', 'K', 'D', 'S', 'E', 'H', 'P', 'M', 'J','G' };
		for (int j = 0; j < H; j++) {
			for (int k = 0; k < W; k++) {
				int mapVal = map[currentLevel][j][k];
				if (mapVal < 0) {
					System.out.print("M ");
				} else if (mapVal == 11) { // 水果用数字11表示
					System.out.print("G ");
				}else if (mapVal > 9) { // 原10改为9（剑的索引是9）
					System.out.print("P ");
				} else {
					// 安全校验：避免索引超出字符数组长度
					int idx = Math.max(0, Math.min(mapVal, C.length - 1));
					System.out.print(C[idx] + " ");
				}
			}
			System.out.print("\n\n");
		}
		System.out.print("Health:" + heroHealth + "  Keys:" + keyNum + "  Swords:" + swordNum + "  Menu:0\n");}

	void copyFields(Object source) {
		try {
			Class<?> clazz = this.getClass();
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				Object value = field.get(source);
				field.set(this, value);
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
