package editorGraph.shell;

public enum ButtonEnum {
	VERTEX("Вершина", "VERTEX_MODE"), EDGE("Дуга", "EDGE_MODE"), EDIT(
			"Имя/Длина", "EDIT_MODE"), OPEN("Открыть", "OPEN"), SAVE(
			"Сохранить", "SAVE");

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
