package pro.simpleproject.core.primary;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class PrimaryPaneBuilder {

	private static BorderPane pane;

	public static BorderPane getPane() {
		return pane;
	}

	public static Pane build() {
		try {
			pane = (BorderPane) FXMLLoader.load(PrimaryPaneBuilder.class.getResource("primary.fxml"));
			AnchorPane root = new AnchorPane(pane);
			AnchorPane.setLeftAnchor(pane, 3.0);
			AnchorPane.setRightAnchor(pane, 3.0);
			AnchorPane.setTopAnchor(pane, 3.0);
			AnchorPane.setBottomAnchor(pane, 3.0);
			root.getStyleClass().add("base");
			return root;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
