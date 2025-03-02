package view;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;

import Frame.MenuBar;
import model.interfaces.GameEngine;
import model.interfaces.Player;



public class MainFrame extends JFrame 
{

	private ToolBar toolBar;

	public MainFrame(GameEngine gameEngine, ViewModel viewModel) 
	{

		setBounds(50, 50, 1400, 700);
	
		toolBar = new ToolBar(gameEngine, viewModel);
		
		
		viewModel.addObserver(toolBar);
	
		
		add(toolBar, BorderLayout.NORTH);
		

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	
}
