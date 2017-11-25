package com.github.mathmax;

import static com.github.mathmax.model.GrouperType.LEFT_PARENTHESES;
import static com.github.mathmax.model.GrouperType.RIGHT_PARENTHESES;
import static com.github.mathmax.model.Operation.DIVISION;
import static com.github.mathmax.model.Operation.MINUS;
import static com.github.mathmax.model.Operation.PLUS;
import static com.github.mathmax.model.Operation.TIMES;

import com.github.mathmax.model.Token;

abstract class TestUtils {
	private TestUtils() {
		throw new AssertionError();
	}

	static final double toleranceDelta = 0.e-10;

	static final Token one = Token.createLiteral( 1 );
	static final Token two = Token.createLiteral( 2 );
	static final Token three = Token.createLiteral( 3 );
	static final Token four = Token.createLiteral( 4 );
	static final Token five = Token.createLiteral( 5 );
	static final Token six = Token.createLiteral( 6 );

	static final Token plus = Token.createOperation( PLUS );
	static final Token minus = Token.createOperation( MINUS );
	static final Token times = Token.createOperation( TIMES );
	static final Token division = Token.createOperation( DIVISION );

	static final Token rightParentheses = Token
			.createGrouper( RIGHT_PARENTHESES );
	static final Token leftParentheses = Token
			.createGrouper( LEFT_PARENTHESES );
}
