package pro.simpleproject.core.entry;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import pro.simpleproject.core.primary.PrimaryPaneBuilder;

public class EntryController {

	public Pane pane_base;
	public ComboBox<String> combobox0;
	public Button button0;

	public void commit() {
		String v = combobox0.getValue();
		if (v != null && v.length() > 0) {
			boolean e = EntryService.run(v);
			if (e) {
				PrimaryPaneBuilder.get().setLeft(null);
				PrimaryPaneBuilder.get().setCenter(null);
			}
		}
	}

}
