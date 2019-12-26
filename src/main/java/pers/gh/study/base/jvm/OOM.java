//-Xms20m -Xmx20m
package pers.gh.study.base.jvm;

import java.util.ArrayList;
import java.util.List;

public class OOM {
    public static void main(String[] args) {
        List<String> l = new ArrayList();
        try{
            while(true){
                l.add("a");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
