package model.player;

import model.GameFigure;

public abstract class Player {

    private final String name;
    private final GameFigure figure;

    public Player(String name, GameFigure figure){
        this.name = name;
        this.figure = figure;
    }

    public abstract String move();

    public String getName() {
        return name;
    }

    public GameFigure getFigure() {
        return figure;
    }
}