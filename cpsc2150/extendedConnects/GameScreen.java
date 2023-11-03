package cpsc2150.extendedConnects;
import cpsc2150.extendedConnectX.models.BoardPosition;
import cpsc2150.extendedConnectX.models.GameBoard;
import cpsc2150.extendedConnectX.models.GameBoardMem;
import cpsc2150.extendedConnectX.models.IGameBoard;

import java.util.Scanner;

/*GROUP MEMBER NAMES AND GITHUB USERNAMES SHOULD GO HERE
Jacob Colson (jccolso)
Warren Wasden (wwasden)
Steven Cabezas (scabeza)
*/

public class GameScreen {

    private static IGameBoard playerBoard;
    private static char winningChar = 'a';
    public static final int maxPlayers = 10;
    public static final int minPlayers = 2;
    public static final int maxRowsCol = 100;
    public static final int minRowsCol = 3;

    public static void printBoard() {
        System.out.println(playerBoard.toString());
    }

    public static void printWinner() {
        System.out.println("Player " + winningChar +" Won!");
    }

    public static int askPlayerForColumn() {
        int column = 0;
        //Gets column number from player.
        Scanner in = new Scanner(System.in);
        column = in.nextInt();

        return column;

    }

    public static void main(String[] args) {
        // Input validation for the amount of players
        char playerInput = 'y';
        do {
            boolean valid = true;
            int numOfPlayers = 0;
            while (valid) {
                System.out.println("How many players?");
                Scanner playerAmount = new Scanner(System.in);
                numOfPlayers = playerAmount.nextInt();
                if (numOfPlayers > maxPlayers) {
                    System.out.println("Must be 10 players or fewer");
                } else if (numOfPlayers < minPlayers) {
                    System.out.println("Must be at least 2 players");
                } else {
                    valid = false;
                }
            }
            // Assigning character values to each player
            char[] playerCharacters = new char[numOfPlayers];
            for (int i = 0; i < numOfPlayers; i++) {
                char playerChar;
                System.out.println("Enter the character to represent player " + (i + 1));
                Scanner scan = new Scanner(System.in);
                playerChar = scan.next().charAt(0);
                playerChar = Character.toUpperCase(playerChar);

                for (int j = 0; j < numOfPlayers; j++) {
                    while (playerCharacters[j] == playerChar) {
                        System.out.println(playerChar + " is already taken as a player token!");
                        System.out.println("Enter the character to represent player " + (i + 1));
                        playerChar = scan.next().charAt(0);
                        playerChar = Character.toUpperCase(playerChar);
                    }
                }

                playerCharacters[i] = playerChar;

            }

            // Determine number of rows, columns, and number of tokens in a row to win for the game
            int numOfRows = 0;
            int numOfColumns = 0;
            int numberToWin = 0;

            System.out.println("How many rows should be on the board?");
            Scanner input = new Scanner(System.in);
            numOfRows = input.nextInt();

            while (numOfRows < minRowsCol) {
                System.out.println("Amount of rows must be between 3 - 100");
                numOfRows = input.nextInt();
            }

            while (numOfRows > maxRowsCol) {
                System.out.println("Amount of rows must be between 3 - 100");
                numOfRows = input.nextInt();
            }

            System.out.println("How many columns should be on the board?");
            numOfColumns = input.nextInt();

            while (numOfColumns < minRowsCol) {
                System.out.println("Amount of columns must be between 3 - 100");
                numOfColumns = input.nextInt();
            }

            while (numOfColumns > maxRowsCol) {
                System.out.println("Amount of columns must be between 3 - 100");
                numOfColumns = input.nextInt();
            }

            System.out.println("How many in a row to win?");
            numberToWin = input.nextInt();

            if (numOfColumns > numOfRows) {
                while (numberToWin > numOfColumns) {
                    System.out.println("Number of tokens to win must be between 3 and " + numOfColumns);
                    numberToWin = input.nextInt();
                }

                while (numberToWin < minRowsCol) {
                    System.out.println("Number of tokens to win must be between 3 and " + numOfColumns);
                    numberToWin = input.nextInt();
                }
            }
            if (numOfColumns <= numOfRows) {
                while (numberToWin > numOfRows) {
                    System.out.println("Number of tokens to win must be between 3 and " + numOfRows);
                    numberToWin = input.nextInt();
                }

                while (numberToWin < minRowsCol) {
                    System.out.println("Number of tokens to win must be between 3 and " + numOfColumns);
                    numberToWin = input.nextInt();
                }
            }


            char efficiency = 'a';

            while (efficiency != 'M' && efficiency != 'F') {
                System.out.println("Would you like a Fast Game (F/f) or a Memory Efficient Game (M/m)?");
                Scanner speed = new Scanner(System.in);
                efficiency = speed.next().charAt(0);
                efficiency = Character.toUpperCase(efficiency);

                if (efficiency != 'M' && efficiency != 'F') {
                    System.out.println("Please enter F or M");
                }
            }

            if (efficiency == 'F') {
                playerBoard = new GameBoard(numOfRows, numOfColumns, numberToWin);
            }
            else {
                playerBoard = new GameBoardMem(numOfRows, numOfColumns, numberToWin);
            }

            boolean gameNotOver = true;
            while (gameNotOver) {

                for (int i = 0; i < numOfPlayers; i++) {

                    boolean validCol = false;
                    printBoard();
                    System.out.println("Player " + playerCharacters[i] + ", what column do you want to place your marker in?");
                    int col = askPlayerForColumn();

                    if (col > 0 && col < playerBoard.getNumColumns() - 1) {
                        validCol = true;
                    }

                    while (!validCol) {
                        if (col < 0) {
                            System.out.println("Column cannot be less than 0");
                            System.out.println("Player " + playerCharacters[i] + ", what column do you want to place your marker in?");
                            col = askPlayerForColumn();
                        } else if (col > playerBoard.getNumColumns() - 1) {
                            System.out.println("Column cannot be greater than " + (playerBoard.getNumColumns() - 1));
                            System.out.println("Player " + playerCharacters[i] + ", what column do you want to place your marker in?");
                            col = askPlayerForColumn();
                        }
                        //Validates that column is empty.
                        else if (!playerBoard.checkIfFree(col)) {
                            System.out.println("Column is full");
                            System.out.println("Player " + playerCharacters[i] + ", what column do you want to place your marker in?");
                            col = askPlayerForColumn();
                        } else {
                            validCol = true;
                        }
                    }

                    if (!playerBoard.checkIfFree(col)) {
                        validCol = false;
                    }

                    while (!validCol) {
                        if (col < 0) {
                            System.out.println("Column cannot be less than 0");
                            System.out.println("Player " + playerCharacters[i] + ", what column do you want to place your marker in?");
                            col = askPlayerForColumn();
                        } else if (col > playerBoard.getNumColumns() - 1) {
                            System.out.println("Column cannot be greater than " + (playerBoard.getNumColumns() - 1));
                            System.out.println("Player " + playerCharacters[i] + ", what column do you want to place your marker in?");
                            col = askPlayerForColumn();
                        }
                        //Validates that column is empty.
                        else if (!playerBoard.checkIfFree(col)) {
                            System.out.println("Column is full");
                            System.out.println("Player " + playerCharacters[i] + ", what column do you want to place your marker in?");
                            col = askPlayerForColumn();
                        } else {
                            validCol = true;
                        }
                    }

                    playerBoard.dropToken(playerCharacters[i], col);

                    if (playerBoard.checkTie()) {
                        printBoard();

                        //validates the user's choice
                        do {
                            System.out.println("Tie!");
                            System.out.println("Would you like to play again? Y/N");
                            Scanner inputChar = new Scanner(System.in);
                            playerInput = inputChar.next().charAt(0);
                            playerInput = Character.toUpperCase(playerInput);
                        } while (playerInput != 'Y' && playerInput != 'N');
                        gameNotOver = false;
                        break;
                    }

                    if (playerBoard.checkForWin(col)) {
                        printBoard();
                        winningChar = playerCharacters[i];
                        printWinner();
                        do {
                            System.out.println("Would you like to play again? Y/N");
                            Scanner inputChar = new Scanner(System.in);
                            playerInput = inputChar.next().charAt(0);
                            playerInput = Character.toUpperCase(playerInput);
                        } while (playerInput != 'Y' && playerInput != 'N');
                        gameNotOver = false;
                        break;
                    }

                }
            }
        } while (playerInput == 'Y');
    }
}
