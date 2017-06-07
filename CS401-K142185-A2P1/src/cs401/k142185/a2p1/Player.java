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
public class Player {
    int player1Symbol;  //max
    int player2Symbol;  //min
    int player1Value;
    int player2Value;
    int currentPlayerSymbol;
    
    public Player(int p1Symbol,int p2Symbol,int p1Value,int p2Value,int pcurrent){
        player1Symbol=p1Symbol;
        player2Symbol=p2Symbol;
        player1Value=p1Value;
        player2Value=p2Value;
        currentPlayerSymbol=pcurrent;        
    }
    
    public int nextTurn(){
        int temp=currentPlayerSymbol;
        if(currentPlayerSymbol==player1Symbol)
            currentPlayerSymbol=player2Symbol;
        else
            currentPlayerSymbol=player1Symbol;
        
        return temp;
                    
    }
    
    public boolean currentTurn(String s){
        if(s.equals("MAX") && currentPlayerSymbol == player1Symbol)
            return true;
        
        if(s.equals("MIN") && currentPlayerSymbol == player2Symbol)
            return true;
        
        return false;
    }
    
    public static int switchTurn(int player){
        return player == 1? 2 :1;
    }
}
