package com.github.mathmax.model;

public enum TokenType {
	LITERAL, OPERATION, GROUPER;

	public boolean isLiteral() {
		return this == LITERAL;
	}

	public boolean isOperation() {
		return this == OPERATION;
	}

	public boolean isGrouper() {
		return this == GROUPER;
	}
}
