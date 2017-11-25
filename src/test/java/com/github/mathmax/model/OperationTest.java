package com.github.mathmax.model;

import org.junit.Assert;
import org.junit.Test;

import com.github.mathmax.model.Operation;
import com.google.common.collect.ImmutableList;

public class OperationTest {
	@Test
	public void testInvalidOperationCharCode() {
		for ( final char charCode : ImmutableList.of( 'x', '.', '!' ) ) {
			try {
				Operation.fromChar( charCode );
				Assert.fail( "Expected to be unrecognized operation." );
			} catch ( final IllegalArgumentException e ) {
				// expected
			}
		}
	}
}
