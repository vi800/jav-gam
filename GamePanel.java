import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable
{
	final int tileSize = 16 * 3; //16*16 pixel tile scaled by 3 to be visible on the screen
	final int row = 16;
	final int col = 12;
	final int width = tileSize * row;
	final int height = tileSize * col;

	int[] pos = {100, 100};
	int speed = 2;

	Thread gameThread;
	Input in = new Input();
	Player player = new Player(this, in);
	TileManager tm = new TileManager(this);

	GamePanel()
	{
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.addKeyListener(in);
		this.setFocusable(true);
	}

	void startGameThread()
	{
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run()
	{
		long delta;
		int fps = 60;
		int rec = (1000/60);
		while(gameThread != null) {
			delta = System.currentTimeMillis();
			update();
			repaint();
			delta = System.currentTimeMillis() - delta;

			try {
				gameThread.sleep((rec-delta));
			} catch(Exception e) {}
		}
	}

	void update()
	{
		player.update();
	}

	public void paintComponent(Graphics g3D)
	{
		super.paintComponent(g3D);
		Graphics2D g = (Graphics2D)g3D;
		tm.draw(g);
		player.draw(g);
		g.dispose();
	}
}
