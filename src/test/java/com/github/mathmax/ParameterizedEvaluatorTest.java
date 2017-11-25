package com.github.mathmax;

import static com.github.mathmax.TestUtils.toleranceDelta;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import com.github.mathmax.Evaluator;
import com.google.common.collect.ImmutableList;

@RunWith( Parameterized.class )
public class ParameterizedEvaluatorTest {
	private static final Evaluator evaluator = Evaluator.create();
	private final double expectedResult;
	private final String expression;

	@Parameters
	public static Collection<Object[]> data() {
		return ImmutableList.copyOf( new Object[][] {
				{ "5", 5 },
				{ "4+2", 6 },
				{ "7-3", 4 },
				{ "18*2+4", 40 },
				{ "2 + 8 / 4 * 5 - 10 * 2", - 8 },
				{ "5 / 2", 2.5 },
				{ "1 / 3", 1. / 3 },
				{ "2.5 * 2", 5 },
				{ "1.58+2.42", 4 },
				{ "2^5", 32 },
				{ "2+2^5", 34 },
				{ "2/3/4", 0.16666666666666666 },
				{ "2-3-4", - 5 },
				{ "2^3^4", Math.pow( 2, Math.pow( 3, 4 ) ) },
				{ "(2+2)^5", Math.pow( 4, 5 ) },
				{ "(2 + 5) / (14 / 2)", 1 },
				{ "2 * (2 + 8)", 20 },
				{ "((1+1) / 4) ^ (((1 + 1)))", Math.pow( 0.5, 2 ) },
				{ "((((2))))", 2 },
				{ "((((2)) * 4))", 8 } } );
	}

	public ParameterizedEvaluatorTest( final String expression,
			final double expectedResult ) {
		this.expression = expression;
		this.expectedResult = expectedResult;
	}

	@Test
	public void testByParameter() {
		evaluateAndAssert( expression, expectedResult );
	}

	private static void evaluateAndAssert(
			final String expression,
			final double expectedResult ) {
		final double result = evaluator.evaluate( expression );
		assertEquals( expectedResult, result, toleranceDelta );
	}
}
