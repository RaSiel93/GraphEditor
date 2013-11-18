package editorGraph.shell;

public enum ButtonEnum {
	VERTEX("�������", "VERTEX_MODE"), EDGE("����", "EDGE_MODE"), EDIT(
			"���/�����", "EDIT_MODE"), OPEN("�������", "OPEN"), SAVE(
			"���������", "SAVE");

	private String label;
	private String method;

	private ButtonEnum(String label, String method) {
		this.label = label;
		this.method = method;
	}

	public String getLabel() {
		return label;
	}

	public String getMethod() {
		return method;
	}
}
