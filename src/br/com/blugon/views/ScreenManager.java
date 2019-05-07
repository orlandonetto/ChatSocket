package br.com.blugon.views;

import javax.swing.JPanel;

import br.com.blugon.views.screens.ChatScreen;
import br.com.blugon.views.screens.IndexScreen;
import br.com.blugon.views.screens.ServerScreen;

public class ScreenManager {

	private JPanel[] screens;
	private static Integer currentScreen;
	private static ScreenManager instance;

	private ScreenManager() {
		initComponents();
		fillComponents();
	}

	public static ScreenManager getInstance() {
		if (instance == null)
			instance = new ScreenManager();
		return instance;
	}

	private void initComponents() {
		currentScreen = 0;

		screens = new JPanel[3];

		screens[0] = new IndexScreen();
		screens[1] = new ServerScreen();
		screens[2] = new ChatScreen();
		
	}

	private void fillComponents() {

	}

	public JPanel getScreen(Integer currentScreen) {
		return null;
	}

	public JPanel getCurrentScreen() {
		return screens[currentScreen];
	}

	public void setCurrent(Integer current) {
		ScreenManager.currentScreen = current;
		MainScreen.getInstance().updateComponents();
	}

}