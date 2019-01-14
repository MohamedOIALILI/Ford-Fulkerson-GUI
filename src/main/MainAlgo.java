package main;

import java.util.LinkedList;
	 
public class MainAlgo
{
    private int V; // Le nombre de sommets dans le graphe 
    private int graphe[][]; // La matrice valué
    public int source; // La source du graphe
    public int puits; // Le puits du graphe
   
   // Algorithme BFS pour vérifier les chemins augmentés dans le graphe
    boolean bfs(int GrapheResiduel[][], int parent[])
    {
        boolean visited[] = new boolean[this.V];
      //En premier temps, on considère que tous les sommets ne sont pas encore visités
        for(int i=0; i<this.V; ++i)
            visited[i]=false;
 
      //Ici on définit les sommets visités
        LinkedList<Integer> queue = new LinkedList<Integer>();
        queue.add(this.source);
        visited[this.source] = true;
        parent[this.source]=-1;
 
        while (queue.size()!=0)
        {
            int u = queue.poll();
 
            for (int v=0; v<this.V; v++)
            {
                if (visited[v]==false && GrapheResiduel[u][v] > 0)
                {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        
      //retourner Vrai s'il existe un chemin entre la source et le puits
        return (visited[this.puits] == true);
    }
 
    public int fordFulkerson()
    {
        int u, v;        
        int GrapheResiduel[][] =new int[this.V][this.V];
        
        for (u = 0; u < this.V; u++){
            for (v = 0; v < this.V; v++){
            	GrapheResiduel[u][v] = this.graphe[u][v];
            }
         }
        int parent[] = new int[this.V];
 
        int FlotMax = 0;  

      // Tant qu'il existe un chemin augmenté, calculer le flot
        while (this.bfs(GrapheResiduel, parent))
        {
            int CheminFlot = Integer.MAX_VALUE;
            for (v=this.puits; v!=this.source; v=parent[v])
            {
                u = parent[v];
                // Le flot minimal de chemin augmenté
                CheminFlot = Math.min(CheminFlot, GrapheResiduel[u][v]); 
            }
 
            for (v=this.puits; v != this.source; v=parent[v])
            {
                u = parent[v];
                GrapheResiduel[u][v] -= CheminFlot;
                GrapheResiduel[v][u] += CheminFlot;
            }
 
            FlotMax += CheminFlot;
        }
 
        return FlotMax;
    }
 
    public void setGraphe(int[][] GrapheUtilisateur)
    {
        int i = 0;
        int j = 0;
							          
        this.graphe = new int[this.V][this.V];	// Matrice valuée
        
        for (i = 0; i < this.V; i++){
        	for (j = 0; j < this.V; j++){
                this.graphe[i][j] = GrapheUtilisateur[i][j];
            }
        }
    }
    
    public void setSource(int s){
    	this.source = s;
    }
    public void setPuits(int t){
    	this.puits = t;
    }
    public void setSommetNumber(int v){
    	this.V = v;
    }
}
