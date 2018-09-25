package pro.simpleproject.core.intra;

import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import pro.simpleproject.core.helper.EditorBuilder;
import pro.simpleproject.core.intra.model.IntraContact;

public class IntraPaneBuilder {

	private static TreeView<Text> view;
	private static Pane connectPane;
	private static Map<IntraContact, Node> nodes = new HashMap<>();

	public static TreeView<Text> getContact() {
		if (view == null) {
			view = IntraContactPaneBuilder.build();
		}
		return view;
	}

	public static Pane getConnect() {
		if (connectPane == null) {
			try {
				FXMLLoader f = new FXMLLoader(IntraPaneBuilder.class.getResource("intra.fxml"));
				connectPane = f.load();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return connectPane;
	}

	public static Node getChat(IntraContact contact) {
		Node node = nodes.get(contact);
		if (node == null) {
			node = buildChat(contact.getAddress());
			nodes.put(contact, node);
		}
		return node;
	}

	private static Pane buildChat(String c) {
		try {
			FXMLLoader f = new FXMLLoader(IntraPaneBuilder.class.getResource("intrachat.fxml"));
			VBox vbox = f.load();
			IntraChatController intraChatController = f.getController();
			vbox.setUserData(c);
			EditorBuilder.build(intraChatController.editor);
			return vbox;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
