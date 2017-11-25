package com.github.mathmax.exception;

import static com.google.common.base.Preconditions.checkNotNull;

public class SyntaxExpressionException extends RuntimeException {
	private static final long serialVersionUID = 7865492356589585449L;

	public SyntaxExpressionException( final String message ) {
		super( checkNotNull( message, "Null description message." ) );
	}
}
