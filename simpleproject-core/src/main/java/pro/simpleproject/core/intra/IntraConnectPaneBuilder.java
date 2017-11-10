package pro.simpleproject.core.intra;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class IntraConnectPaneBuilder {

	private static Pane pane;

	private Pane build() throws IOException {
		TilePane pane = (TilePane) FXMLLoader.load(getClass().getResource("intra.fxml"));
		return pane;
	}

	public static Pane get() {
		if (pane == null) {
			try {
				pane = new IntraConnectPaneBuilder().build();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return pane;
	}

}
