package input;

import java.util.ArrayList;

import MainMenu.GameOverButton;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

public class InputUtility {
	public static double mouseX, mouseY;
	public static boolean mouseOnScreen = true;
	private static boolean isLeftDown = false;
	private static boolean isLeftClickedLastTick = false;
	private static ArrayList<KeyCode> keyPressed = new ArrayList<>();

	public static boolean getKeyPressed(KeyCode keycode) {
		return keyPressed.contains(keycode);
	}

	public static void setKeyPressed(KeyCode keycode, boolean pressed) {
		if (pressed) {
			if (!keyPressed.contains(keycode)) {
				keyPressed.add(keycode);
			}
		} else {
			keyPressed.remove(keycode);
		}
//		System.out.println(keyPressed);
	}

	public static void mouseLeftDown() {
		isLeftDown = true;
		isLeftClickedLastTick = true;
	}

	public static void mouseLeftRelease() {
		isLeftDown = false;
	}

	public static boolean isLeftClickTriggered() {
		return isLeftClickedLastTick;
	}

	public static void updateInputState() {
		isLeftClickedLastTick = false;
	}

	public static ArrayList<KeyCode> getKeyPressed() {
		return keyPressed;
	}

	public boolean isIn(GameOverButton b, MouseEvent e) {
		return b.getBounds().contains(e.getX(), e.getY());
	}
}
