package pro.simpleproject.core.entry;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class EntryPaneBuilder {

	private static Pane pane;

	public static Pane get() {
		if (pane == null) {
			build();
		}
		return pane;
	}

	private static void build() {
		try {
			FXMLLoader f = new FXMLLoader(EntryPaneBuilder.class.getResource("entry.fxml"));
			pane = (FlowPane) f.load();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
