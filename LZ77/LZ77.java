import java.util.ArrayList;
import java.util.Scanner;

public class Multimedia {
    // this function returns true when str is found in dict
    static int exist(ArrayList<String> dict,String str){
        for(int i=0 ; i<dict.size() ; i++){
            String cur=dict.get(i);
            if(cur.equals(str)){
                return i;
            }
        }
        return -1;
    }
    static ArrayList<Tag> compress(String input){
        ArrayList<String> dict=new ArrayList<>();
        ArrayList<Tag> Tags=new ArrayList<>();
        dict.add("");
        for(int i=0 ; i<input.length() ; i++){
            String curTag=input.charAt(i)+"";
            int idx=exist(dict,curTag);
            int lstIdx=0;
            while(idx!=-1) ///Exist
            {
                i++;
                if(i>=input.length()) break;
                curTag += input.charAt(i);
                lstIdx = idx;
                idx = exist(dict,curTag);
            }
            if(exist(dict,curTag) != -1){
                Tags.add(new Tag('#',exist(dict,curTag)));
            }else {
                Tags.add(new Tag(curTag.charAt(curTag.length() - 1), lstIdx));
                dict.add(curTag);
            }
        }
        for(Tag t:Tags){

            System.out.print("< "+t.index+" , "+t.nxtChar+" > ");
        }
        System.out.println();
        return Tags;
    }
    static void decompress(ArrayList<Tag> Tags){
        String res = "";
        ArrayList<String> dict = new ArrayList<>();
        dict.add("");
        for(Tag tag : Tags)
        {
            if(tag.index == 0)
            {
                res += tag.nxtChar;
                dict.add(tag.nxtChar + "");
            }
            else
            {
                String temp = "";
                temp += dict.get(tag.index);
                if(tag.nxtChar!='#')
                    temp += tag.nxtChar;
                res += temp;
                dict.add(temp);
            }
        }
        System.out.println(res);
    }
    public static void main(String[] args){
        Scanner s=new Scanner(System.in);
        String input=s.nextLine();
        ArrayList<Tag> tags=compress(input);
        decompress(tags);
    }
}
class Tag{
    char nxtChar;
    int index;

    public Tag(char nxtChar, int index) {
        this.nxtChar = nxtChar;
        this.index = index;
    }
}
