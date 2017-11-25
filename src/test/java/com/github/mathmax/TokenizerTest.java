package com.github.mathmax;

import static java.util.Collections.singletonList;

import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.github.mathmax.Tokenizer;
import com.github.mathmax.model.Operation;
import com.github.mathmax.model.Token;
import com.google.common.collect.ImmutableList;

public class TokenizerTest {
	private static final Tokenizer tokenizer = new Tokenizer();

	@Test
	public void testLiteralToken() {
		final String expression = "5";
		final List<Token> tokens = tokenizer.tokenize( expression );
		final Token literalToken = Token.createLiteral( 5 );
		final List<Token> expectedTokens = singletonList( literalToken );
		Assert.assertThat( tokens, CoreMatchers.equalTo( expectedTokens ) );
	}

	@Test
	public void testAdditionToken() {
		final String expression = "4 + 2";
		final List<Token> tokens = tokenizer.tokenize( expression );
		final List<Token> expectedTokens = ImmutableList.of(
				Token.createLiteral( 4 ),
				Token.createOperation( Operation.PLUS ),
				Token.createLiteral( 2 ) );
		Assert.assertThat( tokens, CoreMatchers.equalTo( expectedTokens ) );
	}

	@Test
	public void testSubtractionToken() {
		final String expression = "7 - 3";
		final List<Token> tokens = tokenizer.tokenize( expression );
		final List<Token> expectedTokens = ImmutableList.of(
				Token.createLiteral( 7 ),
				Token.createOperation( Operation.MINUS ),
				Token.createLiteral( 3 ) );
		Assert.assertThat( tokens, CoreMatchers.equalTo( expectedTokens ) );
	}

	@Test
	public void testMultiplicationToken() {
		final String expression = "5 * 10";
		final List<Token> tokens = tokenizer.tokenize( expression );
		final List<Token> expectedTokens = ImmutableList.of(
				Token.createLiteral( 5 ),
				Token.createOperation( Operation.TIMES ),
				Token.createLiteral( 10 ) );
		Assert.assertThat( tokens, CoreMatchers.equalTo( expectedTokens ) );
	}

	@Test
	public void testDivisionToken() {
		final String expression = "42 / 6";
		final List<Token> tokens = tokenizer.tokenize( expression );
		final List<Token> expectedTokens = ImmutableList.of(
				Token.createLiteral( 42 ),
				Token.createOperation( Operation.DIVISION ),
				Token.createLiteral( 6 ) );
		Assert.assertThat( tokens, CoreMatchers.equalTo( expectedTokens ) );
	}

	@Test
	public void testEmptyCharactersLiteralToken() {
		final String expression = "         16    	";
		final List<Token> tokens = tokenizer.tokenize( expression );
		final List<Token> expectedTokens = ImmutableList.of( Token
				.createLiteral( 16 ) );
		Assert.assertThat( tokens, CoreMatchers.equalTo( expectedTokens ) );
	}

	@Test
	public void testEmptyCharactersOperationToken() {
		final String expression = "         +    	";
		final List<Token> tokens = tokenizer.tokenize( expression );
		final List<Token> expectedTokens = ImmutableList.of( Token
				.createOperation( Operation.PLUS ) );
		Assert.assertThat( tokens, CoreMatchers.equalTo( expectedTokens ) );
	}

	@Test
	public void testInvalidLiteralToken() {
		final String expression = "         a    	";
		final List<Token> tokens = tokenizer.tokenize( expression );
		final List<Token> expectedTokens = ImmutableList.of();
		Assert.assertThat( tokens, CoreMatchers.equalTo( expectedTokens ) );
	}
}
