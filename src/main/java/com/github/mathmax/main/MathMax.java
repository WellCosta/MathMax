package com.github.mathmax.main;

import static com.github.mathmax.main.Messages.evaluation;
import static com.github.mathmax.main.Messages.evaluationPrompt;
import static com.github.mathmax.main.Messages.evaluationResultMessage;
import static com.github.mathmax.main.Messages.exit;
import static com.github.mathmax.main.Messages.help;
import static com.github.mathmax.main.Messages.unrecognizedCommandMessage;

import java.util.Scanner;

import com.github.mathmax.Evaluator;

public final class MathMax {
	public static void main( final String[] args ) {
		final MathMax main = new MathMax();
		main.startExecutionLoop();
	}

	public void startExecutionLoop() {
		try ( final Scanner scanner = new Scanner( System.in ) ) {
			final Evaluator evaluator = Evaluator.create();
			printUsageMessage();

			executionLoop: while ( true ) {
				final String input = scanner.nextLine();
				switch ( input ) {
				case evaluation:
					doEvaluationSteps( evaluator, scanner );
					break;
				case help:
					printUsageMessage();
					break;
				case exit:
					break executionLoop;
				default:
					System.out.println( unrecognizedCommandMessage + input );
					printUsageMessage();
				}
			}
		}
	}

	private void printUsageMessage() {
		System.out.print( Messages.usage );
	}

	private static void doEvaluationSteps(
			final Evaluator evaluator,
			final Scanner scanner ) {
		System.out.print( evaluationPrompt );
		final String rawExpression = scanner.nextLine();
		final double result = evaluator.evaluate( rawExpression );
		System.out.print( evaluationResultMessage + result );
	}
}
