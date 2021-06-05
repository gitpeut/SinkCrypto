package org.peut;

public class Square {

    private int row;
    private int column;
    boolean occupied;
    Crypto  crypto;

    Square(){
    }

    Square(int row, int column) {
        setPosition( row, column);
    }

    Square(char row, int column) {
        setPosition( row, column);
    }

    Square( String rowcolumn ){
        setPosition( rowcolumn );
    }

    public void setPosition( int row, int column){
        if ( ((row < 0) || ( row > (Board.rows - 1) )) || ((column < 0) || (column > (Board.columns - 1))) ){
            throw new IllegalArgumentException( "arguments were: row " + row + ", column " + column + " rows and columns must be 0-6");
        }

        this.row = row;
        this.column = column;
    }

    public void setPosition(char row, int column ) {
        if ( (row >= 'a') && (row < ('a' + Board.rows)) ) {
            this.row = row - 'a';
        } else if ((row >= 'A') && (row < ('A' + Board.rows ) )) {
            this.row = row - 'A';
        } else {
            throw new IllegalArgumentException( "arguments were: row " + row + ", column " + column +
                                                " row must be A-G or a-g");
        }

        if ( column >= 0 && column < (Board.columns) ) {
            this.column = column;
        }else{
            throw new IllegalArgumentException( "arguments were: row " + row + ", column " + column +
                    " column must be 0-6");
        }

    }

    public void setPosition(String rowcolumn) {
        char    row = rowcolumn.charAt(0);
        int     column = Integer.parseInt( rowcolumn.substring(1) );

        setPosition(row, column);
    }

    public boolean testPosition(String rowcolumn) {

        try {
            char    testrow = rowcolumn.charAt(0);
            int     testcolumn = Integer.parseInt( rowcolumn.substring(1) );

            setPosition(testrow, testcolumn );
        }catch( Exception e){
            return( false );
        }

        return( true );
    }


    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Crypto getCrypto() {
        return crypto;
    }

    public void setCrypto(Crypto crypto) {
        this.crypto = crypto;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public void show(){


       System.out.print("row : " + (char)(row + 'A') + " (" + row  + "), column : " + column  );
       System.out.print(occupied?" - Occupied by ":" - Free");
        if(crypto == null){
            System.out.println();
        }else{
            System.out.println( " - " + crypto.getName() );
        }

    }

    public void playerShow(){


        System.out.print("row : " + (char)(row + 'A') + " (" + row  + "), column : " + column  );
        System.out.print(occupied?" - Occupied by ":" - Free");
        if(crypto == null){
            System.out.println("");
        }else{
            System.out.println( " - " + crypto.getNumber() );
        }

    }


}
