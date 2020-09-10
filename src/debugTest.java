import java.util.HashMap;

public class debugTest {
    public static void main(String[] args) {
        HashMap<String,String> map=new HashMap<>();
        map.put("name","李四");
        map.put("age","12");
        map.put("school","清华");

        String name=map.get("name");
        System.out.println(name);

        map.remove("school");
        System.out.println(map);
    }


}
