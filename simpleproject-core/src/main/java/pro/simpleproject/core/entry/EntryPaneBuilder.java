package pro.simpleproject.core.entry;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class EntryPaneBuilder {

	private static Pane pane;

	public static Pane get() {
		if (pane == null) {
			pane = build();
		}
		return pane;
	}

	private static Pane build() {
		try {
			FlowPane pane = (FlowPane) FXMLLoader.load(EntryPaneBuilder.class.getResource("entry.fxml"));
			return pane;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
