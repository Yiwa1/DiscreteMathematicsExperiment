package TruthTableMethod;

import java.util.*;

public class UserInput {
    public static void main(String[] args) {
        System.out.println("输入不超过三种命题变元的合式公式(连接词优先级从高到低)");
        while (true) {
            //获取用户输入
            Scanner in = new Scanner(System.in);
            String formula = in.nextLine();

            //判断用户输入的合式公式是否规范

            //判断命题变元个数是否不超过三个
            Set set=new HashSet();
            List<String> sym=new LinkedList<>(Arrays.asList(symbol.FEI.sym,symbol.HEQU.sym,symbol.XIQU.sym,symbol.DANTIAOJIAN.sym,symbol.SHUANGTIAOJIAN.sym));
            int len=formula.length();
            //命题变元个数
            int cnt=0;
            for (int i = 0; i < len; i++) {
                if(!sym.contains(formula.charAt(i)+"")&&!set.contains(formula.charAt(i))){
                    set.add(formula.charAt(i));
                    cnt++;
                }
                if(cnt>3){
                    System.out.println("您输入的命题变元大于三个,系统默认退出");
                    break;
                }
            }
            Object[] BianYuan =set.toArray();
            if(cnt<=1){
                System.out.println("无法构成合式公式");
            }

            if(cnt>3){
                break;
            }

            //根据命题变元个数创建真值表
            int[][] trueTable=new int[(int)Math.pow(2,cnt)][cnt+1];
            if(cnt==2){
                trueTable[0][0]=1;
                trueTable[1][0]=1;

                trueTable[0][1]=1;
                trueTable[2][1]=1;
            }else if(cnt==3){
                trueTable[0][0]=1;
                trueTable[1][0]=1;
                trueTable[2][0]=1;
                trueTable[3][0]=1;

                trueTable[0][1]=1;
                trueTable[1][1]=1;
                trueTable[4][1]=1;
                trueTable[5][1]=1;

                trueTable[0][2]=1;
                trueTable[2][2]=1;
                trueTable[4][2]=1;
                trueTable[6][2]=1;
            }

            //计算真值表
            int row=trueTable.length;
            for(int i=0;i<row;i++){
                LinkedList<Integer> stack=new LinkedList<>();
                int[] calc = trueTable[i];
                int col= calc.length;

                    int j=0;
                    while (j<len){
                        if(sym.contains(formula.charAt(j)+"")){
                            //字符串该位置为逻辑运算符
                            String prod=formula.charAt(j)+"";
                            if(prod.equals(symbol.FEI.sym)){
                                //如果符号为逻辑非查看下一位
                                j++;
                                for (int k = 0; k < cnt; k++) {
                                    if(BianYuan[k].equals(formula.charAt(j))){
                                        stack.add(Math.abs(calc[k]-1));
                                        j++;
                                        break;
                                    }
                                }
                            }else if(prod.equals(symbol.HEQU.sym)){
                                //如果符号为合取查看下一位
                                j++;
                                for (int k = 0; k < cnt; k++) {
                                    if(BianYuan[k].equals(formula.charAt(j))){
                                        if(stack.pop()==1&&calc[k]==1){
                                            stack.add(1);
                                        }else stack.add(0);
                                        j++;
                                        break;
                                    }

                                }
                            }else if(prod.equals(symbol.XIQU.sym)){
                                //如果符号为析取查看下一位
                                j++;
                                for (int k = 0; k < cnt; k++) {
                                    if(BianYuan[k].equals(formula.charAt(j))){
                                        if(stack.pop()==1||calc[k]==1){
                                            stack.add(1);
                                        }else stack.add(0);
                                        j++;
                                        break;
                                    }
                                }
                            }else if(prod.equals(symbol.DANTIAOJIAN.sym)){
                                //如果符号为单条件查看下一位
                                j++;
                                for (int k = 0; k < cnt; k++) {
                                    if(BianYuan[k].equals(formula.charAt(j))){
                                        if(stack.peek()==0){
                                            stack.pop();
                                            stack.add(1);
                                        }else if(stack.peek()==1){
                                            if(calc[k]==0){
                                                stack.pop();
                                                stack.add(0);
                                            }
                                        }
                                        j++;
                                        break;
                                    }
                                }
                            }else if(prod.equals(symbol.SHUANGTIAOJIAN.sym)){
                                //如果符号为双条件查看下一位
                                j++;
                                for (int k = 0; k < cnt; k++) {
                                    if(BianYuan[k].equals(formula.charAt(j))){
                                        if(calc[k]==stack.pop()){
                                            stack.add(1);
                                        }else {
                                            stack.add(0);
                                        }
                                        j++;
                                        break;
                                    }
                                }
                            }
                        }else {
                            for (int k = 0; k < cnt; k++) {
                                if(BianYuan[k].equals(formula.charAt(j))){
                                    stack.add(calc[k]);
                                    j++;
                                    break;
                                }
                            }
                        }

                 }
                calc[cnt]=stack.pop();
            }


            //输出真值表
            System.out.println("该合式公式的真值表如下所示");
            Iterator iterator = set.iterator();
            while (iterator.hasNext()){
                Object tableTitle = iterator.next();
                System.out.printf("%s\t",tableTitle);
            }
            System.out.printf("%s\n",formula);

            for(int h=0;h<trueTable.length;h++){
                int[] res = trueTable[h];
                for(int i=0;i<res.length;i++){
                    System.out.printf("%d\t",res[i]);
                }
                System.out.println();
            }

            //主析取范式
            System.out.println("该合式公式的主析取范式为:");
            StringBuffer sb=new StringBuffer();
            for(int i=0;i<trueTable.length;i++){
                if(trueTable[i][cnt]==1){
                    sb.append("(");
                    for(int m=0;m<cnt;m++){
                        if(trueTable[i][m]==1){
                            if(m<cnt-1) {
                                sb.append(BianYuan[m] + "&");
                            }else {
                                sb.append(BianYuan[m]+")");
                            }
                        }else {
                            if(m<cnt-1){
                                sb.append("!"+BianYuan[m]+"&");
                            }else {
                                sb.append("!"+BianYuan[m]+")");
                            }
                        }
                    }
                    sb.append("|");
                }

            }
            if(sb.charAt(sb.length()-1)=='|') {
                String substring = sb.substring(0,sb.length()-1);
                System.out.println(substring);
            }else {
                System.out.println(sb);
            }

            //主合取范式
            System.out.println("该合式公式的主合取范式为:");
            sb=new StringBuffer();
            for(int i=0;i<trueTable.length;i++){
                if(trueTable[i][cnt]==0){
                    sb.append("(");
                    for(int m=0;m<cnt;m++){
                        if(trueTable[i][m]==0){
                            if(m<cnt-1) {
                                sb.append(BianYuan[m] + "|");
                            }else {
                                sb.append(BianYuan[m]+")");
                            }
                        }else {
                            if(m<cnt-1){
                                sb.append("!"+BianYuan[m]+"|");
                            }else {
                                sb.append("!"+BianYuan[m]+")");
                            }
                        }
                    }
                    sb.append("&");
                }

            }
            if(sb.charAt(sb.length()-1)=='&') {
                String substring = sb.substring(0,sb.length()-1);
                System.out.println(substring);
            }else {
                System.out.println(sb);
            }

            System.out.println("输入0退出计算，输入1继续计算");
            String isContinue=in.nextLine();
            if(isContinue.equals("0")){
                break;
            }else if(isContinue.equals("1")){
                System.out.println("输入不超过三种命题变元的合式公式(连接词优先级从高到低)");
            }else {
                System.out.println("输入不规范,系统默认退出");
                break;
            }

        }

    }
}
