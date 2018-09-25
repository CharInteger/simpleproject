package pro.simpleproject.core.primary;

import javafx.application.Platform;
import pro.simpleproject.core.intra.model.IntraModel;
import pro.simpleproject.core.tray.TrayBuilder;

public class PrimaryService {

	public static boolean entry() {
		return true;
	}

	public static void close() {
		IntraModel.close();
		Platform.exit();
		TrayBuilder.close();
	}

}
