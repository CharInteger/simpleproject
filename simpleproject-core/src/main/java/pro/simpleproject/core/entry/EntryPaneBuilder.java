package pro.simpleproject.core.entry;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class EntryPaneBuilder {

	private static Pane pane;

	public Pane build() throws IOException {
		AnchorPane pane = (AnchorPane) FXMLLoader.load(getClass().getResource("entry.fxml"));
		return pane;
	}

	public static Pane get() {
		if (pane == null) {
			try {
				pane = new EntryPaneBuilder().build();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pane;
	}

}
