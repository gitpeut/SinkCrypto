package org.peut;

import java.util.Scanner;

public class Game {
    Board b;
    Crypto[] coins;
    int numberOfCryptos;

    Game(){
        System.out.println( "board dimensions: rows " + Board.rows + ", columns " + Board.columns );

        b = new Board();

        numberOfCryptos = 3;
        coins = new Crypto[ numberOfCryptos ];
        coins[0] = new Crypto( "bitcoin", b );
        coins[1] = new Crypto( "ethereum", b );
        coins[2] = new Crypto( "doge", b );

        b.show();

        gameLoop();
    }

    private Square askSquare(){
        Scanner ask = new Scanner( System.in );
        boolean answerValid = false;
        Square tmpSquare = new Square();

        b.playerShow();

        while (answerValid == false) {
            System.out.print("Field to shoot ");
            String answer = ask.nextLine();

            if ( tmpSquare.testPosition(answer)) {
                answerValid = true;
            }else{
                System.out.println("Invalid square selected, rows are A-G colums 0-6. use e.g. A5 or B1 etc.");
            }
        }

        System.out.println("Valid, row " +  (char)(tmpSquare.getRow() + 'A') + ", column " + tmpSquare.getColumn() );
        return ( b.getSquare( tmpSquare.getRow(), tmpSquare.getColumn()) );
    }

    private void checkSquare( Square square ) {
        if (square.isOccupied()) {
            Crypto tmpCrypto = square.getCrypto();

            if (tmpCrypto.isDead()) {
                System.out.println("Shooting wreckage is counter productive");
            } else {
                if (tmpCrypto.isDamaged(square)){
                    System.out.println("Crypto #" + tmpCrypto.getNumber() + " - " + tmpCrypto.getName() + " already damaged here");
                }else {
                    System.out.println("Hit! row " +  (char)(square.getRow() + 'A') + ", column " + square.getColumn()  );
                    if (tmpCrypto.hit(square)) {
                        System.out.println("Crypto #" + tmpCrypto.getNumber() + " - " + tmpCrypto.getName() + " is dead");
                        numberOfCryptos--;
                    } else {
                        System.out.println("Crypto #" + tmpCrypto.getNumber() + " - " + tmpCrypto.getName() + " is damaged");
                    }
                }
            }
        }else{
            System.out.println("Missed, nothing at " +  (char)(square.getRow() + 'A') + ", column " + square.getColumn() );
        }
    }


    public void gameLoop(){

        int tryCount = 0;

        while( numberOfCryptos > 0 ){
            tryCount++;
            Square tmpSquare;
            tmpSquare = askSquare();
            checkSquare( tmpSquare );
        }
        b.show();
        System.out.println("You sank all cryptos in " + tryCount + " tries.");
    }


}
