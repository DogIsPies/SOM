import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.*;


public class Test_SOM extends JFrame {

	private MyComponent komponent;
	private SOM som;
	private Timer timer;

	public boolean changeShape = true;
	double x;
	double y;


	class MyComponent extends JComponent{

		@Override
		protected void paintComponent(Graphics g) {
			int w=getWidth();
			int h=getHeight();

			//kwadrat nr1
			g.drawRect(w/8, h/4, w/4, h/2);
			//kwadrat w kwadracie
			g.drawRect(w/8+w/16, h/4+h/8, w/8, h/4);
			//kwadrat nr2
			g.drawRect(w/2-(w/4)/2, h/4, w/4, h/2);
			//kwadrat nr3
			g.drawRect(w/2+(w/4)/2, h/4, w/4, h/2);
			//kółko w kwadracie
			g.drawOval((w/2+(w/4)/2)+w/16, h/4+h/8, w/8, h/4);






			int r = (w/16);
			Random rand=new Random();
			if(som.eta > 0.1){
				som.draw(g, w/2-(w/16), h/4+h/8, w/8, h/4);
				double a = ((rand.nextDouble() - 0.5) / 0.5);
				double b = ((rand.nextDouble() - 0.5) / 0.5);
				Vec2D wejsciaKwadrat = new Vec2D(a, b);
				som.ucz(wejsciaKwadrat);
				System.out.println(som.eta);


			}
			else{
				for (int i = 0; i < 100; i++) {
						som.draw(g, w/2-(w/16), h/4+h/8, w/8, w/8);
						double angle = rand.nextDouble() * 2 * Math.PI;
						x = ((r * Math.cos(angle))*0.017);
						y = ((r * Math.sin(angle))*0.017);

						System.out.println(som.eta);
					}
				Vec2D wejsciaKolo=new Vec2D(x,y);
				som.uczKolo(wejsciaKolo);


			}

			
			super.paintComponent(g);
		}
		
	}
	public Test_SOM(String string) {
		super(string);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension d=kit.getScreenSize();
		setBounds(d.width/4,d.height/4,d.width/2,d.height/2);
		JPanel paneldown = new JPanel();
		JButton start = new JButton("START");
		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				som=new SOM(10,10,0.3,0.998,0.998);
			}
		});
		int x = getWidth();
		paneldown.setLayout(new BoxLayout(paneldown, BoxLayout.Y_AXIS));
		paneldown.add(komponent=new MyComponent());
		paneldown.add(start);
		start.setAlignmentX(Component.CENTER_ALIGNMENT);


		add(paneldown);

		timer=new Timer(20,new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				komponent.repaint();
			}
		});
		timer.start();
		setVisible(true);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				new Test_SOM("Test SOM");
			}
		});
	}

}
