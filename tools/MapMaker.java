import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.imageio.ImageIO;

import java.io.FileWriter;
import java.io.IOException;

class MapMaker
{
	public static void main(String args[])
	{
		int map = 0;
		try{
			map = Integer.parseInt(args[0]);
		} catch(Exception e) {
			System.out.println("format: exec map-number");
			System.exit(1);
		}
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Map Maker");

		Panel panel = new Panel(map);

		panel.addMouseListener(panel);
		panel.addKeyListener(panel);
		window.add(panel);
		window.pack();

		window.setLocationRelativeTo(null);
		window.setVisible(true);
	}

}

class Panel extends JPanel implements MouseListener, KeyListener
{
	int x, y;
	int map;

	int tileSize = 16 * 3;
	int row = 16;
	int col = 12;
	int width = tileSize * row;
	int height = tileSize * col;
	String[] tileName = {"grass", "water", "tree"};
	BufferedImage[] tile = new BufferedImage[3];

	short currentTile = 0;

	short[] grid = new short[row*col];
	BufferedImage canvas = new BufferedImage(width, height + 2*tileSize, BufferedImage.TYPE_3BYTE_BGR);

	Panel(int m)
	{
		for(int i=0; i<tileName.length; i++) {
			try {
				tile[i] = ImageIO.read(MapMaker.class.getResourceAsStream("../res/tile/"+tileName[i]+".png"));
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		initTileMap();

		this.setPreferredSize(new Dimension(width, height + 2*tileSize));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		map = m;
	}

	void initTileMap()
	{
		Graphics2D g = (Graphics2D)canvas.createGraphics();
		for(int i=0; i<row*col; i++) {
			g.drawImage(tile[currentTile], (i%row)*tileSize, (i/row)*tileSize, tileSize, tileSize, null);
		}
		g.drawImage(tile[currentTile], tileSize, height + tileSize/2, tileSize, tileSize, null);
		g.dispose();
	}

	void setTile(int x, int y)
	{
		Graphics2D g = (Graphics2D)canvas.createGraphics();
		g.drawImage(tile[currentTile], x, y, tileSize, tileSize, null);
		g.dispose();
		repaint();
	}

	public void paintComponent(Graphics g3D)
	{
		super.paintComponent(g3D);
		Graphics2D g = (Graphics2D)g3D;
		g.drawImage(canvas, 0, 0, width, height + 2*tileSize, null);
		g.dispose();
	}

	public void mousePressed(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
		x -= x%tileSize;
		y -= y%tileSize;
		if(x<width && y<height) {
			setTile(x, y);
			grid[(x/tileSize) + (y/tileSize)*row] = currentTile;
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}

	public void keyPressed(KeyEvent e)
	{
		short num = (short)(e.getKeyCode() - 49);
		if(num>=0 && num <tileName.length) {
			currentTile = num;
			setTile(tileSize, height + tileSize/2);
		}
		else if(e.getKeyCode() == KeyEvent.VK_ENTER)
			write();
	}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}

	void write()
	{
		try{
			FileWriter f = new FileWriter("map/"+map);
			for(int j=0; j<col; j++) {
				for(int i=0; i<row; i++) {
					f.write(grid[j*row+i]+" ");
				}
				f.write("\n");
			}
			f.close();
			System.out.println("Saved to /map/"+map);
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
