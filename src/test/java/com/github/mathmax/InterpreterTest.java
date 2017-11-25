package com.github.mathmax;

import static com.github.mathmax.TestUtils.division;
import static com.github.mathmax.TestUtils.five;
import static com.github.mathmax.TestUtils.minus;
import static com.github.mathmax.TestUtils.plus;
import static com.github.mathmax.TestUtils.six;
import static com.github.mathmax.TestUtils.times;
import static com.github.mathmax.TestUtils.toleranceDelta;
import static com.github.mathmax.TestUtils.two;
import static com.google.common.collect.ImmutableList.of;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.github.mathmax.Interpreter;
import com.github.mathmax.model.SyntaxTree;
import com.github.mathmax.model.Token;

public class InterpreterTest {
	private static final Interpreter interpreter = new Interpreter();

	@Test
	public void testLiteralInterpretation() {
		final SyntaxTree syntaxTree = SyntaxTree.create( of( five ) );
		final Double result = interpreter.interpret( syntaxTree );
		assertEquals( 5., result, toleranceDelta );
	}

	@Test
	public void testAdditionInterpretation() {
		final List<Token> tokens = of( five, two, plus );
		final SyntaxTree syntaxTree = SyntaxTree.create( tokens );
		final Double result = interpreter.interpret( syntaxTree );
		assertEquals( 7., result, toleranceDelta );
	}

	@Test
	public void testSubtractionInterpretation() {
		final List<Token> tokens = of( TestUtils.five, two, minus );
		final SyntaxTree syntaxTree = SyntaxTree.create( tokens );
		final Double result = interpreter.interpret( syntaxTree );
		assertEquals( 3., result, toleranceDelta );
	}

	@Test
	public void testDivisionAndMultiplicationInterpretation() {
		final List<Token> tokens = of( five, six, times, two, division );
		final SyntaxTree syntaxTree = SyntaxTree.create( tokens );
		final Double result = interpreter.interpret( syntaxTree );
		assertEquals( 15., result, toleranceDelta );
	}
}
