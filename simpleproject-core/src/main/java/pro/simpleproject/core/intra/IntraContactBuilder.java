package pro.simpleproject.core.intra;

import javafx.scene.control.SelectionMode;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.text.Text;

public class IntraContactBuilder {

	private static TreeView<Text> view;

	public static TreeView<Text> get() {
		if (view == null) {
			view = new IntraContactBuilder().build();
		}
		return view;
	}

	public TreeView<Text> build() {
		TreeView<Text> tv = new TreeView<Text>(getItem());
		tv.setEditable(false);
		tv.autosize();
		tv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tv.setShowRoot(false);
		return tv;
	}

	private TreeItem<Text> getItem() {
		TreeItem<Text> item = new TreeItem<Text>(new Text("192.168.1.1"));
		TreeItem<Text> root = new TreeItem<Text>();
		root.getChildren().add(item);
		return root;
	}

}
