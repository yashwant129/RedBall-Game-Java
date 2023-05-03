package redball;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Gameplay extends JPanel implements KeyListener,ActionListener {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private boolean play=false;
private int score=0;
private Timer timer;
int totalBricks=21;
private int delay=8;
private int playerX=310;
private int ballposX=120;
private int ballposY=350;
private int ballxdir=-1;
private int ballydir=-2;
private MapGenarator map;

public Gameplay(){
	map=new MapGenarator(3,7);
	addKeyListener(this);
	setFocusable(true);
	setFocusTraversalKeysEnabled(false);
	timer=new Timer(delay,this);
	timer.start();

}
//Graphics
public void paint(Graphics g){
	//background
g.setColor(Color.black);
g.fillRect(1,1,692,592);
//Map
map.draw((Graphics2D)g);
//borders
g.setColor(Color.yellow);
g.fillRect(0, 0, 3, 592);
g.fillRect(0,0,692,3);
g.fillRect(691, 0, 3,592);
//scores
g.setColor(Color.white);
g.setFont(new Font("serif",Font.PLAIN,25));
g.drawString("Score:"+score, 50, 30);
//paddle
g.setColor(Color.green);
g.fillRect(playerX,550,100,8);
//ball
g.setColor(Color.yellow);
g.fillOval(ballposX,ballposY,20,20);

if(ballposY>570){
	play=false;
	ballxdir=0;
	ballydir=0;
	g.setColor(Color.red);
	g.setFont(new Font("serif",Font.PLAIN,25));
	g.drawString("GAME OVER!"+"Your Score:"+score, 200, 300);
	
	g.drawString("Press Enter to Restart!",230,350);
}
g.dispose();

}

@Override
	public void actionPerformed(ActionEvent arg0) {
		timer.start();
		if(play){
	    if(new Rectangle ( ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8))){
		ballydir=-ballydir;
	    }
	    
	A:    for(int i=0;i<map.map.length;i++){
	    	for(int j=0;j<map.map[0].length;j++){
	    		if(map.map[i][j]>0){
	    			int brickX=j*map.brickWidth+80;
	    			int brickY=i*map.brickHeight+50;
	    			int brickWidth=map.brickWidth;
	    			int brickHeight=map.brickHeight;
	    			
	    			Rectangle rect=new Rectangle(brickX,brickY,brickWidth,brickHeight);
	    			Rectangle ballRect=new Rectangle(ballposX,ballposY,20,20);
	    			Rectangle brickRect=rect;
	    			
	    			if(ballRect.intersects(brickRect)){
	    				map.setBrickValue(0,i,j);
	    				totalBricks--;
	    				score+=5;
	    				
	    				if(ballposX+19<=brickRect.x||ballposX+1>=brickRect.x+brickRect.width){
	    					ballxdir=-ballxdir;
	    				}else{
	    					ballydir=-ballydir;
	    				}
	    				break A;
	    			}
	    		}
	    	}
	    }
	    
	    
	    
	    
		ballposX+=ballxdir;
		ballposY+=ballydir;
		if(ballposX<0){
			ballxdir=-ballxdir;
		}
		if(ballposY<0){
			ballydir=-ballydir;
		}
		if(ballposX>670){
			ballxdir=-ballxdir;
		}
		repaint();
		}
}
		

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
		if(playerX>=600){
			playerX=600;
		}else{
			moveright();
		}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
		if(playerX<10){
			playerX=10;
		}else{
			moveleft();
		}
		
		}
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			if(!play){
			play=true;
			ballposX=120;
			ballposY=350;
			ballxdir=-1;
			ballydir=-2;
			playerX=310;
			score=0;
			totalBricks=21;
			map=new MapGenarator(3,7);
			repaint();
			}
			}
		
}
		

	private void moveright() {
		play=true;
		playerX+=20;
		
	}
	private void moveleft() {
		play=true;
		playerX-=20;
		
	}

	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
