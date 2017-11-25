package com.github.mathmax;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.lang.Character.isDigit;

import java.text.DecimalFormatSymbols;
import java.util.List;

import com.github.mathmax.model.GrouperType;
import com.github.mathmax.model.Operation;
import com.github.mathmax.model.Token;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public final class Tokenizer {
	private static final char decimalSeparator = DecimalFormatSymbols
			.getInstance().getDecimalSeparator();

	public List<Token> tokenize( final String rawExpression ) {
		checkNotNull( rawExpression, "Null raw expression." );
		final String expression = removeMultipleWhitespaceCharacters( rawExpression );

		StringBuilder literalBuilder = new StringBuilder();
		final Builder<Token> tokens = ImmutableList.builder();
		for ( int i = 0; i < expression.length(); i++ ) {
			char currentChar = expression.charAt( i );
			int j = i;
			while ( isLiteralCharCode( currentChar ) ) {
				literalBuilder.append( currentChar );
				j++;
				if ( j == expression.length() ) {
					break;
				}
				currentChar = expression.charAt( j );
			}
			i = j;

			final String literalString = literalBuilder.toString();
			if ( ! literalString.isEmpty() ) {
				literalBuilder = new StringBuilder();
				final Double literalValue = Double.valueOf( literalString );
				tokens.add( Token.createLiteral( literalValue ) );
			}
			if ( i == expression.length() ) {
				break;
			}
			if ( Operation.isValidCharRepresentation( currentChar ) ) {
				final Operation operation = Operation.fromChar( currentChar );
				tokens.add( Token.createOperation( operation ) );
			}
			if ( Character.isWhitespace( currentChar ) ) {
				continue;
			}
			if ( GrouperType.isValidCharRepresentation( currentChar ) ) {
				final GrouperType type = GrouperType.fromChar( currentChar );
				tokens.add( Token.createGrouper( type ) );
			}
		}
		return tokens.build();
	}

	private String removeMultipleWhitespaceCharacters(
			final String rawExpression ) {
		return rawExpression.trim().replaceAll( "[\\s\\t\\r\\n]", " " );
	}

	private boolean isLiteralCharCode( final char currentChar ) {
		return isDigit( currentChar ) || currentChar == decimalSeparator;
	}
}
