package FindEulerCircuit;

import java.util.Arrays;

public class Graph {
    int ConnectedComponent=0;
    public static StringBuffer sb=new StringBuffer();
    public void DFS(int[][] graph,int[] visited,int v){
        //将该节点标记为已访问
        visited[v]=1;
        //访问相邻节点
        for(int i=0;i< graph.length;i++){
            if(graph[v][i]==1&&v!=i&&visited[i]==0){
                DFS(graph,visited,i);
            }
        }
    }

    public boolean DFSGraph(int[][] graph){
        boolean flag=false;
        int[] visited=new int[graph.length];
        for(int i=0;i< graph.length;i++) {
            //连通分量自增
            ConnectedComponent++;
            DFS(graph, visited, i);
            int sum = Arrays.stream(visited).sum();
            if (sum == graph.length) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void DFSFleury(int[][] graph,int v){
        sb.append(v+"->");
        //记录一条边以备必须走桥
        boolean flag=false;
        int record=-1;
        //计算连通分量
        Graph before=new Graph();
        before.DFSGraph(graph);
        //计算边数
       int cnt= Arrays.stream(graph[v]).sum();

        for(int i=0;i< graph.length;i++){
            if(graph[v][i]==1){
                if(!flag) {
                    record = i;
                    flag=true;
                }
                //模拟将此边删除
                graph[v][i]=0;
                graph[i][v]=0;
                //计算连通分量
                Graph after=new Graph();
                after.DFSGraph(graph);
                if(after.ConnectedComponent> before.ConnectedComponent) {
                    //连通分量增加，该边为桥
                    //回溯
                    graph[v][i] = 1;
                    graph[i][v] = 1;
                    cnt--;
                    if(cnt==0){
                        //必须走桥
                        graph[v][record] = 0;
                        graph[record][v] = 0;
                        DFSFleury(graph, record);
                        break;
                    }
                }else{
                    //该边不为桥
                    DFSFleury(graph,i);
                    break;
                }
            }
        }





    }

    public void DFSFleuryGraph(int[][] graph){
        //任意选择一个开始
        DFSFleury(graph,0);
    }
}
