package com.github.mathmax.model;

public enum GrouperType {
	LEFT_PARENTHESES('('),
	RIGHT_PARENTHESES(')');
	private final char charRepresentation;

	private GrouperType( final char charRepresentation ) {
		this.charRepresentation = charRepresentation;
	}

	public static boolean isValidCharRepresentation(
			final char charRepresentation ) {
		for ( final GrouperType grouperType : values() ) {
			if ( grouperType.charRepresentation == charRepresentation ) {
				return true;
			}
		}
		return false;
	}

	public static GrouperType fromChar( final char grouperTypeChar ) {
		for ( final GrouperType grouperType : values() ) {
			if ( grouperType.charRepresentation == grouperTypeChar ) {
				return grouperType;
			}
		}
		throw new IllegalArgumentException(
				"Invalid grouper type char representation: " + grouperTypeChar );
	}

	@Override
	public String toString() {
		return String.valueOf( charRepresentation );
	}
}
