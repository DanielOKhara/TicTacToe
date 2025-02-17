package model.player;

import model.GameFigure;

import java.util.Random;

public class ComputerPlayerModel extends Player{

    public ComputerPlayerModel(GameFigure figure) {
        super("Computer", figure);
    }

    @Override
    public String move() {
        int number = new Random().nextInt(9) + 1;
        return String.valueOf(number);
    }
}