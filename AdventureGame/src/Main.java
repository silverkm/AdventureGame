
public class Main {
	public static GUI gui;
	public static void main (String args[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); 
            }

        });
		
	}
	private static void createAndShowGUI() {
		// TODO Auto-generated method stub
		gui = new GUI();
	}
}
