package com.game.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.game.model.PlayerBoard;
import com.google.gson.Gson;

/**
 * @author Vinit
 *
 */
public class PlayConnect4Test {

	private PlayConnect4 connect;
	private PlayerBoard pb;
	Gson gson;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
		connect = new PlayConnect4( );
		pb = new PlayerBoard();
		pb.setClientId("12345");
		pb.setDiceColor("red");
		pb.setLastCol(-1);
		pb.setLastRow(-1);
		gson = new Gson();
	}

	@Test
	public void testWinningPlay() {
		pb.setDiceColor("red");
		pb.setDiceDropCol(5);
		String round1 = connect.play(gson.toJson(pb));
		System.out.println(round1);
		
		pb.setDiceColor("red");
		pb.setDiceDropCol(5);
		String round2 = connect.play(round1);
		System.out.println(round2);
		
		pb.setDiceColor("red");
		pb.setDiceDropCol(5);
		String round3 = connect.play(round2);
		System.out.println(round3);
		
		pb.setDiceColor("red");
		pb.setDiceDropCol(5);
		String round4 = connect.play(round3);
		System.out.println(round4);
		
		assertEquals("won", gson.fromJson(round4, PlayerBoard.class).getGameOutcome());
	}
	
	@Test
	public void testRunningPlay(){
		pb.setDiceColor("red");
		pb.setDiceDropCol(5);
		String round1 = connect.play(gson.toJson(pb));
		
		assertEquals("Running...", gson.fromJson(round1, PlayerBoard.class).getGameOutcome());
	}
}
