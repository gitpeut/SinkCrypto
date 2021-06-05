package org.peut;

public class Board {

    public static final int rows=7;
    public static final int columns=7;
    private int cryptoCount;
    private Square board[][];

    Board(){

        board = new Square[rows][columns];

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows ; j++) {
                board[i][j] = new Square( j,i);
            }
        }

    }

    public Square getSquare( int row, int column){
       return( board[row][column]);
    }

    public int getCryptoCount() {
        return cryptoCount;
    }

    public void setCryptoCount(int cryptoCount) {
        this.cryptoCount = cryptoCount;
    }

    public void show(){

        char  rownumber = 'A';

        for (int i = 0; i < rows; i++) {

            System.out.print( rownumber++ );

            for (int j = 0; j < columns ; j++) {
                if( board[i][j].isOccupied() ) {
                    System.out.print(" | " + board[i][j].getCrypto().getNumber());
                }else{
                    System.out.print(" | ." );
                }
            }
            System.out.println(" |");
        }
        System.out.println("    0   1   2   3   4   5   6  ");

    }

    public void playerShow(){

        char  rownumber = 'A';

        for (int i = 0; i < rows; i++) {

            System.out.print( rownumber++ );

            for (int j = 0; j < columns ; j++) {
                  Square tmpSquare = board[i][j];
                if(  tmpSquare.isOccupied() && tmpSquare.getCrypto().isDead() ) {
//                if(tmpSquare.isOccupied() && tmpSquare.getCrypto().isDamaged( tmpSquare ) ) {
                    System.out.print(" | " + tmpSquare.getCrypto().getNumber());
                }else{
                    System.out.print(" | ." );
                }
            }
            System.out.println(" |");
        }
        System.out.println("    0   1   2   3   4   5   6  ");


    }

}
