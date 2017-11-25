package com.github.mathmax.model;

import static com.google.common.collect.ImmutableList.copyOf;

import java.util.Collection;
import java.util.List;

import com.github.mathmax.exception.SyntaxExpressionException;

public final class SyntaxTree {
	private final List<Token> tokens;

	public static SyntaxTree create( final Collection<Token> tokens ) {
		final List<Token> tokenList = copyOf( tokens );
		validateSyntax( tokenList );
		return new SyntaxTree( tokenList );
	}

	private SyntaxTree( final List<Token> tokens ) {
		this.tokens = tokens;
	}

	private static void validateSyntax( final List<Token> tokenList ) {
		int operandCounter = 0;
		for ( final Token token : tokenList ) {
			if ( token.getType().isLiteral() ) {
				operandCounter++;
			}
			if ( token.getType().isOperation() ) {
				if ( operandCounter == 1 ) {
					throw new SyntaxExpressionException(
							"Expected two operands but got only one: "
									+ tokenList );
				}
				operandCounter--;
			}
		}
	}

	public List<Token> getTokens() {
		return tokens;
	}
}
