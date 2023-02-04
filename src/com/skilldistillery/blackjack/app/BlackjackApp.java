package com.skilldistillery.blackjack.app;

import java.util.Scanner;

import com.skilldistillery.blackjack.entities.BlackjackDealer;
import com.skilldistillery.blackjack.entities.BlackjackHand;
import com.skilldistillery.blackjack.entities.BlackjackPlayer;
import com.skilldistillery.blackjack.entities.Deck;

public class BlackjackApp {
	Scanner sc = new Scanner(System.in);
	private Deck deck = new Deck();
	private BlackjackHand playersHand = new BlackjackHand();
	private BlackjackHand dealersHand = new BlackjackHand();
	private BlackjackPlayer player = new BlackjackPlayer(playersHand);
	private BlackjackDealer dealer = new BlackjackDealer(dealersHand);
	private final static int RULE_OF_SEVENTEEN =17;
	private final static int CUT_CARD = 13;
	public static void main(String[] args) {
		BlackjackApp bjapp = new BlackjackApp();
		bjapp.playBlackjack();
	}

	public void playBlackjack() {
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
		// dealer reveals bottom card and that is added to their hand total
		// (print to console and adding ot hand does not happen till here)
		// if current dealers hand is over 17 they are forced to stand
		// if the hand is less than 17 they are forced to hit and add a card to their
		// hand
		// dealers decision to hit or stand is based on 17 rule logic
		// card totals are compared to each other and whoever is closest to 21 without
		// going over wins
//		while (deck.checkDeckSize() > CUT_CARD) {

		playersHand.addCard(dealer.dealToPlayer());

		player.showPlayersHand();

		dealersHand.addCard(dealer.dealToSelf());

		System.out.println("The dealers first card is hidden.");

		playersHand.addCard(dealer.dealToPlayer());

		player.showPlayersHand();

		dealersHand.addCard(dealer.dealToSelf());

		dealer.showOneCard();
		dealer.oneCardValue();
		player.checkHandValue();

		if (playersHand.isBlackjack() == true) {
			System.out.println("Congrats! you got Blackjack!");
		} else if (dealersHand.isBlackjack() == true) {
			System.out.println("Unfortunately the dealer has Blackjack. Try again.");
		} else {
//				continue;
		}
		playersDecision();
		
	}
//	}

	public void playersDecision() {
		boolean stillHitting = true;
		System.out.println();
		while (stillHitting) {
			System.out.println("Would you like to stand or hit? \n" + "Press 1 for stand and 2 for hit.");
			int userInput = sc.nextInt();
			sc.nextLine();
			if (userInput == 1) {
				System.out.println("You have choosen to stand");
				stillHitting = false;
			} else if (userInput == 2) {
				playersHand.addCard(dealer.dealToPlayer());
				player.showPlayersHand();
				player.checkHandValue();
				if (playersHand.isBust() == true) {
					System.out.println("Sorry you are over 21. Try again.");
					break;
				}
				else {
					continue;
				}
			}
		}
	}

	
}
