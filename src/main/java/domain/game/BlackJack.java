package domain.game;

import java.util.List;

import controller.InputController;
import view.InputView;

public class BlackJack {
	private InputController inputController;
	private InputView inputView;
	private Match match;
	private boolean firstDone;

	public BlackJack() {
		inputView = new InputView();
		inputController = new InputController(inputView);
	}

	public void run() {
		setUp();

		firstRound();
		if (firstDone) {
			finish();
			return;
		}

		secondRound();
		finish();
	}

	private void setUp() {
		List<String> playerNames = inputController.getPlayerNames();
		List<Double> bettings = inputController.getBettings(playerNames);
		match = new Match(playerNames, bettings);
	}

	private void firstRound() {
		match.distribute(Rule.getBasicDraw());
		match.findBlackJackWinners(Rule.getBlackjackPoint());
		if (match.hasWinners()) {
			firstDone = true;
		}
	}

	private void secondRound() {
		match.doPlayersPhase(inputController);
		match.doDealerPhase();
		match.findPointWinners();
	}

	private void finish() {

	}
}
