package com.skilldistillery.blackjack.entities;



public class BlackjackPlayer {
	protected BlackjackHand bjHand = new BlackjackHand();

	public BlackjackPlayer(BlackjackHand bjHand) {
		this.bjHand = bjHand;
	}

	public void checkHandValue() {
		System.out.println("The player has a total of " + bjHand.getHandValue());
	}


	public BlackjackHand getPlayersHand() {
		return bjHand;
	}
	
	public void showPlayersHand() {
		System.out.println("Players hand is " + bjHand);
	}

}
