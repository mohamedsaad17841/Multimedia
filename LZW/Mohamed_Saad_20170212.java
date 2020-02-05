import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Scanner;

public class Mohamed_Saad_20170212
{
    static ArrayList<String> dic = new ArrayList<String>();
    static void build()
    {
        for(int i = 0; i<128 ; i++)
        {
            dic.add((char)i + "");
        }
    }
    static String decomp(String toDecomp)
    {
        String res = "";
        String[] search =  toDecomp.split(",");
        String lastStr = "";
        for(int i = 0 ; i<search.length ; i++)
        {
            String cur = search[i];
            int curInt =  Integer.parseInt(cur);
            String str = "";
            boolean ok = false;
            if(curInt > dic.size() - 1) //Special case
            {
                str = lastStr;
                str += lastStr.charAt(0); //str = ABAA
                ok = true;
            }
            else str = dic.get(curInt);
            res += str;
            if(i > 0) {
                String toAdd = lastStr;
                if(!ok) toAdd += str.charAt(0);
                dic.add(toAdd);
            }
            lastStr = str;
        }
        return res;
    }
    public static void main(String[] args)
    {
        String str = "65,66,65,128,128,129,131,134,130,129,66,138,139,138";
        build();

        String res = decomp(str);
        System.out.println(res);
    }

}
