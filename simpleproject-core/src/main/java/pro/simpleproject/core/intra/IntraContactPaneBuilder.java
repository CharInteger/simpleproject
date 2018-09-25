package pro.simpleproject.core.intra;

import javafx.collections.ObservableList;
import javafx.event.EventTarget;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;
import pro.simpleproject.core.intra.model.IntraContact;
import pro.simpleproject.core.intra.model.IntraModel;
import pro.simpleproject.core.intra.server.ServerExecutor;
import pro.simpleproject.core.primary.PrimaryPaneBuilder;

public class IntraContactPaneBuilder {

	private static TreeItem<Text> root;
	private static ContextMenu contextMenu;

	public static TreeView<Text> build() {
		root = new TreeItem<Text>();
		TreeView<Text> tv = new TreeView<Text>(root);
		tv.setEditable(false);
		tv.autosize();
		tv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tv.setShowRoot(false);
		tv.setOnMouseClicked(e -> {
			TreeItem<Text> selectedItem = tv.getSelectionModel().getSelectedItem();
			int n = tv.getTreeItemLevel(selectedItem);
			EventTarget t = e.getTarget();
			if (n == 1 && t instanceof Text) {
				if (MouseButton.PRIMARY.equals(e.getButton())) {
					chat(((Text) t).getText());
				} else if (MouseButton.SECONDARY.equals(e.getButton())) {
					tv.setContextMenu(userContextMenu(((Text) t).getText()));
				}
			} else if (MouseButton.SECONDARY.equals(e.getButton())) {
				tv.setContextMenu(viewContextMenu());
			}
		});
		return tv;
	}

	public static void add(String addr, int port) {
		if (!IntraModel.hasContact(addr)) {
			addItem(addr);
		}
		IntraModel.setContact(addr, port);
		chat(addr);
	}

	private static void addItem(String addr) {
		TreeItem<Text> item = new TreeItem<Text>(new Text(addr));
		root.getChildren().add(item);
	}

	private static void removeItem(String addr) {
		IntraModel.removeContact(addr);
		ObservableList<TreeItem<Text>> list = root.getChildren();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getValue().getText().equals(addr)) {
				list.remove(i);
			}
		}
		PrimaryPaneBuilder.get().setCenter(null);
	}

	private static void chat(String addr) {
		IntraContact intraContact = IntraModel.getContact(addr);
		if (intraContact != null) {
			PrimaryPaneBuilder.get().setCenter(IntraPaneBuilder.getChat(intraContact));
		}
	}

	private static ContextMenu userContextMenu(String addr) {
		ContextMenu contextMenu = new ContextMenu();
		MenuItem menuItem0 = new MenuItem("chat");
		menuItem0.setOnAction(e -> {
			chat(addr);
		});
		contextMenu.getItems().add(menuItem0);
		MenuItem menuItem1 = new MenuItem("remove");
		menuItem1.setOnAction(e -> {
			removeItem(addr);
		});
		contextMenu.getItems().add(menuItem1);
		return contextMenu;
	}

	private static ContextMenu viewContextMenu() {
		if (contextMenu == null) {
			contextMenu = new ContextMenu();
			MenuItem menuItem0 = new MenuItem("server");
			menuItem0.setOnAction(e -> {
				server();
			});
			contextMenu.getItems().add(menuItem0);
			MenuItem menuItem1 = new MenuItem("contact");
			menuItem1.setOnAction(e -> {
				PrimaryPaneBuilder.get().setCenter(IntraPaneBuilder.getConnect());
			});
			contextMenu.getItems().add(menuItem1);
		}
		return contextMenu;
	}

	private static void server() {
		ServerExecutor.run(12345);
		contextMenu.getItems().get(0).setText("12345");
	}

}
