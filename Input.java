import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Input implements KeyListener
{
	int[] dir = {0, 0};
	int key;

	@Override
	public void keyTyped(KeyEvent e)
	{}

	@Override
	public void keyPressed(KeyEvent e)
	{
		dir[0]=0;
	 	dir[1]=0;
		key = e.getKeyCode();
		if(key == KeyEvent.VK_W) dir[1]-=1;
		else if(key == KeyEvent.VK_A) dir[0]-=1;
		else if(key == KeyEvent.VK_D) dir[0]+=1;
		else if(key == KeyEvent.VK_S) dir[1]+=1;
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == key) {
			dir[0]=0;
			dir[1]=0;
		}
	}
}
