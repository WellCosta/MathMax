package com.github.mathmax;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.github.mathmax.exception.SyntaxExpressionException;
import com.github.mathmax.model.Associativeness;
import com.github.mathmax.model.GrouperType;
import com.github.mathmax.model.Operation;
import com.github.mathmax.model.SyntaxTree;
import com.github.mathmax.model.Token;

public final class Parser {
	public SyntaxTree parse( final List<Token> tokens ) {
		checkNotNull( tokens, "Null tokens list." );
		final Queue<Token> outputQueue = new LinkedList<Token>();
		final Deque<Token> operationStack = new ArrayDeque<>();
		for ( final Token token : tokens ) {
			if ( token.getType().isLiteral() ) {
				outputQueue.offer( token );
				continue;
			}
			if ( token.getType().isOperation() ) {
				processOperationToken( outputQueue, operationStack, token );
				continue;
			}
			if ( token.getType().isGrouper() ) {
				processGrouperTypeToken( outputQueue, operationStack, token );
			}
		}
		validateParenthesesMatching( operationStack );
		pushRemainingOperatorToOutput( outputQueue, operationStack );
		return SyntaxTree.create( outputQueue );
	}

	private void processOperationToken(
			final Queue<Token> outputQueue,
			final Deque<Token> operationStack,
			final Token token ) {
		if ( outputQueue.isEmpty() ) {
			throw new SyntaxExpressionException(
					"Found operation before an operand." );
		}
		final Operation operation = (Operation) token.getValue();
		for ( final Iterator<Token> tokenIterator = operationStack.iterator(); tokenIterator
				.hasNext(); ) {
			final Token stackedOperationToken = tokenIterator.next();
			if ( stackedOperationToken.getType().isGrouper() ) {
				break;
			}
			if ( stackedOperationToken.getType().isOperation() ) {
				final Operation stackedOperation = (Operation) stackedOperationToken
						.getValue();
				if ( stackedOperation.getPrecedence() >= operation
						.getPrecedence()
						&& operation.getAssociativeness() == Associativeness.LEFT ) {
					tokenIterator.remove();
					outputQueue.offer( stackedOperationToken );
				}
			}
		}
		operationStack.push( token );
	}

	private void processGrouperTypeToken(
			final Queue<Token> outputQueue,
			final Deque<Token> operationStack,
			final Token token ) {
		final GrouperType grouperType = (GrouperType) token.getValue();
		if ( grouperType == GrouperType.LEFT_PARENTHESES ) {
			operationStack.push( token );
			return;
		}
		checkArgument( grouperType == GrouperType.RIGHT_PARENTHESES );
		boolean foundLeftParentheses = false;
		for ( final Iterator<Token> tokenIterator = operationStack.iterator(); tokenIterator
				.hasNext(); ) {
			final Token stackedOperationToken = tokenIterator.next();
			if ( isLeftParentheses( stackedOperationToken ) ) {
				foundLeftParentheses = true;
				tokenIterator.remove();
				break;
			}
			tokenIterator.remove();
			outputQueue.offer( stackedOperationToken );
		}
		if ( ! foundLeftParentheses ) {
			throw new SyntaxExpressionException(
					"Mismatched right parentheses." );
		}
	}

	private void validateParenthesesMatching( final Deque<Token> operationStack ) {
		final Token operationStackHead = operationStack.peekFirst();
		if ( operationStackHead != null
				&& operationStackHead.getType().isGrouper() ) {
			throw new SyntaxExpressionException( "Mismatched parentheses: "
					+ operationStackHead );
		}
	}

	private void pushRemainingOperatorToOutput(
			final Queue<Token> outputQueue,
			final Deque<Token> operationStack ) {
		for ( final Token operationToken : operationStack ) {
			outputQueue.offer( operationToken );
		}
	}

	private boolean isLeftParentheses( final Token token ) {
		if ( ! token.getType().isGrouper() ) {
			return false;
		}
		final GrouperType stackedGrouperType = (GrouperType) token.getValue();
		return stackedGrouperType == GrouperType.LEFT_PARENTHESES;
	}
}
