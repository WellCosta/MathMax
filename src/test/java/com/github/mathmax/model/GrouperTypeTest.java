package com.github.mathmax.model;

import org.junit.Assert;
import org.junit.Test;

import com.github.mathmax.model.Operation;
import com.google.common.collect.ImmutableList;

public class GrouperTypeTest {
	@Test
	public void testInvalidOperationCharCode() {
		for ( final char charCode : ImmutableList.of( '[', ']', '{', '}', '<',
				'>' ) ) {
			try {
				Operation.fromChar( charCode );
				Assert.fail( "Expected to be unrecognized grouper type." );
			} catch ( final IllegalArgumentException e ) {
				// expected
			}
		}
	}
}
