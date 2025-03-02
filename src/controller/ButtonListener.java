package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.interfaces.Player;
import view.ImageIconFactory;
import view.ViewModel;

public class ButtonListener implements ActionListener 
{
	private ViewModel view;

	public ButtonListener(ViewModel view) 
	{
		this.view = view;
	}



	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("Bet")) 
		{
			JTextField field1 = new JTextField();
			Object[] fields = { "Enter Bet", field1 };
			FieldKeyListener kl = new FieldKeyListener();
			field1.addKeyListener(kl);

			JOptionPane.showConfirmDialog(null, fields, "Place Bet",
					JOptionPane.OK_CANCEL_OPTION);
			String sbet = field1.getText();
     	
		    
			if (!sbet.equals("")) 
			{
				int bet = Integer.valueOf(sbet);
				if (bet > 0) 
				{
					view.Bet(bet);
				}
			}
			
		}

		
		if (e.getActionCommand().equals("Deal")) 
		{
			view.dealPlayer();
			new ImageIconFactory();
			new ImageIcon();
		}
	}


}
