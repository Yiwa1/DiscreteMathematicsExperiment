package JudgmentOfTheBinaryRelationship;


import java.util.Random;
import java.util.Scanner;


public class UserInput {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("请输入集合中的元素个数(个数不超过5个)");
        int cnt=0;
        while ((cnt=in.nextInt())<=0||(cnt>5)){
            System.out.println("输入的元素个数不符合规则请重新输入");
        }

        //生成关系矩阵
        int[][] relation=new int[cnt][cnt];
        Random random=new Random();
        for(int i=0;i< relation.length;i++){
            int[] nums=relation[i];
            for(int j=0;j<nums.length;j++){
                //随机取值0,1
                nums[j]=Math.abs(random.nextInt())%2;
                //输出关系矩阵
                System.out.print(nums[j]+" ");
            }
            System.out.println();
        }

        //输出性质描述
        /**
         * 1.自反性(主对角线元素全为1)
         * 2.反自反性(主对角线元素全为0)
         * 3.对称性(元素关于主对角线对称)
         * 4.反对称性(元素关于主对角线对称元素不全为1)
         * 5.传递性
         * @Author B20070532
         * @DATE 2021/11/17
         * */
        //判断是否具有自反性
        boolean isCertain=true;
        for(int i=0;i< relation.length;i++){
            if(relation[i][i]!=1){
                System.out.println("不满足自反性,因为第"+(i+1)+"行"+"第"+(i+1)+"列应该为1");
                isCertain=false;
                break;
            }
        }
        if(isCertain){
            System.out.println("满足自反性");
        }

        //判断是否满足反自反性
        isCertain=true;
        for(int i=0;i< relation.length;i++){
            if(relation[i][i]!=0){
                System.out.println("不满足反自反性,因为第"+(i+1)+"行"+"第"+(i+1)+"列应该为0");
                isCertain=false;
                break;
            }
        }
        if(isCertain){
            System.out.println("满足反自反性");
        }

        //判断是否满足对称性
        isCertain=true;
        for(int i=0;i< relation.length;i++){
            for(int j=i+1;j<relation.length;j++){
                if(relation[i][j]!=relation[j][i]){
                    System.out.println("不满足对称性,因为第"+(i+1)+"行第"+(j+1)+"列与第"+(j+1)+"行第"+(i+1)+"列不同时为1");
                    isCertain=false;
                    break;
                }
            }
            if(!isCertain)break;
        }
        if (isCertain){
            System.out.println("满足对称性");
        }

        //判断是否满足反对称性
        isCertain=true;
        for(int i=0;i< relation.length;i++){
            for(int j=i+1;j<relation.length;j++){
                if(relation[i][j]*relation[j][i]==1){
                    System.out.println("不满足反对称性,因为第"+(i+1)+"行第"+(j+1)+"列与第"+(j+1)+"行第"+(i+1)+"列都为1");
                    isCertain=false;
                    break;
                }
            }
            if(!isCertain)break;
        }
        if (isCertain){
            System.out.println("满足对称性");
        }

        //判断是否满足传递性
        isCertain=true;
        for(int i=0;i< relation.length;i++){
            for(int j=0;j<relation.length;j++){
                if(relation[i][j]==1){
                    int[] ints = relation[j];
                    for (int i1 = 0; i1 < ints.length; i1++) {
                        if(ints[i1]==1){
                            if(relation[i][i1]!=1){
                                isCertain=false;
                                System.out.println("不满足传递性,因为第"+(i+1)+"行第"+(j+1)+"列为1,第"+(j+1)+"行第"+(i1+1)+"列也为1,而第"+(i+1)+"行"+"第"+(i1+1)+"列不为1");
                                break;
                            }
                        }
                    }
                }
                if(!isCertain) break;
            }
            if(!isCertain) break;
        }
        if(isCertain){
            System.out.println("满足传递性");
        }






    }
}
