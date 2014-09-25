package pokerPackage;
import java.util.*;

import pokerPackage.Card.Rank;

/**
 * A poker hand is a list of cards, which can be of some "kind" (pair, straight, etc.)
 * 
 */
public class Hand implements Comparable<Hand> {

    public enum Kind {HIGH_CARD, PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, 
        FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH}

    private final List<Card> cards;

    /**
     * Create a hand from a string containing all cards (e,g, "5C TD AH QS 2D")
     */
    public Hand(String c) 
    {
        
        String delims = "[ ]";
        String[] strCards = c.split(delims);
        
        cards = new ArrayList<Card>();
        for(int i =0;i<5;i++)
        {
        	Card newCard = new Card(strCards[i]);
        	cards.add(newCard);
        }
        sortHand();
        
    }
    public void sortHand()
    {
    	Collections.sort(cards,new Comparator<Card>(){
    		public int compare(Card card1,Card card2)
    		{
    			int rank1,rank2;
    			
    			rank1 = card1.getRank().ordinal();
    			rank2 = card2.getRank().ordinal();
    			return rank1 - rank2; 
    			
    		}
    		
    	});
    }
    
    /**
     * @returns true if the hand has n cards of the same rank
	 * e.g., "TD TC TH 7C 7D" returns True for n=2 and n=3, and False for n=1 and n=4
     */
    protected boolean hasNKind(int n) {
    	
    	Card prevCard, currCard;
    	int nKinds[] = {0,0};
    	int c = 0;
    	prevCard = cards.get(0);
    	for(int i = 1; i <5;i++)
    	{
    		currCard = cards.get(i);
    		if(prevCard.getRank().equals(currCard.getRank()))
    		{
    			nKinds[c]++;
    		}
    		else
    		{
    			if(nKinds[c] !=0) c++;
    
    		}
    		prevCard= currCard;
    	}
    	if(nKinds[0] == (n-1) || nKinds[1] == (n-1)) 
    		return true;
    	else
    		return false;
    }
    
    /**
	 * Optional: you may skip this one. If so, just make it return False
     * @returns true if the hand has two pairs
     */
    public boolean isTwoPair() {
    	
    	Card prevCard, currCard;
    	int nKinds[] = {0,0};
    	int c = 0;
    	prevCard = cards.get(0);
    	for(int i = 1; i <5;i++)
    	{
    		currCard = cards.get(i);
    		if(prevCard.getRank().equals(currCard.getRank()))
    		{
    			nKinds[c]++;
    		}
    		else
    		{
    			if(nKinds[c] !=0) c++;
    
    		}
    		prevCard= currCard;
    	}
    	if(nKinds[0] == 1 && nKinds[1] == 1) 
    		return true;
    	else
    		return false;
    }   
    
    /**
     * @returns true if the hand is a straight 
     */
    public boolean isStraight() {
    	Card prevCard, currCard,firstCard;
    	
    	
    	prevCard = cards.get(0);
    	firstCard = prevCard;
    	for(int i = 1; i <5;i++)
    	{
    		currCard = cards.get(i);
    		if(!(currCard.getRank().ordinal() - prevCard.getRank().ordinal() == 1))
    		{
    			if(!(firstCard.getRank().equals(Rank.DEUCE) && currCard.getRank().equals(Rank.ACE)))
    			return false;
    		}
    		prevCard = currCard;
    	}
    	return true;
    }
    
    /**
     * @returns true if the hand is a flush
     */
    public boolean isFlush() 
    {
     
    	Card prevCard, currCard;
    	
    	prevCard = cards.get(0);
    	for(int i = 1; i <5;i++)
    	{
    		currCard = cards.get(i);
    		if(!(prevCard.getSuit().equals(currCard.getSuit())))
    		{
    			return false;
    		}
    		
    	}
    	return true;
    }
    
    @Override
    public int compareTo(Hand h) {
    	
    	if( this.kind().ordinal() - h.kind().ordinal() ==0)
    	{
    		Card card1 = this.cards.get(4);
    		Card card2 = h.cards.get(4);
    		
    		return card1.getRank().ordinal() - card2.getRank().ordinal();
    	}
    	else
    	{
    		return this.kind().ordinal() - h.kind().ordinal();
    	}
    }
    
    /**
	 * This method is already implemented and could be useful! 
     * @returns the "kind" of the hand: flush, full house, etc.
     */
    public Kind kind() {
        if (isStraight() && isFlush()) return Kind.STRAIGHT_FLUSH;
        else if (hasNKind(4)) return Kind.FOUR_OF_A_KIND; 
        else if (hasNKind(3) && hasNKind(2)) return Kind.FULL_HOUSE;
        else if (isFlush()) return Kind.FLUSH;
        else if (isStraight()) return Kind.STRAIGHT;
        else if (hasNKind(3)) return Kind.THREE_OF_A_KIND;
        else if (isTwoPair()) return Kind.TWO_PAIR;
        else if (hasNKind(2)) return Kind.PAIR; 
        else return Kind.HIGH_CARD;
    }

}