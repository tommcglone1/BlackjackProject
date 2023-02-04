package com.skilldistillery.blackjack.entities;


public class BlackjackHand extends Hand {
	private final static int BLACKJACK = 21;

	public BlackjackHand() {

	}

	public int getHandValue() {
		int sum = 0;
		for (Card card : cards) {
			sum = sum + card.getValue();
		}
		return sum;
	}

	public boolean isBlackjack() {

		if (getHandValue() == BLACKJACK && cards.size() == 2) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isBust() {

		if (getHandValue() > BLACKJACK) {
			return true;
		} else {
			return false;
		}
	}
}
