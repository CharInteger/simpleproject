package pro.simpleproject.core.helper;

public class ScriptHelper {

	public static String FUNC = "<script type=\"text/javascript\">function insert(html) {var range, node;if (window.getSelection && window.getSelection().getRangeAt) {range = window.getSelection().getRangeAt(0);node = range.createContextualFragment(html);range.insertNode(node);} else if (document.selection && document.selection.createRange) {document.selection.createRange().pasteHTML(html);}}</script>";

	public static String callFunc(String path) {
		return "insert('<img src=\"file:/" + path + "\" height=\"100px\" width=\"100px\"></img>')";
	}

}
