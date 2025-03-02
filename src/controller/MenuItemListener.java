package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.SimplePlayer;
import view.ViewModel;

public class MenuItemListener implements ActionListener 
{
	private ViewModel viewModel;

	public MenuItemListener(ViewModel viewModel) 
	{

		this.viewModel = viewModel;
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("The Loser")) 
		{
			ButtonListener bl = new ButtonListener(viewModel);
			JButton bet = new JButton("Bet");
			bet.addActionListener(bl);
			
			JButton deal = new JButton("Deal");
			deal.addActionListener(bl);
			
		}
	
		if (e.getActionCommand().equals(" The Shark")) 
		{

			ButtonListener bl = new ButtonListener(viewModel);
			JButton bet = new JButton("Bet");
			bet.addActionListener(bl);

			JButton deal = new JButton("Deal");
			deal.addActionListener(bl);
			
		}

	}
}
