package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.List;

public abstract class Hand {
	protected List<Card> cards = new ArrayList<>();

	public Hand() {
	}

	public void addCard(Card card) {
		cards.add(card);
	}

	public void clearCards() {
			cards.clear();
	}

	public abstract int getHandValue();

	
	public String toString() {
		return "" + cards;
	}

	public List<Card> getCards() {
		return cards;
	}

	public void setCards(List<Card> cards) {
		this.cards = cards;
	}
	
}
