import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

public class Main
{
    public static HashMap<Character,String> short_codes = new HashMap<>();
    public static HashMap<String,Character> codes_short = new HashMap<>();
    public static void codeSet(int n)
    {
        short_codes.put('A',"00");
        short_codes.put('B',"01");
        short_codes.put('C',"10");
        short_codes.put('D', "11");
        codes_short.put("00",'A');
        codes_short.put("01",'B');
        codes_short.put("10",'C');
        codes_short.put("11",'D');
    }
    public static String compress(String msg)
    {
        AH_Tree mytree = new AH_Tree();
        String code = "";
        for(int i = 0;i < msg.length();i++)
        {
            int c_idx = Collections.binarySearch(mytree.symbols,new AH_Tree.Node(msg.charAt(i)),mytree.sy_comparator);
            if(c_idx < 0)
            {
                if(!code.equals(""))
                    code += mytree.NYT.get_code();
                code += short_codes.get(msg.charAt(i));
            }
            else
                code += mytree.symbols.get(c_idx).get_code();
            mytree.add(msg.charAt(i));
        }
        return code;
    }
    public static String decompress(String code)
    {
        AH_Tree mytree = new AH_Tree();
        String msg = "",temp = "";
        int code_length = 2;
        boolean is_short = false;
        for(int  i = 0;i < code.length();i++)
        {
            if(msg.equals("") || is_short)
            {
                temp = code.substring(i,i + code_length);
                msg += codes_short.get(temp);
                mytree.add(codes_short.get(temp));
                i += code_length - 1;
                is_short = false;
                temp = "";
            }
            else
            {
                temp += code.charAt(i);
                AH_Tree.Node code_node = mytree.find_code(temp,mytree.root,0);
                if(code_node != null)
                {
                    if(code_node.get_symbol() == '*')
                        is_short = true;
                    else
                    {
                        msg += code_node.get_symbol();
                        mytree.add(code_node.get_symbol());
                        temp = "";
                    }
                }
            }
        }
        return msg;
    }
    public static void main(String args[])
    {
        try
        {
            codeSet(5);
            FileInputStream in = new FileInputStream("code.txt");
            FileOutputStream out = new FileOutputStream("code.txt");
            String input = new Scanner(System.in).next(),res = compress(input);
            System.out.println(res);
            out.write(res.getBytes());
            out.close();

            byte[] bytes =  in.readAllBytes();
            res = new String(bytes);
            res = decompress(res);
            System.out.println(res);
            out = new FileOutputStream("dec.txt");
            out.write(res.getBytes());

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
