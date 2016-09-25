/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab02;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Jan Osmančík (OSM0014) VŠB-TUO FEI
 */
public class Lab02 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        line = line.replace(" ","");
        StringTokenizer st = new StringTokenizer(line,"+-*/()",true);
        ArrayList<String> data = new ArrayList<>();
        while(st.hasMoreTokens()) {
            data.add(st.nextToken());
        }
        System.out.println(solve(data));
    }
    private static int solve(List<String> data){
        int index = data.indexOf("(");
        while(index!=-1) {
            int end = compute(data,index);
            int tmp = solve(data.subList(index+1,end));
            end = compute(data,index);
            index = data.indexOf("(");
            for(int i = end; i >= index; i--) {
               data.remove(i); 
            }
            
            //data.remove(compute(data,index));
            //data.remove(data.indexOf("("));                      
            data.add(index, ""+tmp);
            index = data.indexOf("(");
        }
        index = data.indexOf("*");
        while(index!=-1) {
            int x = Integer.parseInt(data.get(index-1));
            int y = Integer.parseInt(data.get(index+1));
            data.remove(index-1);
            data.remove(index-1);
            data.remove(index-1);
            data.add(index-1, ""+(x*y));
            index=data.indexOf("*");
        }
        index = data.indexOf("/");
        while(index!=-1) {
            int x = Integer.parseInt(data.get(index-1));
            int y = Integer.parseInt(data.get(index+1));
            data.remove(index-1);
            data.remove(index-1);
            data.remove(index-1);
            data.add(index-1, ""+(x/y));
            index=data.indexOf("/");
        }
        index = data.indexOf("+");
        while(index!=-1) {
            int x = Integer.parseInt(data.get(index-1));
            int y = Integer.parseInt(data.get(index+1));
            data.remove(index-1);
            data.remove(index-1);
            data.remove(index-1);
            data.add(index-1, ""+(x+y));
            index=data.indexOf("+");
        }
        index = data.indexOf("-");
        while(index!=-1) {
            int x = Integer.parseInt(data.get(index-1));
            int y = Integer.parseInt(data.get(index+1));
            data.remove(index-1);
            data.remove(index-1);
            data.remove(index-1);
            data.add(index-1, ""+(x-y));
            index=data.indexOf("-");
        }
        return Integer.parseInt(data.get(0));
    } 
    private static int compute(List<String> data, int index) {
        int counter = 0;
        int tmpi = 0;
        for(int i = index; i < data.size(); i++) {
            if(data.get(i).equals("(")) {
                counter++;
            }
            if (data.get(i).equals(")")) {
                tmpi = i;
                counter--;
            }
            if(counter==0) {
                break;
            }
        }
        /*do {
            index = data.indexOf("(");
            if(data.indexOf("(") != -1) {
                counter++;
              //  data.subList(index+1, tmpi)
            }
            if(data.indexOf(")") != -1) {
                tmpi = data.lastIndexOf(")");
                counter--;
            }    
        } while(counter!=0);*/
        return tmpi;
    }
}