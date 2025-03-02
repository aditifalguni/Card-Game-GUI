package view;

import java.awt.Image;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import model.PlayingCardImpl;
import model.interfaces.PlayingCard;

public class ImageIconFactory
{
	private static final String FILE_PATH = String.format("images%s",
			File.separator);

    private static Map<PlayingCard, ImageIcon> imageIconMap;

    private static Map<String, PlayingCard.Suit> suitMap = new HashMap<String, PlayingCard.Suit>();

    private static Map<Integer, PlayingCard.Value> valueMap = new HashMap<Integer, PlayingCard.Value>();

    public static ImageIcon getImageIcon(PlayingCard playingCard) 
    {

    	if (imageIconMap == null)
    		createImageIcons();

    	return imageIconMap.get(playingCard);
    }

    private static void createImageIcons() 
    {
    	imageIconMap = new HashMap<PlayingCard, ImageIcon>();
    	suitMap.put("C", PlayingCard.Suit.CLUBS);
    	suitMap.put("D", PlayingCard.Suit.DIAMONDS);
    	suitMap.put("H", PlayingCard.Suit.HEARTS);
    	suitMap.put("S", PlayingCard.Suit.SPADES);
    	valueMap.put(1, PlayingCard.Value.ACE);
    	valueMap.put(8, PlayingCard.Value.EIGHT);
    	valueMap.put(9, PlayingCard.Value.NINE);
    	valueMap.put(10, PlayingCard.Value.TEN);
    	valueMap.put(11, PlayingCard.Value.JACK);
    	valueMap.put(12, PlayingCard.Value.QUEEN);
    	valueMap.put(13, PlayingCard.Value.KING);

    	for (int i = 1; i <= 13; i++) 
    	{

    		imageIconMap.put(
    					new PlayingCardImpl(valueMap.get(i), suitMap.get("C")),
    					new ImageIcon(new ImageIcon(getFullPath("C", i))
    								.getImage().getScaledInstance(100, 100,
    											Image.SCALE_DEFAULT)));

    		imageIconMap.put(
    					new PlayingCardImpl(valueMap.get(i), suitMap.get("D")),
    					new ImageIcon(new ImageIcon(getFullPath("D", i))
    								.getImage().getScaledInstance(100, 100,
    											Image.SCALE_DEFAULT)));

    		imageIconMap.put(
    					new PlayingCardImpl(valueMap.get(i), suitMap.get("S")),
    					new ImageIcon(new ImageIcon(getFullPath("S", i))
    								.getImage().getScaledInstance(100, 100,
    											Image.SCALE_DEFAULT)));

    		imageIconMap.put(
    					new PlayingCardImpl(valueMap.get(i), suitMap.get("H")),
    					new ImageIcon(new ImageIcon(getFullPath("H", i))
    								.getImage().getScaledInstance(100, 100,
    											Image.SCALE_DEFAULT)));
    	}
    }
    private static String getFullPath(String suit, int val) 
    {
    	return FILE_PATH + val + suit + ".png";

    }
	


}
