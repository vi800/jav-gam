import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.IOException;

public class TileManager
{
	GamePanel gp;
	Tile[] tile;

	TileManager(GamePanel gp)
	{
		this.gp = gp;
		tile = new Tile[2];
		getTileImage();
	}

	void getTileImage()
	{
		try {
			tile[0] = new Tile();
			tile[1] = new Tile();

			tile[0].img = ImageIO.read(getClass().getResourceAsStream("/res/tile/grass.png"));

			tile[1].img = ImageIO.read(getClass().getResourceAsStream("/res/tile/water.png"));
			tile[1].layer = 2;

		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	void draw(Graphics2D g)
	{
		g.drawImage(tile[0].img, 0, 0, gp.tileSize, gp.tileSize, null);
		g.drawImage(tile[1].img, 200, 100, gp.tileSize, gp.tileSize, null);
	}
}
