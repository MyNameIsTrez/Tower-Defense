package data;

import org.newdawn.slick.opengl.Texture;

import UI.UI;

import static helpers.Artist.*;

public class MainMenu {
	
	private Texture background;
	private UI menuUI;
	
	public MainMenu() {
		background = quickLoad("mainMenu");
		menuUI = new UI();
		menuUI.addButton("Play", "playButton", WIDTH / 2 - 128, (int) (HEIGHT * 0.45));
		menuUI.addButton("Editor", "editorButton", WIDTH / 2 - 128, (int) (HEIGHT * 0.55));
		menuUI.addButton("Quit", "quitButton", WIDTH / 2 - 128, (int) (HEIGHT * 0.65));
	}
	
	public void update() {
		drawQuadTex(background, 0, 0, 2048, 1024);
		menuUI.draw();
	}

}
