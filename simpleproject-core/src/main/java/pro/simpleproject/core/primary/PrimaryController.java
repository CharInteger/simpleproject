package pro.simpleproject.core.primary;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pro.simpleproject.core.entry.EntryPaneBuilder;
import pro.simpleproject.core.extra.ExtraContactPaneBuilder;
import pro.simpleproject.core.intra.IntraContactPaneBuilder;

public class PrimaryController {

	public BorderPane pane_base;
	public Pane pane_top;
	public final double[] d = new double[2];
	public Pane pane_system;

	public void initial(MouseEvent e) {
		d[0] = e.getScreenX() - pane_top.getScene().getWindow().getX();
		d[1] = e.getScreenY() - pane_top.getScene().getWindow().getY();
	}

	public void reposition(MouseEvent e) {
		pane_top.getScene().getWindow().setX(e.getScreenX() - d[0]);
		pane_top.getScene().getWindow().setY(e.getScreenY() - d[1]);
		Stage stage = (Stage) pane_top.getScene().getWindow();
		if (stage.isMaximized()) {
			stage.setMaximized(false);
		}
	}

	public void close(ActionEvent e) {
		PrimaryService.close();
	}

	public void maximize(ActionEvent e) {
		Stage stage = (Stage) pane_system.getScene().getWindow();
		if (stage.isMaximized()) {
			stage.setMaximized(false);
		} else {
			stage.setMaximized(true);
		}
	}

	public void minimize(ActionEvent e) {
		((Stage) pane_system.getScene().getWindow()).setIconified(true);
	}

	public void minimize2(ActionEvent e) {
		pane_system.getScene().getWindow().hide();
	}

	public void open1() {
		pane_base.setLeft(IntraContactPaneBuilder.get());
		pane_base.setCenter(null);
		pane_base.setRight(null);
	}

	public void open2() {
		if (PrimaryService.entry()) {
			pane_base.setCenter(EntryPaneBuilder.get());
			pane_base.setLeft(null);
		} else {
			pane_base.setLeft(ExtraContactPaneBuilder.get());
			pane_base.setCenter(null);
		}
	}

}
