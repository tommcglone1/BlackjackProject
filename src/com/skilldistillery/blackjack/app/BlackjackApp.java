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
	private final static int RULE_OF_SEVENTEEN = 17;
	private final static int CUT_CARD = 13;
	public final static int HIGHEST_BLACKJACK_SCORE = 21;

	public static void main(String[] args) {
		BlackjackApp bjapp = new BlackjackApp();
		bjapp.playBlackjack();
	}

	public void playBlackjack() {
		Boolean isGameContinuing = true;
		while (isGameContinuing) {


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

			boolean checkStop = blackjackCheck(isGameContinuing);
			if(checkStop == false ) {
				break;
			}
			checkStop = playersDecision(isGameContinuing);
			if(checkStop == false ) {
				break;
			}
			checkStop = dealerMechanic(isGameContinuing);
			if(checkStop == false ) {
				break;
			}
			checkStop = comparingHands(isGameContinuing);
			if(checkStop == false ) {
				break;
			}

		}
	}

	public boolean blackjackCheck(boolean isGameContinuing) {

		if (playersHand.isBlackjack() == true) {
			System.out.println("Congrats! you got Blackjack!");
			isGameContinuing = false;
		} else if (dealersHand.isBlackjack() == true) {
			System.out.println("Unfortunately the dealer has Blackjack. Try again.");
			isGameContinuing = false;
		} 
		return isGameContinuing;
	}

	public boolean playersDecision(boolean isGameContinuing) {
		boolean playerStillHitting = true;
		System.out.println();
		while (playerStillHitting) {
			System.out.println("Would you like to stand or hit? \n" + "Press 1 for stand and 2 for hit.");
			int userInput = sc.nextInt();
			sc.nextLine();
			if (userInput == 1) {
				System.out.println("You have choosen to stand");
				playerStillHitting = false;

			} else if (userInput == 2) {
				playersHand.addCard(dealer.dealToPlayer());
				player.showPlayersHand();
				player.checkHandValue();
				if (playersHand.isBust() == true) {
					System.out.println("Sorry you are over 21. Try again.");
					playerStillHitting = false;
					isGameContinuing = false;

				}

			}
		}
		return isGameContinuing;

	}

	public boolean dealerMechanic(boolean isGameContinuing) {
		boolean dealerStillHitting = true;
		dealer.showDealersHand();
		dealer.checkHandValue();
		while (dealerStillHitting) {
			if (dealersHand.getHandValue() >= RULE_OF_SEVENTEEN
					&& dealersHand.getHandValue() <= HIGHEST_BLACKJACK_SCORE) {
				System.out.println("Dealer must stand");
				dealerStillHitting = false;
			} else {
				dealersHand.addCard(dealer.dealToSelf());
				dealer.showDealersHand();
				dealer.checkHandValue();
				if (dealersHand.isBust() == true) {
					System.out.println("Dealer is over 21. You Win!");
					dealerStillHitting = false;
					isGameContinuing = false;

				}
			}
		}
		return isGameContinuing;
	}

	public boolean comparingHands(boolean isGameContinuing) {
		if (playersHand.getHandValue() > dealersHand.getHandValue()
				&& playersHand.getHandValue() <= HIGHEST_BLACKJACK_SCORE) {
			System.out.println("Congrats the player has won!");
			isGameContinuing = false;

		} else if (playersHand.getHandValue() < dealersHand.getHandValue()
				&& dealersHand.getHandValue() <= HIGHEST_BLACKJACK_SCORE) {
			System.out.println("Unfortunately the dealer has won.");
			isGameContinuing = false;
		} else if (playersHand.getHandValue() == dealersHand.getHandValue()) {
			System.out.println("This hand is a push");
			isGameContinuing = false;
		}
		return isGameContinuing;
	}
}
