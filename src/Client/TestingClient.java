package Client;

import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import client.SimpleTestClient;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import view.GameEngineCallbackGUI;
import view.GameEngineCallbackImpl;
import view.MainFrame;
import view.ViewModel;

public class TestingClient extends SimpleTestClient 
{
	private static Logger logger = Logger.getLogger("assignment1");

	public static void main(String args[]) 
	{

		final GameEngine gameEngine = new GameEngineImpl();
		final ViewModel viewModel = new ViewModel(gameEngine);
		
		new SimplePlayer("1", "The Shark", 20);
		new SimplePlayer("2", "The Loser", 1000);

		
		gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
		gameEngine.addGameEngineCallback(new GameEngineCallbackGUI(viewModel));
;

		new Thread() 
		{
			@Override
			public void run() 
			{

			}
		}.start();

		SwingUtilities.invokeLater(new Runnable() 
		{
			@Override
			public void run() 
			{

				new MainFrame(gameEngine, viewModel);
			}
		});

	}

}