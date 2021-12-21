package FindEulerCircuit;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class UserInput {
    public static void main(String[] args) {
        //用户输入节点个数和边的条数
        int m=0,n=0;
        Scanner in=new Scanner(System.in);
        System.out.println("输入节点个数");
        while ((m=in.nextInt())<=0){
            System.out.println("输入的节点个数要大于0");
        }
        //计算最多有多少条边
        int MaxN=m*(m-1)/2;
        System.out.println("请输入边数,须小于等于"+MaxN+":");
        while ((n=in.nextInt())<0||n>MaxN){
            System.out.println("请输入边数,须小于等于"+MaxN+":");
        }

        System.out.println("随机矩阵生成中...");
        //根据用户输入的节点数和边数生成
        int[][] graph=new int[m][m];
        int cnt=n;
        Random random=new Random();
        while (cnt>0){
            int x=Math.abs(random.nextInt()%m);
            int y=Math.abs(random.nextInt()%m);
            //生成的边不满足要求时进行调整
            int record=x;
            while (x==y||graph[x][y]==1){
                x=(x+1)%m;
                if(x==record){
                    //该行不满足要求
                    y=(y+1)%m;
                }
            }
            //无向图的边
            graph[x][y]=1;
            graph[y][x]=1;
            cnt--;
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("生成完毕");

        //输出邻接矩阵
        System.out.println("图的邻接矩阵:");
        for(int i=0;i<m;i++){
            int[] nums=graph[i];
            for (int num : nums) {
                System.out.print(num+" ");
            }
            System.out.println();
        }

        //判断是否是连通图
        Graph gp=new Graph();
        boolean isConnected=gp.DFSGraph(graph);
        if(!isConnected){
            System.out.println("这个图不是连通图，所以也不是欧拉图");
            return;
        }

        //计算每个节点的度，具有欧拉回路要求每个节点的度均为偶数
        boolean isEven=true;
        for(int i=0;i< graph.length;i++){
            int[] nums=graph[i];
            int sum=Arrays.stream(nums).sum();
            if(sum%2!=0){
                System.out.println("这个图不是每个节点的度都是偶数，所以不是欧拉图");
                isEven=false;
                break;
            }
        }

        if(isEven){
            System.out.println("随机生成的图是欧拉图");
            //寻找一条欧拉回路,Fleury算法
            gp.DFSFleuryGraph(graph);
            String road = Graph.sb.substring(0, Graph.sb.length()-2);
            System.out.println("其中一条欧拉回路为:");
            System.out.println(road);
        }

    }

}
