package com.skilldistillery.blackjack.entities;

public class BlackjackDealer extends BlackjackPlayer {
	private Deck dealersDeck = new Deck();

	public BlackjackDealer(BlackjackHand dealersHand) {
		super(dealersHand);
		shuffleDeck();
	}

	public void cardsRemaining() {
		System.out.println(dealersDeck.checkDeckSize());
	}

	public Card dealToPlayer() {

		return dealersDeck.dealCard();

	}

	public Card dealToSelf() {

		return dealersDeck.dealCard();
	}

	public void shuffleDeck() {
		dealersDeck.shuffle();
	}

	public void showOneCard() {
		System.out.println("The dealers upcard is a " + bjHand.getCards().get(1));
	}
	
	public void oneCardValue() {
		System.out.println("The value of the dealers upcard is " + bjHand.getCards().get(1).getValue());
				
	}
	public void showDealersHand() {
		System.out.println("Dealers hand is " + bjHand);
	}
	@Override
	public void checkHandValue() {
		System.out.println("The dealer has a total of " + bjHand.getHandValue());
	}
	
}
