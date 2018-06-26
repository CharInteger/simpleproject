package pro.simpleproject.core.start;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pro.simpleproject.core.primary.PrimaryPaneBuilder;
import pro.simpleproject.core.primary.PrimaryService;
import pro.simpleproject.core.tray.TrayBuilder;

public class Start extends Application {

	private static Stage stage;

	public static Stage getStage() {
		return stage;
	}

	@Override
	public void start(Stage arg) {
		try {
			stage = arg;
			Scene scene = new Scene(PrimaryPaneBuilder.build(), 800, 600);
			scene.getStylesheets().add(getClass().getResource("start.css").toExternalForm());
			stage.initStyle(StageStyle.UNIFIED);
			stage.setScene(scene);
			stage.setTitle("START");
			stage.show();
			stage.setOnCloseRequest(e -> PrimaryService.close());
			TrayBuilder.build();
			Platform.setImplicitExit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
