package redball;
import javax.swing.JFrame;
public class Redball {
	

	public static void main(String[] args){
		JFrame obj=new JFrame();
		Gameplay gameplay=new Gameplay();
		obj.setBounds(10,10,700,600);
		obj.setResizable(false);
		obj.setTitle("REDBALL");
		obj.setVisible(true);
		obj.add(gameplay);
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}

