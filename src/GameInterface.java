import exception.WrongUsersInputException;
import model.GameFigure;
import model.MainMenuControlModel;
import model.player.ComputerPlayerModel;
import model.player.PlayerModel;

import java.util.Scanner;

public class GameInterface {

    private static final String MAIN_MENU = """
            ***** TIC-TAC-TOE *****
            1. Start game (Player vs Computer)
            2. Start game (Player vs Player)
            0. Exit
            """;

    private final Scanner scanner = new Scanner(System.in);

    public void start() throws InterruptedException {
        boolean status = true;
        while (status){
            try {
                System.out.println(MAIN_MENU);
                String usersInput = scanner.nextLine();
                MainMenuControlModel choice = MainMenuControlModel.get(usersInput);
                switch (choice){
                    case PVC -> startOnePlayerGame();
                    case PVP -> startTwoPlayersGame();
                    case EXIT -> status = false;
                }
            } catch (WrongUsersInputException ex){
                System.out.println(ex.getMessage() + "\n");
                Thread.sleep(1000);
            }
            if(!status){
                System.out.println("See ya!");
                Thread.sleep(1000);
            }
        }
    }

    private void startOnePlayerGame() throws InterruptedException {
        System.out.println("Hi! Enter your name:");
        String playerName = enterName();
        GameFigure chosenFigure = chooseFigure();


        boolean playing = true;
        while (playing){
            Game game = createPVCGame(playerName, chosenFigure);
            System.out.println("Good luck, " + playerName + "!");
            game.startGame();

            System.out.println("Press \"1\" to play one more time");
            String oneMoreGame = scanner.nextLine();
            if(!oneMoreGame.trim().equals("1")){
                playing = false;
            }
        }
    }

    private void startTwoPlayersGame() throws InterruptedException {
        System.out.println("X - player, enter your name: ");
        String firstPlayerName = enterName();
        System.out.println("O - player, enter your name: ");
        String secondPlayerName = enterName();

        boolean playing = true;
        while (playing){
            Game game = new Game(new PlayerModel(firstPlayerName, GameFigure.X, scanner),
                    new PlayerModel(secondPlayerName, GameFigure.O, scanner));

            game.startGame();
            System.out.println("Press \"1\" to play one more time");
            String oneMoreGame = scanner.nextLine();
            if(!oneMoreGame.trim().equals("1")){
                playing = false;
            }
        }
    }

    private String enterName(){
        while (true){
            String playerName = scanner.nextLine();
            if(playerName.isEmpty()){
                System.out.println("Name cannot be empty :( Write your name!");
                continue;
            } else if(playerName.isBlank()) {
                System.out.println("Name cannot be blank :( Write your name!!!");
                continue;
            }
            return playerName;
        }
    }

    private GameFigure chooseFigure(){
        while (true){
            System.out.println("""
                Choice you figure:
                1. X
                2. O
                """);
            String usersInput = scanner.nextLine();

            if(usersInput.equals("1")){
                return GameFigure.X;
            } else if(usersInput.equals("2")){
                return GameFigure.O;
            } else {
                System.out.println("No-no-no! You need to choose figure!");
            }
        }
    }

    private Game createPVCGame(String playerName, GameFigure chosenFigure){
        PlayerModel model = new PlayerModel(playerName, chosenFigure, scanner);

        if(chosenFigure == GameFigure.X){
            return new Game(model, new ComputerPlayerModel(GameFigure.O));
        } else {
            return new Game(new ComputerPlayerModel(GameFigure.X), model);
        }
    }
}