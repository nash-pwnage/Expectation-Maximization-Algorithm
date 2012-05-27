import java.io.*;
import java.util.*;
import java.lang.*;
import java.math.*;
class prob_set
{int zi1;      
    int zi2;          
    int zi3;
    double x;     
    double pzi1=0.0;  
    double pzi2=0.0;
    double pzi3=0.0;
}
public class EM {
    public static void main(String args[])
    {   double m1=5.1211;
        double m2=10.222;
        double m3=13.21;
        double v1=0;
        double v2=0;
        double v3=0;
        double pi1=0.333333;
        double pi2,pi3;
        pi2=pi3=pi1;
        double denominator=0;
        int wordcount=6000;
        int k=0;
        prob_set p[]=new prob_set[6000];
        while(k<wordcount)
        {p[k]=new prob_set();
        k++;}
        try
       {    FileReader fr=new FileReader("em_data.txt");
            Scanner scan=new Scanner(fr);
            int y=0;
            while(scan.hasNext())
            {   p[y].x=Double.parseDouble(scan.next()); 
                v1=v1+Math.pow((p[y].x-m1),2);
                v2=v2+Math.pow((p[y].x-m2),2);
                v3=v3+Math.pow((p[y].x-m3),2);
                y++;
            }
            v1=1.0;v2=1.0;v3=1.0;
            System.out.println("variance 1 :"+v1+"   variance 2 :"+v2+"   variance 3 :"+v3);  
            System.out.println("exp  ="+Math.exp(1));
        }
        catch(Exception e){}
       int iter=0;
    while(iter<250)    
    { int i=0;
        while(i<6000)
        {double g1=Math.exp(-Math.pow((p[i].x-m1),2)/2/Math.sqrt(v1));
            g1=g1/(Math.sqrt(2));
            g1=g1/(Math.sqrt(3.14159));
            g1=g1/(Math.sqrt(v1));
            p[i].pzi1=pi1*g1;  
           double g2=Math.exp(-Math.pow((p[i].x-m2),2)/2/Math.sqrt(v2));
            g2=g2/(Math.sqrt(2));
            g2=g2/(Math.sqrt(3.14159));
            g2=g2/(Math.sqrt(v2));
            p[i].pzi2=pi2*g2;  
           double g3=Math.exp(-Math.pow((p[i].x-m3),2)/2/Math.sqrt(v3));
            g3=g3/(Math.sqrt(2));
            g3=g3/(Math.sqrt(3.14159));
            g3=g3/(Math.sqrt(v3));
            p[i].pzi3=pi3*g3;
         denominator=p[i].pzi1+p[i].pzi2+p[i].pzi3;
            p[i].pzi1=p[i].pzi1/denominator;
            p[i].pzi2=p[i].pzi2/denominator;
            p[i].pzi3=p[i].pzi3/denominator;
            if(Double.isNaN(p[i].pzi1) || p[i].pzi1<0.00000000000001)
                p[i].pzi1=0.00000000000001;
            if(Double.isNaN(p[i].pzi2) || p[i].pzi2<0.00000000000001)
                p[i].pzi2=0.00000000000001;
            if(Double.isNaN(p[i].pzi3) || p[i].pzi3<0.00000000000001)
                p[i].pzi3=0.00000000000001;i++;
        }
            int h=0;
            while(h<6000)
            {
                pi1=pi1+p[h].pzi1;
                pi2=pi3+p[h].pzi2;
                pi3=pi3+p[h].pzi3;
                m1=m1+(p[h].x)*(p[h].pzi1);
                m2=m2+(p[h].x)*(p[h].pzi2);
                m3=m3+(p[h].x)*(p[h].pzi3);
                v1=v1+(Math.pow((p[h].x-m1),2))*(p[h].pzi1);
                v2=v2+(Math.pow((p[h].x-m2),2))*(p[h].pzi2);
                v3=v3+(Math.pow((p[h].x-m3),2))*(p[h].pzi3);
                h++;}
          m1=m1/pi1;
          m2=m2/pi2;
          m3=m3/pi3;
          v1=v1/pi1;
          v2=v2/pi2;
          v3=v3/pi3;
          pi1=pi1/(double)6000;
          pi2=pi2/(double)6000;
          pi3=pi3/(double)6000;
          System.out.println("Mean 1="+m1);
          System.out.println("Mean 2="+m2);
          System.out.println("Mean 3="+m3);
          System.out.println(p[0].pzi1);
          System.out.println(p[0].pzi2);
          System.out.println(p[0].pzi3);
          iter++;
    }    
    }
}