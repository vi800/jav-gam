import javax.swing.JFrame;

class Main
{
	public static void main(String args[])
	{
		JFrame wnd = new JFrame();
		wnd.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		wnd.setResizable(false);
		wnd.setTitle("Game");

		GamePanel gamePanel = new GamePanel();
		wnd.add(gamePanel);
		wnd.pack();

		wnd.setLocationRelativeTo(null);
		wnd.setVisible(true);

		gamePanel.startGameThread();
	}
}
