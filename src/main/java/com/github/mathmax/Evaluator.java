package com.github.mathmax;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.github.mathmax.model.SyntaxTree;
import com.github.mathmax.model.Token;

public final class Evaluator {
	private final Tokenizer tokenizer;
	private final Parser parser;
	private final Interpreter interpreter;

	public static Evaluator create() {
		return new Evaluator( new Tokenizer(), new Parser(), new Interpreter() );
	}

	private Evaluator( final Tokenizer tokenizer, final Parser parser,
			final Interpreter interpreter ) {
		this.tokenizer = tokenizer;
		this.parser = parser;
		this.interpreter = interpreter;
	}

	public double evaluate( final String expression ) {
		checkNotNull( expression, "Null expression." );
		final List<Token> tokens = tokenizer.tokenize( expression );
		final SyntaxTree syntaxTree = parser.parse( tokens );
		return interpreter.interpret( syntaxTree );
	}
}
