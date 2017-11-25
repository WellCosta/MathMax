package com.github.mathmax;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.collect.Iterables.getOnlyElement;

import java.util.List;

import com.github.mathmax.exception.SyntaxExpressionException;
import com.github.mathmax.model.Operation;
import com.github.mathmax.model.SyntaxTree;
import com.github.mathmax.model.Token;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public final class Interpreter {
	public double interpret( final SyntaxTree syntaxTree ) {
		checkNotNull( syntaxTree, "Null syntax tree." );

		final List<Token> tokens = syntaxTree.getTokens();
		if ( tokens.size() == 1 ) {
			final Token singleToken = getOnlyElement( tokens );
			checkArgument( singleToken.getType().isLiteral() );
			return (double) singleToken.getValue();
		}

		final int operationIndex = indexOfFirstOperation( tokens );
		final Token operation = tokens.get( operationIndex );

		final int leftOperandIndex = operationIndex - 2;
		final Token leftOperand = tokens.get( leftOperandIndex );

		final int rightOperandIndex = operationIndex - 1;
		final Token rightOperand = tokens.get( rightOperandIndex );

		final Token result = apply( operation, leftOperand, rightOperand );
		final Builder<Token> resultantTokens = ImmutableList.<Token> builder()
				.addAll( tokens.subList( 0, leftOperandIndex ) ).add( result );

		if ( operationIndex + 1 < tokens.size() ) {
			resultantTokens.addAll( tokens.subList( operationIndex + 1,
					tokens.size() ) );
		}

		return interpret( SyntaxTree.create( resultantTokens.build() ) );
	}

	private int indexOfFirstOperation( final List<Token> tokens ) {
		for ( int i = 0; i < tokens.size(); i++ ) {
			final Token token = tokens.get( i );
			if ( token.getType().isOperation() ) {
				return i;
			}
		}
		throw new SyntaxExpressionException( "Invalid syntax tree: " + tokens );
	}

	private Token apply(
			final Token operationToken,
			final Token leftOperandToken,
			final Token rightOperandToken ) {
		final Operation operation = (Operation) operationToken.getValue();
		final double leftOperand = (double) leftOperandToken.getValue();
		final double rightOperand = (double) rightOperandToken.getValue();
		final double result = operation.apply( leftOperand, rightOperand );
		return Token.createLiteral( result );
	}
}
