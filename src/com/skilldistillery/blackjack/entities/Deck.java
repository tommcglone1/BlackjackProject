package com.skilldistillery.blackjack.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	private List<Card> deckOfCards = new ArrayList<>();

	public Deck() {
		Rank[] ranks = Rank.values();
		Suit[] suits = Suit.values();

		for (Suit suit : suits) {
			for (Rank rank : ranks) {
				Card card = new Card(suit, rank);
				deckOfCards.add(card);
			}
		}
	}

	public int checkDeckSize() {
		int cardsLeftInDeck = deckOfCards.size();
		return cardsLeftInDeck;
	}

	public Card dealCard() {
		Card removed = deckOfCards.remove(0);

		return removed;
	}

	public void shuffle() {
		Collections.shuffle(deckOfCards);
	}

	public List<Card> getDeckOfCards() {
		return deckOfCards;
	}

}
