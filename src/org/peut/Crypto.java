package org.peut;

import java.util.ArrayList;
import java.util.Random;

public class Crypto {

    public static final int length=3;

    private ArrayList<Square>  squares;
    private String      name;
    private int         number;
    private boolean     dead;


    Crypto( String name, Board board ){
        this.name    = name;
        this.dead    = false;
        this.squares = new ArrayList<Square>();

        placeCrypto( board );
    }

    private void placeCrypto( Board board ){
        boolean vertical = false;
        if ( (Math.random() * 10) < 5 ) vertical = true;


            boolean ready = false;
            int maxcount = 100;

            while ( ! ready && maxcount >= 0 ){
                int endrow = Board.rows;
                int endcol = Board.columns;

                if( vertical ){
                    endrow -= 2;
                }else{
                    endcol -= 2;
                }

                Random r = new Random();
                int startRow = r.nextInt( endrow);
                int startCol = r.nextInt( endcol);

                   ready = true;
                   if ( vertical ) {
                      for( int i = 0; i < length; ++i) {
                          if (board.getSquare(startRow + i, startCol).isOccupied()) {
                              ready = false;
                              break;
                          }
                      }
                   }else {
                       for (int i = 0; i < length; ++i) {
                           if (board.getSquare(startRow, startCol + i).isOccupied()) {
                               ready = false;
                               break;
                           }
                       }
                   }

                   if ( ready ) {
                       if ( vertical ) {
                           for( int i = 0; i < length; ++i) {
                                Square tmpSquare;
                                tmpSquare = board.getSquare(startRow + i, startCol);
                                tmpSquare.setOccupied( true);
                                tmpSquare.setCrypto( this);
                                squares.add( tmpSquare );
                           }
                       }else {
                           for (int i = 0; i < length; ++i) {
                               Square tmpSquare;
                               tmpSquare = board.getSquare(startRow, startCol + i);
                               tmpSquare.setOccupied( true);
                               tmpSquare.setCrypto( this);
                               squares.add( tmpSquare );
                           }
                       }
                   }else{
                       System.out.println("Crypto replace " + name);
                       maxcount--;
                   }

            }

            if ( maxcount >= 0 ){
               board.setCryptoCount( (board.getCryptoCount() + 1) );
               this.number = board.getCryptoCount();
            }else{
                throw new IllegalArgumentException( "No space found for " + name + ". Reduce number of crypto's.");
            }



     }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isDead() {
        return dead;
    }

    public boolean isDamaged( Square square){
        if ( ! squares.contains( square ) )return( true );
        return(false);
    }

    public boolean hit( Square square){
        squares.remove( square);
        if ( squares.isEmpty() ){
            dead = true;
        }
        return ( dead );
    }

    public void show(){
        System.out.println("Crypto name: " + name);
        if ( ! dead ) {
            System.out.println("Squares occupied by #" + number + " - " + name );
            for (Square a : squares) {
                a.show();
            }
        }
    }
}
