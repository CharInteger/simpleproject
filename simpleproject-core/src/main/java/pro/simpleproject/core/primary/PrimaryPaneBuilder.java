package pro.simpleproject.core.primary;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class PrimaryPaneBuilder {

	private static BorderPane pane;

	public static BorderPane get() {
		return pane;
	}

	public static Pane build() {
		try {
			FXMLLoader f = new FXMLLoader(PrimaryPaneBuilder.class.getResource("primary.fxml"));
			pane = f.load();
			return pane;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
