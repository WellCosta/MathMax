package com.github.mathmax.main;

import com.google.common.base.Joiner;

public abstract class Messages {
	private Messages() {
		throw new AssertionError();
	}

	static final String lineFeed = System.lineSeparator();
	static final String usage = Joiner.on( lineFeed )
			.join( "Welcome to the math evaluator!",
					"1) Type e to enter the evaluation mode;",
					"2) Type x to exit;",
					"3) Type h to see this usage message again." + lineFeed );
	static final String evaluation = "e";
	static final String exit = "x";
	static final String help = "h";

	static final String evaluationPrompt = "Type a mathematical expression to be evaluated: "
			+ lineFeed;
	static final String evaluationResultMessage = lineFeed + "Result: "
			+ lineFeed;
	static final String unrecognizedCommandMessage = "Unrecognized command: ";
}
