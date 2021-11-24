package PartialOrderRelation;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class UserInput {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("输入一个不超过50的整数");
        int num=0;
        while ((num=in.nextInt())<=0||num>50){
            System.out.println("输入的数不符合要求请重新输入");
            num=in.nextInt();
        }
        //获得所有因子
        List<Integer> list=new LinkedList<>();
        for(int i=1;i<=num;i++){
            if(num%i==0){
                list.add(i);
            }
        }

        //关系矩阵
        int[][] relation=new int[list.size()][list.size()];
        //将所有元素都设置为1，主对角线设置为0
        for (int i = 0; i < relation.length; i++) {
            Arrays.fill(relation[i],1);
            relation[i][i]=0;
        }
        for (int i = 0; i < relation.length; i++) {
            int[] nums=relation[i];
            for (int j = 0; j < nums.length; j++) {
                //不满足整除关系
                if(nums[j]==1&&list.get(j)%list.get(i)!=0){
                    nums[j]=0;
                }
            }
        }

        //判断是否满足盖住关系
        for (int i = 0; i < relation.length; i++) {
            int[] nums=relation[i];
            for (int j = 0; j < nums.length; j++) {
                if(nums[j]==1){
                    int[] nums2=relation[j];
                    for (int k = 0; k < nums2.length; k++) {
                        if(nums2[k]==1){
                            relation[i][k]=0;
                        }
                    }
                }
            }
        }
        System.out.print("盖住关系为: ");
        for (int i = 0; i < relation.length; i++) {
            int[] nums=relation[i];
            for (int j = 0; j < nums.length; j++) {
                if(nums[j]==1){
                    System.out.print("<"+list.get(i)+","+list.get(j)+"> ");
                }
            }
        }

        //判断是否是有补格
        boolean flag=false;
        for(int i=0;i<list.size();i++){
            flag=false;
            for(int j=0;j<list.size();j++){
                if(i==j) continue;
                if(gcd(list.get(i),list.get(j))==list.get(0)&&getLcm(list.get(i),list.get(j))==list.get(list.size()-1)){
                    flag=true;
                    break;
                }
            }
            if(flag==false){
                System.out.println("不是有补格");
                break;
            }
        }
        if(flag){
            System.out.println("是有补格");
        }

    }
    public static int gcd(int a,int b){
        if(a%b==0){
            return b;
        }else return gcd(b,a%b);
    }

    public static int getLcm(int a,int b){
        int gcd = gcd(a,b);
        int result = a*b / gcd;
        return result;
    }
}
