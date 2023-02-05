package com.skilldistillery.blackjack.app;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.skilldistillery.blackjack.entities.BlackjackDealer;
import com.skilldistillery.blackjack.entities.BlackjackHand;
import com.skilldistillery.blackjack.entities.BlackjackPlayer;

public class BlackjackApp {
	static Scanner sc = new Scanner(System.in);
	private BlackjackHand playersHand = new BlackjackHand();
	private BlackjackHand dealersHand = new BlackjackHand();
	private BlackjackPlayer player = new BlackjackPlayer(playersHand);
	private BlackjackDealer dealer = new BlackjackDealer(dealersHand);
	private final static int RULE_OF_SEVENTEEN = 17;
	private final static int CUT_CARD = 13;
	public final static int HIGHEST_BLACKJACK_SCORE = 21;

	public static void main(String[] args) {
		BlackjackApp bjapp = new BlackjackApp();
		bjapp.playGame();
		sc.close();
	}

	public void playGame() {
		boolean isGameContinuing = true;
		boolean checkStop = true;
		while (dealer.cardsRemaining() > CUT_CARD) {
			while (isGameContinuing) {
				playersHand.clearCards();
				dealersHand.clearCards();
				dealHands();
				if (playersHand.getHandValue() > HIGHEST_BLACKJACK_SCORE
						|| dealersHand.getHandValue() > HIGHEST_BLACKJACK_SCORE) {
					dealer.showDealersHand();
					System.out.println("No player should be able to have above 21 on a deal."
							+ " This hand is a wash. Try again.");
					break;
				}

				checkStop = blackjackCheck(isGameContinuing);
				if (checkStop == false && dealer.cardsRemaining() < CUT_CARD) {
					endGameMessage();
					break;
				} else if (checkStop == false) {
					break;
				}
				checkStop = playersDecision(isGameContinuing);
				if (checkStop == false && dealer.cardsRemaining() < CUT_CARD) {
					endGameMessage();
					break;
				} else if (checkStop == false) {
					break;
				}
				checkStop = dealerMechanic(isGameContinuing);
				if (checkStop == false && dealer.cardsRemaining() < CUT_CARD) {
					endGameMessage();
					break;
				} else if (checkStop == false) {
					break;
				}
				checkStop = comparingHands(isGameContinuing);
				if (checkStop == false && dealer.cardsRemaining() < CUT_CARD) {
					endGameMessage();
					break;
				} else if (checkStop == false) {
					break;
				}
			}
		}
	}

	public void dealHands() {
		System.out.println();
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

	}

	public boolean blackjackCheck(boolean isGameContinuing) {

		if (playersHand.isBlackjack() == true) {
			System.out.println("***Congrats! Player has Blackjack!***");
			dealer.showDealersHand();
			isGameContinuing = false;
		} else if (dealersHand.isBlackjack() == true) {
			dealer.showDealersHand();
			System.out.println("---Unfortunately the dealer has Blackjack. Try again.---");
			isGameContinuing = false;
		}
		return isGameContinuing;
	}

	public boolean playersDecision(boolean isGameContinuing) {
		int userInput = 0;
		boolean playerStillHitting = true;
		System.out.println();
		while (playerStillHitting) {

			System.out.println("Would you like to stand or hit? \n" + "Press 1 for stand or 2 for hit.");
			userInput = sc.nextInt();
			sc.nextLine();
			if (userInput == 1) {
				System.out.println("You have choosen to stand");
				playerStillHitting = false;

			} else if (userInput == 2) {
				playersHand.addCard(dealer.dealToPlayer());
				player.showPlayersHand();
				player.checkHandValue();
				if (playersHand.isBust() == true) {
					System.out.println("---Sorry, player is over 21. Dealer has won. Try again.---");
					dealer.showDealersHand();
					playerStillHitting = false;
					isGameContinuing = false;
				}
			}
			else {
				System.out.println("Invalid number please try again");
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
				System.out.println("Dealer draws another card.");
				dealersHand.addCard(dealer.dealToSelf());
				dealer.showDealersHand();
				dealer.checkHandValue();
				if (dealersHand.isBust() == true) {
					System.out.println("***Dealer is over 21. Player Wins!***");
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
			System.out.println("***The Player is closer to 21! Congrats the player has won!***");
			isGameContinuing = false;

		} else if (playersHand.getHandValue() < dealersHand.getHandValue()
				&& dealersHand.getHandValue() <= HIGHEST_BLACKJACK_SCORE) {
			System.out.println("---Unfortunately the dealer is closer to 21 and has won.---");
			isGameContinuing = false;
		} else if (playersHand.getHandValue() == dealersHand.getHandValue()) {
			System.out.println("This hand is a push");
			isGameContinuing = false;
		}
		return isGameContinuing;
	}

	public void endGameMessage() {
		System.out.println();
		System.out.println("***The deck will run out of cards shortly, " + "and needs to be reshuffeled. \n"
				+ "Press run to resuffle and play again!***");
	}
}
