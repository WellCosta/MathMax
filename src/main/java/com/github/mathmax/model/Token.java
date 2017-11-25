package com.github.mathmax.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Objects;

public final class Token {
	private final TokenType type;
	private final Object value;

	public static Token createLiteral( final double literalValue ) {
		return new Token( literalValue, TokenType.LITERAL );
	}

	public static Token createOperation( final Operation operation ) {
		checkNotNull( operation, "Null operation." );
		return new Token( operation, TokenType.OPERATION );
	}

	public static Token createGrouper( final GrouperType grouperType ) {
		checkNotNull( grouperType, "Null grouper type." );
		return new Token( grouperType, TokenType.GROUPER );
	}

	private Token( final Object value, final TokenType type ) {
		this.type = type;
		this.value = value;
	}

	public TokenType getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

	@Override
	public String toString() {
		if ( type.isOperation() ) {
			final Operation operationValue = (Operation) value;
			return Character.toString( operationValue.getCharRepresentation() );
		}
		return value.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash( value, type );
	}

	@Override
	public boolean equals( final Object obj ) {
		if ( ! (obj instanceof Token) ) {
			return false;
		}
		final Token other = (Token) obj;
		return Objects.equals( value, other.value )
				&& Objects.equals( type, other.type );
	}
}
