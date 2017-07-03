package org.movie.test;

import java.util.ArrayList;
import java.util.List;

public class jvmdemo
{
    private static jvmdemo obj;
    protected void finalize() throws Throwable {  
        super.finalize();  
        System.out.println("finalize方法被执行!");  
        obj = this;  
    }  
    
    public static void method_3() throws InterruptedException
    {
        obj=new jvmdemo();
        obj=null;
        //进行gc 对象第一次拯救自己成功
        System.gc();
        //因为finalize方法优先级很低 等待它
        Thread.sleep(500);
        
        if (null != obj) {  
            System.out.println("1-obj还存活着！");  
        } else {  
            System.out.println("1-obj已经死了");  
        }  
        
        obj=null;
        //进行gc 对象第二次拯救自己失败
        System.gc();
        //因为finalize方法优先级很低 等待它
        Thread.sleep(500);
        
        if (null != obj) {  
            System.out.println("2-obj还存活着！");  
        } else {  
            System.out.println("2-obj已经死了");  
        }  
        
    }
    
    public static void main(String [] args) throws InterruptedException
    {
        method_3();
    }
    
    public Object instance = null;
    public static void method_2 ()
    {
        jvmdemo obj1=new jvmdemo();
        jvmdemo obj2=new jvmdemo();
        obj1.instance=obj2;
        obj2.instance=obj1;
        obj1=null;
        obj2=null;
        System.gc();
        
    }
    
    public void method_1(){
        List list=new ArrayList<int[]>();
        while(true){
          int[] tmp=new int[1000];
          list.add(tmp);
        }
    }
    
   
}
