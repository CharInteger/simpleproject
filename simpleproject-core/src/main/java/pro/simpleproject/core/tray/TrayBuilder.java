package pro.simpleproject.core.tray;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.File;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import pro.simpleproject.core.start.Start;

public class TrayBuilder {

	private static TrayIcon trayIcon;

	public static boolean build() {
		Toolkit.getDefaultToolkit();
		if (!SystemTray.isSupported()) {
			return false;
		}
		SystemTray tray = SystemTray.getSystemTray();
		try {
			File f = new File("");
			Image image = ImageIO.read(f);
			trayIcon = new TrayIcon(image);
			trayIcon.addActionListener(e -> {
				Platform.runLater(() -> {
					showStage();
				});
			});
			MenuItem openItem = new MenuItem("Show");
			openItem.addActionListener(event -> {
				Platform.runLater(() -> {
					showStage();
				});
			});
			MenuItem exitItem = new MenuItem("Exit");
			exitItem.addActionListener(e -> {
				Platform.exit();
				tray.remove(trayIcon);
			});
			PopupMenu popup = new PopupMenu();
			popup.add(openItem);
			popup.addSeparator();
			popup.add(exitItem);
			trayIcon.setPopupMenu(popup);
			tray.add(trayIcon);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private static void showStage() {
		if (Start.getStage() != null) {
			Start.getStage().show();
			Start.getStage().toFront();
		}
	}

	public static void close() {
		if (trayIcon != null) {
			SystemTray tray = SystemTray.getSystemTray();
			tray.remove(trayIcon);
		}
	}

}
