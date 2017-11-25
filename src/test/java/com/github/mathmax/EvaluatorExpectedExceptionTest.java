package com.github.mathmax;

import org.junit.Test;

import com.github.mathmax.Evaluator;
import com.github.mathmax.exception.SyntaxExpressionException;

public class EvaluatorExpectedExceptionTest {
	private final Evaluator evaluator = Evaluator.create();

	@Test( expected = SyntaxExpressionException.class )
	public void testInvalidSyntaxForEmptyCharInsteadOfOperator() {
		System.out.println( evaluator.evaluate( "5 5" ) );
	}

	@Test( expected = ArithmeticException.class )
	public void testArithmeticExceptionForDivisionByZero() {
		evaluator.evaluate( "10 / 0" );
	}

	@Test( expected = SyntaxExpressionException.class )
	public void testInvalidExpressionForNoOperand() {
		evaluator.evaluate( "+" );
	}

	@Test( expected = SyntaxExpressionException.class )
	public void testInvalidExpressionForSingleOperand() {
		evaluator.evaluate( "9+" );
	}

	@Test( expected = SyntaxExpressionException.class )
	public void testSyntaxErrorForEmptyExpression() {
		evaluator.evaluate( "" );
	}

	@Test( expected = NullPointerException.class )
	public void testNPEForNullExpression() {
		evaluator.evaluate( null );
	}
}
