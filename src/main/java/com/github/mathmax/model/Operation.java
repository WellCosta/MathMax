package com.github.mathmax.model;

public enum Operation {
	PLUS('+', 1, Associativeness.LEFT) {
		@Override
		public double apply( final double leftOperand, final double rightOperand ) {
			return leftOperand + rightOperand;
		}
	},
	MINUS('-', 1, Associativeness.LEFT) {
		@Override
		public double apply( final double leftOperand, final double rightOperand ) {
			return leftOperand - rightOperand;
		}
	},
	TIMES('*', 2, Associativeness.LEFT) {
		@Override
		public double apply( final double leftOperand, final double rightOperand ) {
			return leftOperand * rightOperand;
		}
	},
	DIVISION('/', 2, Associativeness.LEFT) {
		@Override
		public double apply( final double leftOperand, final double rightOperand ) {
			if ( rightOperand == 0 ) {
				throw new ArithmeticException( "Division by zero." );
			}
			return leftOperand / rightOperand;
		}
	},
	POWER('^', 3, Associativeness.RIGHT) {
		@Override
		public double apply( final double leftOperand, final double rightOperand ) {
			return Math.pow( leftOperand, rightOperand );
		}
	};

	private final char charRepresentation;
	private final int precedence;
	private final Associativeness associativeness;

	private Operation( final char charRepresentation, final int precedence,
			final Associativeness associativeness ) {
		this.charRepresentation = charRepresentation;
		this.precedence = precedence;
		this.associativeness = associativeness;
	}

	public static Operation fromChar( final char operationChar ) {
		for ( final Operation operation : values() ) {
			if ( operation.charRepresentation == operationChar ) {
				return operation;
			}
		}
		throw new IllegalArgumentException(
				"Invalid operation char representation: " + operationChar );
	}

	public static boolean isValidCharRepresentation( final char operationChar ) {
		for ( final Operation operation : values() ) {
			if ( operation.charRepresentation == operationChar ) {
				return true;
			}
		}
		return false;
	}

	public char getCharRepresentation() {
		return charRepresentation;
	}

	public int getPrecedence() {
		return precedence;
	}

	public Associativeness getAssociativeness() {
		return associativeness;
	}

	public abstract double apply( double leftOperand, double rightOperand );
}
