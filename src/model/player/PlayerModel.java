package model.player;

import model.GameFigure;

import java.util.Scanner;

public class PlayerModel extends Player{

    private final Scanner scanner;

    public PlayerModel (String name, GameFigure gameFigure, Scanner scanner){
        super(name, gameFigure);
        this.scanner = scanner;
    }

    @Override
    public String move() {
        return scanner.nextLine();
    }
}
