package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.interfaces.PlayingCard;
import view.ImageIconFactory;

public class ViewModel extends Observable 
{
	
	private ArrayList<Observer> users = new ArrayList<Observer>();
	private GameEngine gameEngine;
	private Player currentPlayer;
	
	private LinkedList<Player> haventDealtAllPlayers = new LinkedList<Player>();

	private Map<Player, PlayingCard> playerBust = new HashMap<Player, PlayingCard>();

	private Player dealingPlayer;
	private int score;
	private String notif;

	public ViewModel(GameEngine gameEngine) 
	{
		this.gameEngine = gameEngine;
	}

	public void addObservers(Observer o) 
	{
		users.add(o);
	}

	// removes observers when needed
	public void removeObservers(Observer o) 
	{
		users.remove(o);
	}

	// addPlayer button calls this
	public void addPlayer(Player player) 
	{
		gameEngine.addPlayer(player);
		setChanged();
		
	}

	public void removePlayer(String id) 
	{
		gameEngine.removePlayer(gameEngine.getPlayer(id));
		setChanged();
		
	}


	public void currentPlayer(Player player) 
	{
		currentPlayer = player;
	}

	public void Bet(int bet) 
	{
		score = 0;
		setChanged();
		if (currentPlayer != null) 
		{
			if (currentPlayer.getBet() == 0  ) 
			{
				reset();
				notif = currentPlayer.getPlayerName() + " has placed a bet of: "
							+ bet;
				notifyStatus(notif);
				haventDealtAllPlayers.add(currentPlayer);
			} 
			
			else 
			{
				notif = currentPlayer.getPlayerName()
							+ " has failed to place a bet";
				notifyStatus(notif);
			}
		}
	}

	
	
	public void dealPlayer() 
	{
		setChanged();
		if (haventDealtAllPlayers.contains(currentPlayer)) 
		{
			if (currentPlayer.getBet() > 0) 
			{
				new Thread() 
				{
					@Override
					public void run() 
					{
						gameEngine.dealPlayer(currentPlayer, 1000);
					}
				}.start();
			}

		} 
		
		else 
		{
			notif = currentPlayer.getPlayerName() + " cannot be dealt";
			notifyStatus(notif);
		}
	}

	public Player getCurrentPlayer() 
	{
		return currentPlayer;
	}

	public void clicked() 
	{
		setChanged();
		int num = 1;
		notifyObservers(num);
	}

	
	public void cardDealt(PlayingCard card, Player player) 
	{
		setChanged();
		if (dealingPlayer == null) 
		{
			
		} 
		else 
		{
			if (!dealingPlayer.equals(player)) 
			{
				score = 0;
			}
		}
		
		dealingPlayer = player;
		if (currentPlayer.equals(dealingPlayer)) 
		{
			setChanged();
			notifyObservers(card);
		}
		
		score += card.getScore();
		notif = player.getPlayerName() + " has a score of: " + score;
		notifyStatus(notif);
	}

	public void hasBust(PlayingCard card, Player player) 
	{
		playerBust.put(player, card);
		notif = player.getPlayerName() + " has a score of: " + score
					+ "...BUSTED!";
		notifyStatus(notif);
		if (currentPlayer.equals(player)) 
		{
			setChanged();
			notifyObservers(card);
		}
		
		int count = 0;
		for (Player aplayer : haventDealtAllPlayers) 
		{
			if (playerBust.containsKey(aplayer)) 
			{
				count++;
			}
			
			if (haventDealtAllPlayers.size() == count) 
			{
				haventDealtAllPlayers.clear();
				
				new Thread() 
				{
					@Override
					public void run() 
					{
						gameEngine.dealHouse(1000);
					}
				}.start();
			}
		}
		score = 0;
	}

	public void callBustCard(Player selectedPlayer) 
	{
		if (haventDealtAllPlayers.contains(selectedPlayer)) 
		{
			if (playerBust.containsKey(selectedPlayer)) 
			{
				setChanged();
				notifyObservers(playerBust.get(selectedPlayer));
			} 
			else 
			{
				setChanged();
				notifyObservers(MessageEnum.message.reset_panel);
			}
		}

	}

	public void houseCardDealt(PlayingCard card) 
	{
		setChanged();
		notifyObservers(card);
		score += card.getScore();
		notif = "HOUSE has a score of: " + score;
		notifyStatus(notif);
	}

	public void houseHasBust(PlayingCard card) 
	{
		notif = "HOUSE has a score of: " + score + "...BUSTED!";
		notifyStatus(notif);
		setChanged();
		notifyObservers(card);
	}

	public void getHouseResult(int result) 
	{
		notif = "HOUSE RESULT: " + result;
		notifyStatus(notif);
		dealingPlayer = null;
		playerBust.clear();
	}


	private void notifyStatus(String notif) 
	{
		setChanged();
		notifyObservers(notif);
	}


	
}
