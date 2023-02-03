package com.skilldistillery.blackjack.testing;

import com.skilldistillery.blackjack.entities.Deck;

public class DealingCards {

	public static void main(String[] args) {
		DealingCards dealer = new DealingCards();
		dealer.deal();
	}

	public void deal() {
		Deck theDeck = new Deck();
		theDeck.shuffle();
		System.out.println(theDeck.dealCard());
		System.out.println(theDeck.dealCard());
		System.out.println(theDeck.dealCard());
		System.out.println(theDeck.dealCard());
		System.out.println(theDeck.dealCard());
		System.out.println(theDeck.dealCard());
		System.out.println(theDeck.dealCard());
		System.out.println(theDeck.dealCard());
		System.out.println( theDeck.checkDeckSize());
	}

}
