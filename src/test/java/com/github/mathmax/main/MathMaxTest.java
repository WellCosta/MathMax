package com.github.mathmax.main;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.SystemOutRule;
import org.junit.contrib.java.lang.system.TextFromStandardInputStream;

import com.github.mathmax.main.MathMax;
import com.github.mathmax.main.Messages;

public class MathMaxTest {
	@Rule
	public final TextFromStandardInputStream systemInMock = TextFromStandardInputStream
			.emptyStandardInputStream();
	@Rule
	public final SystemOutRule systemOutRule = new SystemOutRule().enableLog();

	@Test
	public void testNoOp() {
		systemInMock.provideLines( "x" );
		MathMax.main( new String[0] );
		final String expectedOutput = Messages.usage;
		assertEquals( expectedOutput, systemOutRule.getLog() );
	}

	@Test
	public void testHelpMessage() {
		systemInMock.provideLines( "h", "x" );
		MathMax.main( new String[0] );
		final String expectedOutput = Messages.usage + Messages.usage;
		assertEquals( expectedOutput, systemOutRule.getLog() );
	}

	@Test
	public void testEvaluationAndMessages() {
		systemInMock.provideLines( "e", "5 + 5 * 2 + 28 - 1 / 1", "x" );
		MathMax.main( new String[0] );

		final String expressionResult = "42.0";
		final String expectedOutput = Messages.usage
				+ Messages.evaluationPrompt
				+ Messages.evaluationResultMessage
				+ expressionResult;
		assertEquals( expectedOutput, systemOutRule.getLog() );
	}

	@Test
	public void testUnrecognizedCommand() {
		systemInMock.provideLines( "k", "x" );
		MathMax.main( new String[0] );
		final String expectedOutput = Messages.usage
				+ Messages.unrecognizedCommandMessage
				+ "k"
				+ System.lineSeparator()
				+ Messages.usage;
		assertEquals( expectedOutput, systemOutRule.getLog() );
	}
}
