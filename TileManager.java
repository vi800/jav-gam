import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.awt.image.BufferedImage;

public class TileManager
{
	GamePanel gp;
	Tile[] tile;
	BufferedImage tileMap;

	TileManager(GamePanel gp)
	{
		this.gp = gp;
		tile = new Tile[3];
		getTileImage();
		tileMap = new BufferedImage(gp.width, gp.height, BufferedImage.TYPE_3BYTE_BGR);

		getTileMap(0);
	}

	void getTileImage()
	{
		try {
			tile[0] = new Tile();
			tile[1] = new Tile();
			tile[2] = new Tile();

			tile[0].img = ImageIO.read(getClass().getResourceAsStream("/res/tile/grass.png"));

			tile[1].img = ImageIO.read(getClass().getResourceAsStream("/res/tile/water.png"));
			tile[1].layer = 2;

			tile[2].img = ImageIO.read(getClass().getResourceAsStream("/res/tile/tree.png"));
			tile[2].layer = 1;

		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	void getTileMap(int n)
	{
		Scanner s;
		try {
			s = new Scanner(new File("map/"+n));
			for(int j=0; j<gp.col; j++) {
				for(int i=0; i<gp.row; i++) {
					int t = s.nextInt();
					Graphics2D g = (Graphics2D)tileMap.createGraphics();
					g.drawImage(tile[t].img, i*gp.tileSize, j*gp.tileSize, gp.tileSize, gp.tileSize, null);
					g.dispose();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	void draw(Graphics2D g)
	{
		g.drawImage(tileMap, 0, 0, gp.width, gp.height, null);
	}
}
