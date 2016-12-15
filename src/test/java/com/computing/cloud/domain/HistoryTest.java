package com.computing.cloud.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.computing.cloud.AbstractTest;

public class HistoryTest extends AbstractTest {
	
	@Test
	public void builderContract() {
	    final History.HistoryBuilder historyBuilderA = new History.HistoryBuilder();
	    final History.HistoryBuilder historyBuilderB = new History.HistoryBuilder();
	    assertEquals(historyBuilderA.toString(), historyBuilderB.toString());
	}
	
}