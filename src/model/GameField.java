package model;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameField {

    private final Map<Integer, GameFigure> figureMap;

    private static final List<String> COMBINATIONS = List.of("1 2 3",
            "4 5 6",
            "7 8 9",
            "1 4 7",
            "2 5 8",
            "3 6 9",
            "1 5 9",
            "3 5 7");

    public GameField() {
        figureMap = new HashMap<>();
        for (int i = 1; i < 10; i++) {
            figureMap.put(i, GameFigure.EMPTY);
        }
    }

    public boolean playerMove(String usersMove, GameFigure figure) {
        Integer moveNumber;
        try {
            moveNumber = Integer.valueOf(usersMove);
        } catch (NumberFormatException ex){
            System.out.println("Incorrect input");
            return false;
        }

        if ((!figureMap.containsKey(moveNumber))) {
            System.out.println("Wrong choice! Repeat please");
            return false;
        }

        return figureMap.replace(moveNumber, GameFigure.EMPTY, figure);
    }

    public void printField() {
        System.out.println(MessageFormat.format("""
                         {0} | {1} | {2} 
                        -----|-----|-----
                         {3} | {4} | {5} 
                        -----|-----|-----
                         {6} | {7} | {8}
                        """,
                fill(1, figureMap.get(1)),
                fill(2, figureMap.get(2)),
                fill(3, figureMap.get(3)),
                fill(4, figureMap.get(4)),
                fill(5, figureMap.get(5)),
                fill(6, figureMap.get(6)),
                fill(7, figureMap.get(7)),
                fill(8, figureMap.get(8)),
                fill(9, figureMap.get(9))));
    }

    private String fill(Integer integer, GameFigure figure) {
        String cellValue = "";
        switch (figure) {
            case O -> cellValue = " O ";
            case X -> cellValue = " X ";
            case EMPTY -> cellValue = " " + integer + " ";
        }
        return cellValue;
    }

    public GameFigure winner() {
        for (String combination : COMBINATIONS){
            String[] numbers = combination.split(" ");
            GameFigure check = checkWinner(Integer.valueOf(numbers[0]),
                    Integer.valueOf(numbers[1]),
                    Integer.valueOf(numbers[2]));
            if(check != GameFigure.EMPTY){
                return check;
            }
        }
        return GameFigure.EMPTY;
    }

    private GameFigure checkWinner(Integer cellOne, Integer cellTwo, Integer cellThree) {
        if (figureMap.get(cellOne) == GameFigure.X
                && figureMap.get(cellTwo) == GameFigure.X
                && figureMap.get(cellThree) == GameFigure.X) {
            return GameFigure.X;
        }
        if (figureMap.get(cellOne) == GameFigure.O
                && figureMap.get(cellTwo) == GameFigure.O
                && figureMap.get(cellThree) == GameFigure.O) {
            return GameFigure.O;
        }
        return GameFigure.EMPTY;
    }
}