package com.github.mathmax;

import static com.github.mathmax.TestUtils.division;
import static com.github.mathmax.TestUtils.five;
import static com.github.mathmax.TestUtils.four;
import static com.github.mathmax.TestUtils.leftParentheses;
import static com.github.mathmax.TestUtils.minus;
import static com.github.mathmax.TestUtils.one;
import static com.github.mathmax.TestUtils.plus;
import static com.github.mathmax.TestUtils.rightParentheses;
import static com.github.mathmax.TestUtils.three;
import static com.github.mathmax.TestUtils.times;
import static com.github.mathmax.TestUtils.two;
import static com.google.common.collect.ImmutableList.of;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.github.mathmax.Parser;
import com.github.mathmax.exception.SyntaxExpressionException;
import com.github.mathmax.model.SyntaxTree;
import com.github.mathmax.model.Token;
import com.google.common.collect.ImmutableList;

public class ParserTest {
	private static final Parser parser = new Parser();

	@Test
	public void testAdditionParsing() {
		final SyntaxTree syntaxTree = parser.parse( of( four, plus, two ) );
		final List<Token> expectedTokens = ImmutableList.of( four, two, plus );
		assertEquals( expectedTokens, syntaxTree.getTokens() );
	}

	@Test
	public void testMultipleAdditionParsing() {
		final SyntaxTree syntaxTree = parser.parse( of( four, plus, two, plus,
				five ) );
		final List<Token> expectedTokens = of( four, two, plus, five, plus );
		assertEquals( expectedTokens, syntaxTree.getTokens() );
	}

	@Test
	public void testAdditionAndSubtractionParsing() {
		final List<Token> inputTokens = of( four, plus, two, minus, five );
		final SyntaxTree syntaxTree = parser.parse( inputTokens );
		final List<Token> expectedTokens = of( four, two, plus, five, minus );
		assertEquals( expectedTokens, syntaxTree.getTokens() );
	}

	@Test
	public void testMultiplicationAndAdditionParsing() {
		final List<Token> inputTokens = of( five, plus, one, plus, two, times,
				three );
		final SyntaxTree syntaxTree = parser.parse( inputTokens );
		final List<Token> expectedTokens = of( five, one, plus, two, three,
				times, plus );
		assertEquals( expectedTokens, syntaxTree.getTokens() );
	}

	@Test
	public void testDivisionAndSubtractionParsing() {
		final List<Token> inputTokens = of( five, minus, four, division, two );
		final SyntaxTree syntaxTree = parser.parse( inputTokens );
		final List<Token> expectedTokens = of( five, four, two, division, minus );
		assertEquals( expectedTokens, syntaxTree.getTokens() );
	}

	@Test( expected = SyntaxExpressionException.class )
	public void testSyntaxErrorForOperatorBeforeOperand() {
		parser.parse( of( plus, one ) );
	}

	@Test( expected = SyntaxExpressionException.class )
	public void testSyntaxErrorForOperatorWithSingleOperand() {
		parser.parse( of( one, plus ) );
	}

	@Test( expected = SyntaxExpressionException.class )
	public void testSyntaxErrorForMismatchedRightParentheses() {
		parser.parse( of( rightParentheses ) );
	}

	@Test( expected = SyntaxExpressionException.class )
	public void testSyntaxErrorForMismatchedLeftParentheses() {
		parser.parse( of( leftParentheses ) );
	}
}
