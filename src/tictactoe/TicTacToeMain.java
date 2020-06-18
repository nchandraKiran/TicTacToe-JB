package tictactoe;
import java.util.Scanner;

public class TicTacToeMain {
    static char[][] arr = new char[3][3];
    
    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter cells:");
       
        String s = scanner.nextLine();
        //to track the string index
        int sCnt = 0;
        int numX = 0; 
        int numO = 0;
        
        boolean impossible = false;
        boolean winX = false;
        boolean winO = false;
        boolean winXO = false;
        boolean draw = false;

        char prevPlayer = ' ';
        char currentPlayer = 'X';
        
        int rowCnt = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = ' ';
                 /*arr[i][j] = s.charAt(sCnt);
                if (arr[i][j] == 'X') {
                    numX++;
                } else if (arr[i][j] == 'O') {
                    numO++;
                }*/
                sCnt++;
            }
        }
                
        displayGrid();

        boolean exitDo = false;
        boolean validX = false;
        boolean validY = false;
        boolean chkNonEmpty = false;

        int inX = -1;
        int inY = -1;

        while (!exitDo) {
            boolean isNum = false;

            System.out.print("Enter the coordinates:");
            if (scanner.hasNextInt()) {
                inX = scanner.nextInt();
                isNum = true;
            }

            if (scanner.hasNextInt()) {
                inY = scanner.nextInt();
                isNum = true;
            } else {
                isNum = false;
            }

            if (!isNum) {
                System.out.print("You should enter numbers!");
                exitDo = false;
            }

            if (isNum && inX > 0 && inX < 4) {
                validX = true;
            }

            if (isNum && inY > 0 && inY < 4) {
                validY = true;
            }

            if (validX && validY) {
                int[] hkArr = getMapping(inX, inY);
                int newX = hkArr[0];
                int newY = hkArr[1];

                if (arr[newX][newY] != 'X' && arr[newX][newY] != 'O') {
                    if (prevPlayer == ' ') {
                        currentPlayer = 'X';
                    } else if (prevPlayer == 'X') {
                        currentPlayer = 'O';
                    } else {
                        currentPlayer = 'X';
                    }
                    if ( currentPlayer == 'X') {
                        numX++;
                    } else if ( currentPlayer == 'O') {
                        numO++;
                    }
                    arr[newX][newY] = currentPlayer;
                    prevPlayer = currentPlayer;
                    displayGrid();
                    //exitDo = true;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                    exitDo = false;
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
                exitDo = false;
            }

            winX = checkWinning('X');
            winO = checkWinning('O');


            if (numX - numO >= 2 || numO - numX >= 2) {
                impossible = true;
            }

            if (winO && winX) {
                impossible = true;
            }

            if (!impossible && winX && !winO) {
                winXO = true;
                System.out.println("X wins");
                exitDo = true;
            }

            if (!impossible && !winX && winO) {
                winXO = true;
                System.out.println("O wins");
                exitDo = true;
            }

            if (!impossible && !winX && !winO && numX + numO == 9) {
                draw = true;
                System.out.println("Draw");
                exitDo = true;
            }

            if (impossible) {
                System.out.println("Impossible");
                exitDo = false;
            }

            /*if (!impossible && !winXO && !draw) {
                System.out.println("Game not finished");
            }*/

        }
        
          
    }
     /*(1, 3) (2, 3) (3, 3)
(1, 2) (2, 2) (3, 2)
(1, 1) (2, 1) (3, 1)
*/

    public static void displayGrid() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j]+" ");
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------");
    }

    public static int[] getMapping(int x, int y) {
        int[] mapArr = new int[2];
        if (x == 1 && y == 3) {
            mapArr[0] = 0;
            mapArr[1] = 0;
        } else if (x == 2 && y == 3) {
            mapArr[0] = 0;
            mapArr[1] = 1;
        } else if (x == 3 && y == 3) {
            mapArr[0] = 0;
            mapArr[1] = 2;
        } else if (x == 1 && y == 2) {
            mapArr[0] = 1;
            mapArr[1] = 0;
        } else if (x == 2 && y == 2) {
            mapArr[0] = 1;
            mapArr[1] = 1;
        } else if (x == 3 && y == 2) {
            mapArr[0] = 1;
            mapArr[1] = 2;
        } else if (x == 1 && y == 1) {
            mapArr[0] = 2;
            mapArr[1] = 0;
        } else if (x == 2 && y == 1) {
            mapArr[0] = 2;
            mapArr[1] = 1;
        } else if (x == 3 && y == 1) {
            mapArr[0] = 2;
            mapArr[1] = 2;
        }

        return mapArr;
    }
    
    public static boolean checkWinning(char option) {
        boolean status = false;
        //all rows equal - 
        //row0
        if (arr[0][0] == option && arr[0][1] == option && arr[0][2] == option) {
            status = true;
        } 
        //row1
        if (arr[1][0] == option && arr[1][1] == option && arr[1][2] == option) {
            status = true;
        } 
         //row2
        if (arr[2][0] == option && arr[2][1] == option && arr[2][2] == option) {
            status = true;
        } 
        //col0
        if (arr[0][0] == option && arr[1][0] == option && arr[2][0] == option) {
            status = true;
        } 
        //col1
        if (arr[0][1] == option && arr[1][1] == option && arr[2][1] == option) {
            status = true;
        } 
        //col2
        if (arr[0][2] == option && arr[1][2] == option && arr[2][2] == option) {
            status = true;
        } 
        //diaogonal left to right
        if (arr[0][0] == option && arr[1][1] == option && arr[2][2] == option) {
            status = true;
        } 
        //diaogonal right to left
        if (arr[0][2] == option && arr[1][1] == option && arr[2][0] == option) {
            status = true;
        } 
        return status;
    }
}
