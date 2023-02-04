package com.skilldistillery.blackjack.testing;

import com.skilldistillery.blackjack.entities.Rank;
import com.skilldistillery.blackjack.entities.Suit;

public class CardTest {
  public static void main(String[] args) {
    Rank[] ranks = Rank.values();
    for(int i=0; i<ranks.length; i++) {
      System.out.println(ranks[i] + " " + ranks[i].getValue());
    }
    
    for(Suit s : Suit.values()){
      System.out.println(s);
    }
  }
}


// X Deck is shuffled,
// X card is dealt to player face up
// X card is added to players hand
// card is dealt to dealer face down
// X card is added to dealers
// X card is dealt to player face up
// X card is added to players hand
// X card is dealt to dealer face up
// X card is added to dealers hand and is shown
// X hand values are displayed based on visible cards,
// X player makes decision to hit or stand, if player hits they are dealt a card
// X face up
// X that is added to their hand total
// X when if player stands the game is transfered to the dealer
// X dealer reveals bottom card and that is added to their hand total
// (print to console and adding ot hand does not happen till here)
// X if current dealers hand is over 17 they are forced to stand
// X if the hand is less than 17 they are forced to hit and add a card to their
// hand
// X dealers decision to hit or stand is based on 17 rule logic
// X card totals are compared to each other and whoever is closest to 21 without
// going over wins
