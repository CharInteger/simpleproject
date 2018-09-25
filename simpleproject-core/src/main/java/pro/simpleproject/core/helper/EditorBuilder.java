package pro.simpleproject.core.helper;

import java.io.File;
import java.util.List;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.Clipboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;

public class EditorBuilder {

	public static void build(HTMLEditor h) {
		Set<Node> s = h.lookupAll(".tool-bar");
		for (Node n : s) {
			n.setVisible(false);
			n.setManaged(false);
		}
		h.setHtmlText(ScriptHelper.FUNC);
		WebView v = (WebView) h.lookup(".web-view");
		v.setContextMenuEnabled(false);
		h.setOnKeyReleased(e -> {
			if (e.getCode().equals(KeyCode.V) && e.isControlDown()) {
				Clipboard cb = Clipboard.getSystemClipboard();
				List<File> fs = cb.getFiles();
				if (fs != null && fs.size() > 0) {
					for (File f : fs) {
						putWebView(f, v);
					}
				}
			}
		});
		final ContextMenu contextMenu = new ContextMenu();
		MenuItem menuItem0 = new MenuItem();
		menuItem0.setText("Cut");
		MenuItem menuItem1 = new MenuItem();
		menuItem1.setText("Copy");
		MenuItem menuItem2 = new MenuItem();
		menuItem2.setText("Paste");
		contextMenu.getItems().addAll(menuItem0, menuItem1, menuItem2);
		v.setOnMouseClicked(e -> {
			if (contextMenu != null) {
				contextMenu.hide();
			}
			if (e.getButton() == MouseButton.SECONDARY) {
				contextMenu.show(v, e.getScreenX(), e.getScreenY());
			}
		});
	}

	public static void putWebView(File f, WebView v) {
		String path = f.getPath().replace("\\", "/");
		v.getEngine().executeScript(ScriptHelper.callFunc(path));
	}

}
