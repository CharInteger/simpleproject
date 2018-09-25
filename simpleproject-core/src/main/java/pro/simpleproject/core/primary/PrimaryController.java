package pro.simpleproject.core.primary;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import pro.simpleproject.core.entry.EntryPaneBuilder;
import pro.simpleproject.core.intra.IntraPaneBuilder;

public class PrimaryController {

	public BorderPane pane_base;
	public Pane pane_top;
	public Button button_open1;
	public Button button_open2;

	public void open1() {
		pane_base.setLeft(IntraPaneBuilder.getContact());
		pane_base.setCenter(null);
		pane_base.setRight(null);
	}

	public void open2() {
		if (PrimaryService.entry()) {
			pane_base.setCenter(EntryPaneBuilder.get());
			pane_base.setLeft(null);
		} else {
			pane_base.setLeft(null);
			pane_base.setCenter(null);
		}
	}

}
