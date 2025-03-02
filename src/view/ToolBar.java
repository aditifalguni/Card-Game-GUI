package view;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

import controller.ButtonListener;
import controller.ComboboxListener;
import controller.MenuItemListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MessageEnum.message;

public class ToolBar extends JToolBar implements Observer 
{
	private ButtonListener bl;
	private ViewModel view;

	public ToolBar(GameEngine ge, ViewModel view) 
	{
		
		
		this.view = view;
		bl = new ButtonListener(view);

		JButton deal = new JButton("Deal");
		deal.addActionListener(bl);
		add(deal);

		JButton bet = new JButton("Bet");
		bet.addActionListener(bl);
		add(bet);
		
		
		JComboBox<String> selector = new JComboBox<String>();
        selector.addItem("The Loser");
        selector.addItem("The Shark");
        selector.addItem("House");
        this.add(selector);
     
	}
       


}
