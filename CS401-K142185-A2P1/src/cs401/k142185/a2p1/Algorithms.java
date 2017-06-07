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
public class Algorithms {
    
    Player player;
    Game game;
    int maxDepthforMinMax;
    int maxDepthforAlphaBeta;
    
    public Algorithms(int depForMinMax,int depForAlphaBeta){
        player=new Player(1,2,1,-1,1);
        maxDepthforMinMax=depForMinMax;
        maxDepthforAlphaBeta=depForAlphaBeta;
        
    }
    
    public State copy(State s){
        State newState = new State(s.row,s.column);
        for(int i=0;i<s.row;i++){
            for(int j=0;j<s.column;j++){
                newState.currentBoard[i][j]=s.currentBoard[i][j];
            }
        }
        return newState;
    }
    
    
    public int applyMinMax(State s, int turn){
        State newState;
        int actionReturn=0;
        int max=Integer.MIN_VALUE,worth;
            for(int action:Game.actions){
                
             if(s.ifActionIsApplicable(s, action)){
                newState=s.result(copy(s), action, turn);
                worth= miniMax(newState,player.switchTurn(turn),0);
                if(max < worth){
                    max=worth;
                    actionReturn=action;
                    }
                }     
            }
            return actionReturn;
    }
    
    public int miniMax(State s,int turn,int depth){
        if(terminalTest(s,turn)){
            return utility(s,turn);
        }
        
        if(depth == maxDepthforMinMax  || endState(s)){
            return  cutoffTestResult(s,turn); //- cutoffTestResult(s,Player.switchTurn(turn)));
        }
        
        State newState;
        if(turn == player.player1Symbol){       //max
            int max=Integer.MIN_VALUE,worth;
            for(int action:Game.actions){
                if(s.ifActionIsApplicable(s, action)){
                    newState=s.result(copy(s), action, turn);
                    worth= miniMax(newState,player.switchTurn(turn),depth+1);
                    if(max < worth)
                        max=worth;
                }
                
            }
            return max;
        }
        
        if(turn == player.player2Symbol){       //min
            int min=Integer.MAX_VALUE,worth;
            for(int action:Game.actions){
                if(s.ifActionIsApplicable(s, action)){
                    newState=s.result(copy(s), action, turn);
                    worth= miniMax(newState,player.switchTurn(turn),depth+1);
                    if(min > worth)
                        min=worth;
                }
                
            }
            return min;
        }
        
        return 0;
    }

    public boolean terminalTest(State s,int turn) {
        //for vertical : 1
        for(int i=0;i<s.column;i++){
            for(int j=0;j<=s.row-4;j++){
                if(checkVertical(s,i,j,j+4,turn))
                    return true;
            }
        }
        
        //for horizontal
        for(int i=0;i<s.row;i++){
            for(int j=0;j<=s.column-4;j++){
                if(checkHorizontal(s,i,j,j+4,turn))
                    return true;
            }
        }
        
        //for diagonal(left to right)
        for(int t=0;t<=2;t++){
            int j=t;
            boolean check=true;
            for(int i=0;i<s.row;i++){
                if(s.currentBoard[i][j] != turn)
                    check=false;
                
                j++;
            }
            
            if(check)
                return true;
        }
        
        //for diagonal(right to left)
        
        for(int t=0;t<=2;t++){
            int j=t;
            boolean check=true;
            for(int i=(s.row)-1;i>=0;i--){
                if(s.currentBoard[i][j] != turn)
                    check=false;
                
                j++;
            }
            
            if(check)
                return true;
        }
        
        return false;
        
    }

    private boolean checkVertical(State s,int i ,int j, int n,int target) {
        boolean check=true;
        for(int k=j;k<n;k++){
            if(s.currentBoard[k][i] != target)
                check = false;
        }
        return check; 
    }

    private boolean checkHorizontal(State s, int i, int j, int n, int target) {
        boolean check=true;
        for(int k=j;k<n;k++){
            if(s.currentBoard[i][k] != target)
                check = false;
        }
        return check;
    }

    private int utility(State s,int turn) {
        if(turn == player.player1Symbol){       //max
            return player.player1Value*100;
        }
        else {                              //min
            return player.player2Value*100;
        }
    }
    
    public int cutoffTestResult(State s,int turn) {
        //for vertical
        int count=0;
        for(int i=0;i<s.column;i++){
            for(int j=0;j<=s.row-4;j++){
                if(cutoffCheckVertical(s,i,j,j+4,turn))
                    count++;
            }
        }
        
        //for column
        for(int i=0;i<s.row;i++){
            for(int j=0;j<=s.column-4;j++){
                if(cutoffCheckHorizontal(s,i,j,j+4,turn))
                    count++;
            }
        }
        
        
        //for diagonal(left to right)
        for(int t=0;t<=2;t++){
            int j=t;
            boolean check=true;
            for(int i=0;i<s.row;i++){
                if(s.currentBoard[i][j] == Player.switchTurn(turn))
                    check=false;
                
                j++;
            }
            
            if(check)
                count++;
        }
        
        //for diagonal(right to left)
        
        for(int t=0;t<=2;t++){
            int j=t;
            boolean check=true;
            for(int i=(s.row)-1;i>=0;i--){
                if(s.currentBoard[i][j] == Player.switchTurn(turn))
                    check=false;
                
                j++;
            }
            
            if(check)
                count++;
        }
        
        
        
        return count;
        
    }

    private boolean cutoffCheckVertical(State s,int i ,int j, int n,int target) {
        boolean check=true;
        for(int k=j;k<n;k++){
            if(s.currentBoard[k][i] == player.switchTurn(target))
                check = false;
        }
        return check; 
    }

    private boolean cutoffCheckHorizontal(State s, int i, int j, int n, int target) {
        boolean check=true;
        for(int k=j;k<n;k++){
            if(s.currentBoard[i][k] == player.switchTurn(target))
                check = false;
        }
        return check;
    }

    public boolean endState(State s) {
        for(int i=0;i<s.row;i++){
            for(int j=0;j<s.column;j++){
                if(s.currentBoard[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

    
    
    public int alphaBetaPruning(State s,int turn){
        int v = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        int oldV=v;
        int actionReturn=0;
        for(int action:Game.actions){
         
            if(s.ifActionIsApplicable(s, action)){
                v = Math.max(v, minValue( s.result(copy(s), action, turn),alpha,beta,Player.switchTurn(turn),0));
                if(oldV != v){
                    actionReturn=action;
                    oldV=v;
                }
                if(v >= beta){
                    return action;
                }
                    
                alpha = Math.max(alpha, v);
                
            }
        }
        return actionReturn;
    }

    private int minValue(State s, int alpha, int beta,int turn,int depth) {
        if(terminalTest(s,turn)){
            
             return utility(s,turn);
        }
        
        if(endState(s) || depth == maxDepthforAlphaBeta){
            
            return  cutoffTestResult(s,turn); //- cutoffTestResult(s,Player.switchTurn(turn)));
        }
        
        int v = Integer.MAX_VALUE;
        for(int action:Game.actions){
         
            if(s.ifActionIsApplicable(s, action)){
                
                v= Math.min(v, maxValue(s.result(copy(s), action, turn),alpha,beta,Player.switchTurn(turn),depth+1));
                
                if(v<= alpha){
                    return v;
                }
                    
                
                beta = Math.min(beta, v);
            }
        }
        return v;
    }
    
    
    private int maxValue(State s, int alpha, int beta,int turn,int depth) {
        if(terminalTest(s,turn)){
            return utility(s,turn);
        }
        
        if(endState(s) || depth == maxDepthforAlphaBeta){
            return cutoffTestResult(s,turn); //- cutoffTestResult(s,Player.switchTurn(turn)));
        }
        
        int v = Integer.MIN_VALUE;
        for(int action:Game.actions){
         
            if(s.ifActionIsApplicable(s, action)){
                v= Math.max(v, minValue(s.result(copy(s), action, turn),alpha,beta,Player.switchTurn(turn),depth+1));
                if(v>= beta){
                    return v;
                }
                    
                
                alpha = Math.max(alpha, v);
            }
        }
        return v;
    }
}

