package com.game.service;

import java.util.Arrays;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.game.model.PlayerBoard;
import com.game.service.PlayConnect4;
import com.google.gson.Gson;

/**
 * @author Vinit
 *
 */

@Path("/playconnect4/{playerBoard}")
public class PlayConnect4{

	private final int width = 7;
	private final int height = 6;
	private char[][] grid;
	private int lastCol, lastRow;

	private void _initialize(){
		this.lastCol = -1;
		this.lastRow = -1;
		this.grid = new char[height][];
		for (int h = 0; h < height; h++) {
			Arrays.fill(this.grid[h] = new char[width], '.');
		}
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String play(String playerBoard){
		Gson gson = new Gson();
		PlayerBoard pb = gson.fromJson(playerBoard, PlayerBoard.class);

		this.grid = pb.getGrid();
		if (pb.getLastCol() < 0 && pb.getLastRow() < 0) {
			_initialize();
		}
		System.out.println(pb.getDiceColor());
		char symbol = (pb.getDiceColor().equals(new String("red"))) ? 'X' : '0';//red is just a default color
		System.out.println(symbol);
		pb.setGameOutcome(this.dropADice(symbol, pb.getDiceDropCol()));

		pb.setGrid(this.grid);
		pb.setLastCol(this.lastCol);
		pb.setLastRow(this.lastRow);
		//return the json object to client
		return gson.toJson(pb);
	}

	public String dropADice(char symbol, int colPosition) {
		do {
			if (! (0 <= colPosition && colPosition < this.width)) {
				System.out.println("Column must be between 0 and " +
						(this.width - 1));
				continue;
			}
			for (int h = this.height - 1; h >= 0; h--) {
				if (this.grid[h][colPosition] == '.') {
					this.grid[this.lastRow=h][this.lastCol=colPosition] = symbol;
					return gameOutcome();
				}
			}
			System.out.println("Column " + colPosition + " is full.");
		} while (true);
	}

	private String gameOutcome() {
		String outcome = null;

		if (this.lastCol == -1) {
			throw new IllegalStateException("No players has made any moves...");
		}
		char symbol = this.grid[this.lastRow][this.lastCol];
		String strike = String.format("%c%c%c%c", symbol, symbol, symbol, symbol);

		//draw scenario
		if(contains(this.slashHorizontal(), strike) ||
				contains(this.slashVertical(), strike) ||
				contains(this.slashDiagonal(), strike) ||
				contains(this.backslashDiagonal(), strike)){
			outcome = new String("won");
		}else if(isBoardFilled()){
			outcome = new String("drawn");
		}else{
			outcome = new String("Running...");
		}

		return outcome;
	}

	private boolean contains(String base, String match) {
		return base.indexOf(match) >= 0;
	}

	private String slashHorizontal() {
		return new String(this.grid[this.lastRow]);
	}
	private String slashVertical() {
		StringBuilder sb = new StringBuilder(this.height);
		for (int h = 0; h < this.height; h++) {
			sb.append(this.grid[h][this.lastCol]);
		}
		return sb.toString();
	}
	private String slashDiagonal() {
		StringBuilder sb = new StringBuilder(this.height);
		for (int h = 0; h < this.height; h++) {
			int w = this.lastCol + this.lastRow - h;
			if (0 <= w && w < this.width) {
				sb.append(this.grid[h][w]);
			}
		}
		return sb.toString();
	}
	private String backslashDiagonal() {
		StringBuilder sb = new StringBuilder(this.height);
		for (int h = 0; h < this.height; h++) {
			int w = this.lastCol - this.lastRow + h;
			if (0 <= w && w < this.width) {
				sb.append(this.grid[h][w]);
			}
		}
		return sb.toString();
	}

	private boolean isBoardFilled(){
		boolean filled = true;

		for(int h = 0; h < this.height; h++){
			for(int w = 0; w < this.width; w++){
				if(this.grid[h][w] == '.'){
					filled = false;
				}
			}
		}
		return filled;
	}
}
