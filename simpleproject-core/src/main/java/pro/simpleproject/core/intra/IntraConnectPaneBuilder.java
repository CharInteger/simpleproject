package pro.simpleproject.core.intra;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;

public class IntraConnectPaneBuilder {

	private static Pane pane;

	private static Pane build() {
		try {
			FlowPane pane = (FlowPane) FXMLLoader.load(IntraConnectPaneBuilder.class.getResource("intra.fxml"));
			return pane;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Pane get() {
		if (pane == null) {
			pane = build();
		}
		return pane;
	}

}
