import java.awt.*;
import javax.swing.*;
public class GUI {
	GameData gameData;
	JFrame f;
	JLabel[][] b;
	GUI(GameData gameData) {
		this.gameData = gameData;
		f = new JFrame("Magic Tower");
		b = new JLabel[gameData.H][gameData.W];
		for (int i = 0; i < gameData.H; i++) {
			for (int j = 0; j < gameData.W; j++) {
				b[i][j]=new JLabel();
				b[i][j].setBounds(j*100, i*100, 100, 100);
				f.add(b[i][j]);
			}
		}
		f.setSize(gameData.H*100+10, gameData.W*100+40);
		f.setLayout(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		refreshGUI();
	}
	public void refreshGUI()
	{
		for (int i = 0; i < gameData.H; i++) {
			for (int j = 0; j < gameData.W; j++) {
				Image scaledImage = chooseImage(gameData.map[gameData.currentLevel][i][j]);
				b[i][j].setIcon(new ImageIcon(scaledImage));
			}
		}
	}
	private static Image chooseImage(int index){
		ImageIcon[] icons = new ImageIcon[11];
		Image scaledImage;
		icons[0]= new ImageIcon("C:\\Users\\Lenovo\\Desktop\\软件开发工具\\(3). Magic_Tower_V0.3_EventDrivenProgramming\\Magic_Tower_V0.2\\Wall.jpg");
		icons[1]= new ImageIcon("C:\\Users\\Lenovo\\Desktop\\软件开发工具\\(3). Magic_Tower_V0.3_EventDrivenProgramming\\Magic_Tower_V0.2\\Floor.jpg");
		icons[2]= new ImageIcon("C:\\Users\\Lenovo\\Desktop\\软件开发工具\\(3). Magic_Tower_V0.3_EventDrivenProgramming\\Magic_Tower_V0.2\\Key.jpg");
		icons[3]= new ImageIcon("C:\\Users\\Lenovo\\Desktop\\软件开发工具\\(3). Magic_Tower_V0.3_EventDrivenProgramming\\Magic_Tower_V0.2\\Door.jpg");
		icons[4]= new ImageIcon("C:\\Users\\Lenovo\\Desktop\\软件开发工具\\(3). Magic_Tower_V0.3_EventDrivenProgramming\\Magic_Tower_V0.2\\Stair.jpg");
		icons[5]= new ImageIcon("C:\\Users\\Lenovo\\Desktop\\软件开发工具\\(3). Magic_Tower_V0.3_EventDrivenProgramming\\Magic_Tower_V0.2\\Exit.jpg");
		icons[6]= new ImageIcon("C:\\Users\\Lenovo\\Desktop\\软件开发工具\\(3). Magic_Tower_V0.3_EventDrivenProgramming\\Magic_Tower_V0.2\\Hero.jpg");
		icons[7]= new ImageIcon("C:\\Users\\Lenovo\\Desktop\\软件开发工具\\(3). Magic_Tower_V0.3_EventDrivenProgramming\\Magic_Tower_V0.2\\Potion.jpg");
		icons[8]= new ImageIcon("C:\\Users\\Lenovo\\Desktop\\软件开发工具\\(3). Magic_Tower_V0.3_EventDrivenProgramming\\Magic_Tower_V0.2\\Monster.jpg");
		icons[9]= new ImageIcon("C:\\Users\\Lenovo\\Desktop\\软件开发工具\\(3). Magic_Tower_V0.3_EventDrivenProgramming\\Magic_Tower_V0.2\\Sword.jpg");
		icons[10]= new ImageIcon("C:\\Users\\Lenovo\\Desktop\\软件开发工具\\(3). Magic_Tower_V0.3_EventDrivenProgramming\\Magic_Tower_V0.2\\Fruit.jpg");

		if(index == 11) {

			scaledImage = icons[10].getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		} else if(index>9)
			scaledImage = icons[7].getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		else if(index<0)
			scaledImage = icons[8].getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		else
			scaledImage = icons[index].getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		return scaledImage;
	}
}
