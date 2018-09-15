package designer;

public class TestDesignerMode {
	
	public TestDesignerMode() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		System.out.println("Welcome to the Interactive Designer Mode Tester");
		LevelDesigner designer = new LevelDesigner();
		LevelDesignerController controller = new LevelDesignerController(designer);
		controller.run();
	}
	
}
