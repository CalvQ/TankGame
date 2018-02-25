import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;



public class GridBasedGameDriver {
    private Timer t;
	private JFrame frame = new JFrame("Tank Shooty");
	private static JPanel panel;
	private static List<Drawable> drawables= new ArrayList<>();
	private boolean L, R, U, D, PUp, PDown;
    private static ArrayList<Drawable> temp2 = new ArrayList<>();
    private static ArrayList<Drawable> temp3 = new ArrayList<>();

	public static List<Drawable> getList(){
	    return temp2;
    }
    public static List<Drawable> getOtherList(){
	    return temp3;
    }
    public static List<Drawable> getDrawables(){
	    return drawables;
    }
    public static JPanel getPanel(){
	    return panel;
    }

	public static void main(String[] args) {
		new GridBasedGameDriver().start();
	}

	private void start() {
		setUpGame();
		setBooleans();

		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				drawGame(g);
			}
			
		};
		t = new Timer(10, e -> gameTick());
		panel.setPreferredSize(new Dimension(800,800));//1600, 900
        panel.setBackground(new Color(0,200, 200));

        frame.add(panel);
		frame.pack();

		genTerrain();
		
		setPanel();
		panel.requestFocusInWindow();
		//panel.addKeyListener(l);
		
		setUpObjects();
		t.start();
	}

	private void setBooleans(){//L, R, U, D, PUp, PDown
	    L = false;
	    R = false;
	    U = false;
	    D = false;
	    PUp = false;
	    PDown = false;
    }

	private void gameTick(){
	    ArrayList<Drawable> temp = new ArrayList<>();
	    temp2 = new ArrayList<>();



        for(Drawable dr: drawables){
            if(dr instanceof Bullet){
                if(((Bullet) dr).inCollision()){
                    temp.add(dr);
                    temp.add(((Bullet) dr).genExpl());
                }else {
                    ((Bullet) dr).tick();
                }
            }
            if(dr instanceof Explosion){
                ((Explosion) dr).tick();
                if (((Explosion) dr).remove()){
                    temp2.add(dr);
                }
            }
            if(dr instanceof Tank){
                if(((Tank) dr).getTurn()){
                    if(L){
                        ((Tank) dr).moveLeft();
                    }
                    if(U){
                        if(((Tank) dr).getRed()){
                            ((Tank) dr).angle(-0.01);
                        }else{
                            ((Tank) dr).angle(0.01);
                        }
                    }
                    if(R){
                        ((Tank) dr).moveRight();
                    }
                    if(D){
                        if(((Tank) dr).getRed()){
                            ((Tank) dr).angle(0.01);
                        }else{
                            ((Tank) dr).angle(-0.01);
                        }
                    }
                    if(PUp){
                        ((Tank) dr).power(1);
                    }
                    if(PDown){
                        ((Tank) dr).power(-1);
                    }
                }
            }
        }
        for(Drawable dr2:temp){
            if(dr2 instanceof Explosion){
                drawables.add(dr2);
                ((Explosion) dr2).remExpl();
            }
            if(dr2 instanceof Bullet){
                drawables.remove(dr2);
            }
        }

        for(Drawable dr3:temp2){
            if(dr3 instanceof Bullet){
                drawables.add(dr3);
            }
            if(dr3 instanceof Explosion){
                drawables.remove(dr3);
            }
        }
        while(temp3.size()>0){
            drawables.add(temp3.remove(0));
        }
        panel.repaint();
    }

	private void setPanel(){
//	    panel.getInputMap().put(KeyStroke.getKeyStroke("V"), "v");
//	    panel.getActionMap().put("v", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ((Terrain)drawables.get(400)).remove(10);
//                ((Terrain)drawables.get(401)).remove(10);
//                ((Terrain)drawables.get(402)).remove(10);
//                ((Terrain)drawables.get(403)).remove(10);
//                ((Terrain)drawables.get(404)).remove(10);
//                ((Terrain)drawables.get(405)).remove(10);
//                ((Terrain)drawables.get(406)).remove(10);
//                ((Terrain)drawables.get(407)).remove(10);
//                ((Terrain)drawables.get(408)).remove(10);
//            }
//        });
//        panel.getInputMap().put(KeyStroke.getKeyStroke("B"), "b");
//        panel.getActionMap().put("b", new AbstractAction() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                ((Terrain)drawables.get(400)).remove(-10);
//                ((Terrain)drawables.get(401)).remove(-10);
//                ((Terrain)drawables.get(402)).remove(-10);
//                ((Terrain)drawables.get(403)).remove(-10);
//                ((Terrain)drawables.get(404)).remove(-10);
//                ((Terrain)drawables.get(405)).remove(-10);
//                ((Terrain)drawables.get(406)).remove(-10);
//                ((Terrain)drawables.get(407)).remove(-10);
//                ((Terrain)drawables.get(408)).remove(-10);
//
//            }
//        });
        panel.getInputMap().put(KeyStroke.getKeyStroke("A"),"a");
        panel.getActionMap().put("a",new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                for(Drawable dr: drawables){
                    if(dr instanceof Tank){
                        if(((Tank) dr).getTurn()){
                            ((Tank) dr).prev();
                        }
                    }
                }
                panel.repaint();
            }
        });
        panel.getInputMap().put(KeyStroke.getKeyStroke("D"),"d");
        panel.getActionMap().put("d",new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                for(Drawable dr: drawables){
                    if(dr instanceof Tank){
                        if(((Tank) dr).getTurn()){
                            ((Tank) dr).next();
                        }
                    }
                }
                panel.repaint();
            }
        });
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(!dContains()) {
                    if (e.getExtendedKeyCode() == 32) {
                        setBooleans();
//                        for(Drawable dr:drawables){//todo good but has a concurrent error
//                            if (dr instanceof Tank){
//                                if(((Tank) dr).getTurn()){
//                                    drawables.add(((Tank) dr).fire());
//                                }
//                                ((Tank) dr).toggleTurn();
//                            }
//                        }
                        if (((Tank) drawables.get(800)).getTurn()) {
                            drawables.add(((Tank) drawables.get(800)).fire());
                        } else {
                            drawables.add(((Tank) drawables.get(801)).fire());
                        }
                        ((Tank) drawables.get(800)).toggleTurn();
                        ((Tank) drawables.get(801)).toggleTurn();
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) { //L, R, U, D, PUp, PDown
                if(!dContains()) {
                    switch (e.getExtendedKeyCode()) {
                        case 87:
                            PUp = true;
                            break;
                        case 83:
                            PDown = true;
                            break;
                        case 37:
                            L = true;
                            break;
                        case 38:
                            U = true;
                            break;
                        case 39:
                            R = true;
                            break;
                        case 40:
                            D = true;
                            break;
                        default:
                            break;
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(!dContains()) {
                    switch (e.getExtendedKeyCode()) {
                        case 87:
                            PUp = false;
                            break;
                        case 83:
                            PDown = false;
                            break;
                        case 37:
                            L = false;
                            break;
                        case 38:
                            U = false;
                            break;
                        case 39:
                            R = false;
                            break;
                        case 40:
                            D = false;
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    private boolean dContains(){
	    for(Drawable dr:drawables){
	        if(dr instanceof Bullet || dr instanceof Explosion){
	            return true;
            }
        }
        return false;
    }

	private void genTerrain(){
	    int last = 500;
	    for(int i=0; i<panel.getWidth(); i++){
//	        double y = (Math.exp(-0.5 * Math.pow((400 - Math.abs(400-i)),2.0)))/(Math.pow(Math.PI*2, 0.5));
//	        System.out.println(y);
//	        drawables.add(new Terrain(new Point(i, (int) (y*100) + 500)));
            if(i<panel.getWidth()/2){
                drawables.add(new Terrain(new Point(i, last)));
                last -= ((int) (Math.random()*1.3));
            }else{
                drawables.add(new Terrain(new Point(i, last)));
                last+= ((int) (Math.random()*1.3));
            }
        }
    }

	
	private void setUpObjects() {
		// TODO
        //index 800 and 801 are tanks
        //index 801+ are temp objects(proj + explosion)
        drawables.add(new Tank(100, 0, false));
        drawables.add(new Tank(700, Math.PI, true));
        initTurn();
	}

	private void initTurn(){
        ((Tank) (drawables.get((int) (Math.random()*2)+800))).toggleTurn();
    }

	private void drawGame(Graphics g) {
		for(Drawable dr:drawables) {
			dr.draw(g);
		}
	}
	private void setUpGame() {//TODO

	}

	private void gameOver() {
		// TODO Auto-generated method stub

	}

}
