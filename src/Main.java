
public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the game Tic-tac-toe!");
        GameInterface gameInterface = new GameInterface();
        try {
            gameInterface.start();
        }catch (InterruptedException ex){
            System.out.println("Something wrong");
        }
    }
}