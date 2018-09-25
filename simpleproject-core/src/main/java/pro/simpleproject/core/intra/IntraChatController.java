package pro.simpleproject.core.intra;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import pro.simpleproject.core.helper.EditorBuilder;

public class IntraChatController {

	public VBox pane_base;
	public SplitPane pane_split;
	public StackPane pane_stack;
	public ScrollPane pane_scroll;
	public VBox pane_content0;
	public FlowPane pane_flow;
	public Button pane_flow_button0;
	public Button pane_flow_button1;
	public HTMLEditor editor;

	public void showpaneflow(MouseEvent e) {
		pane_flow.setPrefHeight(10.0);
		pane_flow_button0.setVisible(true);
		pane_flow_button1.setVisible(true);
		pane_scroll.setPrefHeight(pane_scroll.getHeight() - 8.0);
	}

	public void hidepaneflow(MouseEvent e) {
		pane_flow.setPrefHeight(2.0);
		pane_flow_button0.setVisible(false);
		pane_flow_button1.setVisible(false);
		pane_scroll.setPrefHeight(pane_scroll.getHeight() + 8.0);
	}

	public void paneflowbutton0() {
		int i = pane_stack.getChildren().size();
		if (i > 0) {
			pane_stack.getChildren().get(i - 1).setVisible(false);
		}
		VBox v = new VBox();
		pane_stack.getChildren().add(v);
	}

	public void paneflowbutton1() {
		int i = pane_stack.getChildren().size();
		if (i > 1) {
			pane_stack.getChildren().remove(i - 1);
			pane_stack.getChildren().get(i - 2).setVisible(true);
		}
	}

	public void paneanchorbutton0(ActionEvent e) {
		FileChooser fc = new FileChooser();
		fc.setTitle("OPEN");
		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));
		File f = fc.showOpenDialog(pane_base.getScene().getWindow());
		if (f != null) {
			WebView v = (WebView) editor.lookup(".web-view");
			EditorBuilder.putWebView(f, v);
		}
	}

	public void paneanchorbutton1(ActionEvent e) {
		String t = editor.getHtmlText();
		String contact = (String) pane_base.getUserData();
		if (contact == null) {
			return;
		}
		Pane p = IntraEchoBuilder.run(t, contact);
		if (p != null) {
			pane_content0.getChildren().add(p);
		}
	}

}
