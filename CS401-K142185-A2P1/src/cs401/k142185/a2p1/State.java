/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs401.k142185.a2p1;

/**
 *
 * @author Dell-Pc
 */
public class State {
    int[][] currentBoard;
    int column;
    int row;
    
    public State(int r, int c){
        row=r;
        column=c;
        currentBoard = new int[r][c];
    }
    public State(Game game){
        currentBoard=game.getBoard();
        column=game.getColumn();
        row=game.getRow();
        
    }
    
    public boolean ifActionIsApplicable(State s ,int action){
        if(s.currentBoard[0][action] != 0){
            
            return false;
        }
            
        else{
            return true;
        }
            
    }
    
    public State result(State s,int action,int marker){     //check
        s.currentBoard[0][action]=marker;
        return applyGravityAt(s,action);
    }

    private State applyGravityAt(State s, int action) {     //check
        int i=0;
        while(i<row-1 && s.currentBoard[i+1][action] == 0 ){
            s.currentBoard[i+1][action]=s.currentBoard[i][action];
            s.currentBoard[i][action]=0;
            i++;
        }
        return s;
    }
    
    public void printState(){
        for(int i=0;i<row;i++){
            for(int j=0;j<column;j++){
                System.out.print(currentBoard[i][j]+" ");
            }
            System.out.println("");
        }
    }
    
    
}
