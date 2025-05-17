import java.awt.Graphics2D;
import java.awt.Color;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity
{
	GamePanel gp;
	Input in;

	Player(GamePanel gp, Input in)
	{
		this.gp = gp;
		this.in = in;

		x = 100;
		y = 100;
		speed = 4;

		getPlayerImage();
	}

	void getPlayerImage() {
		try {
			down = ImageIO.read(getClass().getResourceAsStream("/res/plyr/plyr.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	void update()
	{
		x += in.dir[0]*speed;
		y += in.dir[1]*speed;
	}
	void draw(Graphics2D g)
	{
		BufferedImage img = down;
		g.drawImage(img, x, y, gp.tileSize, gp.tileSize, null);
	}
}
