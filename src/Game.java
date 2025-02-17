import model.GameField;
import model.GameFigure;
import model.player.ComputerPlayerModel;
import model.player.Player;

import java.util.Map;

public class Game {

    private final Map<Integer, Player> players;
    private final GameField gameField;

    public Game(Player firstPlayer, Player secondPlayer){
        players = Map.of(1 , firstPlayer, 2, secondPlayer);
        gameField = new GameField();
    }

    public void startGame() throws InterruptedException {
        boolean game = true;
        int step = 1;
        while (game){
            int currentPlayerId = step % 2 == 1 ? 1 : 2;
            if (players.get(currentPlayerId).getClass() != ComputerPlayerModel.class){
                gameField.printField();
                System.out.println(players.get(currentPlayerId).getName() + " ("
                        + players.get(currentPlayerId).getFigure() + ") your turn!");
            }
            String move = players.get(currentPlayerId).move();
            boolean correctMove = gameField.playerMove(move,
                    players.get(currentPlayerId).getFigure());

            GameFigure checkWinner = gameField.winner();
            if(checkWinner != GameFigure.EMPTY){
                String winnerName = checkWinner == GameFigure.X ?
                        players.get(1).getName() : players.get(2).getName();

                System.out.println(winnerName + " (" + checkWinner.toString()
                        + ") - you are the winner!!!\n");

                Thread.sleep(2000);
                game = false;
            }

            if(step > 8 && checkWinner == GameFigure.EMPTY){
                System.out.println("The game ended in a draw!");
                game = false;
            }
            if(correctMove){
                step++;
            }
        }
    }
}
