package com.github.mathmax.main;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.github.mathmax.EvaluatorExpectedExceptionTest;
import com.github.mathmax.EvaluatorPackageSanityTest;
import com.github.mathmax.ParameterizedEvaluatorTest;
import com.github.mathmax.ParserTest;
import com.github.mathmax.TokenizerTest;
import com.github.mathmax.model.OperationTest;

@RunWith( Suite.class )
@SuiteClasses( {
		TokenizerTest.class,
		ParserTest.class,
		ParameterizedEvaluatorTest.class,
		EvaluatorExpectedExceptionTest.class,
		EvaluatorPackageSanityTest.class,
		OperationTest.class,
		MathMaxTest.class
} )
public class AllTests {
}
