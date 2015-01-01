package texteditor;
import javax.swing.JFrame;

public class editor {
public static void main(String agrs[]){
	guieditor obj = new guieditor();
	obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	obj.setSize(800,800);
	obj.setVisible(true);
}
}
