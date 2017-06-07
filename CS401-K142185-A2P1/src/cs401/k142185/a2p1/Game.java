/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs401.k142185.a2p1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Dell-Pc
 */
public class Game {
    private int[][] board;
    private int column;
    private int row;
    public static int[] actions;
    
    public Game(int r,int c){
        row=r;
        column=c;
        board=new int[row][column];
        File file = new File("Input.txt");

        try {

            Scanner sc = new Scanner(file);

            for(int i=0;i<row;i++){
                for(int j=0;j<column;j++){
                    board[i][j] = sc.nextInt();
                }
            }
                
            sc.close();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        actions= new int[column];
        for(int i=0;i<column;i++){
            actions[i]=i;
        }
    }
    
    public int[][] getBoard(){
        return board;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getColumn(){
        return column;
    }
    
    public void SetEmptyBoard(){
        for(int i=0;i<row;i++){
                for(int j=0;j<column;j++){
                    board[i][j] = 0;
                }
            }
    }
        
}
