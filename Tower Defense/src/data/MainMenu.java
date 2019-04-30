package data;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;

public class MainMenu {
	
	private Texture background;
	
	public MainMenu() {
		background = quickLoad("mainMenu");
	}
	
	public void update() {
		drawQuadTex(background, 0, 0, 2048, 1024);
	}

}
