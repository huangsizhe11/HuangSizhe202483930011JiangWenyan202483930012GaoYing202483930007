public class MagicTowerMain {
	static GameData gameData;
	static GameControl gameControl;
	static Menu menu;
	static GUI gui;
	public static void main(String[] args) {
		gameData= new GameData();
		gameData.readMapFromFile("C:\\Users\\Lenovo\\Desktop\\软件开发工具\\(3). Magic_Tower_V0.3_EventDrivenProgramming\\Magic_Tower_V0.2\\Map.in");
		gameData.printMap();
		gui=new GUI(gameData);
		menu=new Menu(gameData);
		menu.loadMenu("C:\\Users\\Lenovo\\Desktop\\软件开发工具\\(3). Magic_Tower_V0.3_EventDrivenProgramming\\Magic_Tower_V0.2\\Menu.XML");
		//menu.displayMenu(menu.rootElement);
		gameControl=new GameControl(gameData,menu,gui);
		gameControl.gameStart();
	}
}