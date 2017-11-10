package pro.simpleproject.core.intra;

import java.net.InetAddress;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;

public class IntraConnectController {

	public Pane pane_base;
	public ComboBox<String> combobox0;
	public Button button0;

	public void commit() {
		String v = combobox0.getValue();
		if (v != null && v.length() > 0) {
			try {
				String[] vs = v.split(":");
				InetAddress i = InetAddress.getByName(vs[0]);
				if (i != null) {
					String s = i.getHostAddress();
					if (s != null) {
						IntraContactPaneBuilder.add(s, Integer.parseInt(vs[1]));
						return;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		combobox0.setValue("");
	}

}
