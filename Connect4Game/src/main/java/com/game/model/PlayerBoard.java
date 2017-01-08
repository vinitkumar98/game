package com.game.model;

public class PlayerBoard {

	private String clientId;
	private String playerName;
	private String diceColor;
	private int diceDropCol;
	private char[][] grid;
	private int lastCol;
	private int lastRow;
	
	private String gameOutcome;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getDiceColor() {
		return diceColor;
	}
	public void setDiceColor(String diceColor) {
		this.diceColor = diceColor;
	}
	public int getDiceDropCol() {
		return diceDropCol;
	}
	public void setDiceDropCol(int diceDropCol) {
		this.diceDropCol = diceDropCol;
	}
	public char[][] getGrid() {
		return grid;
	}
	public void setGrid(char[][] grid) {
		this.grid = grid;
	}
	public int getLastCol() {
		return lastCol;
	}
	public void setLastCol(int lastCol) {
		this.lastCol = lastCol;
	}
	public int getLastRow() {
		return lastRow;
	}
	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}
	public String getGameOutcome() {
		return gameOutcome;
	}
	public void setGameOutcome(String gameOutcome) {
		this.gameOutcome = gameOutcome;
	}
}
