package basicSkill.jvm;

import java.util.*;

public class MapComputeIfAbsent {
    public static void main1(String[] args) {
        Map<String, Map<String, List<Long>>> uidShard = new HashMap<>();
        print(uidShard);
        Map<String, List<Long>> map1 = uidShard.computeIfAbsent("A", key -> new HashMap<>());
        print(uidShard);
        List<Long> list1 = map1.computeIfAbsent("a", key -> new ArrayList<>());
        print(uidShard);
        list1.add(1L);
        print(uidShard);

    }

    public static void main(String[] args) {
        //Map<String, Map<String, List<Long>>> uidShard = new HashMap<>();
        //print(uidShard);
        //Map<String, List<Long>> map1 = uidShard.computeIfAbsent("A", key -> new HashMap<>());
        //print(uidShard);
        //List<Long> list1 = map1.computeIfAbsent("a", key -> new ArrayList<>(Arrays.asList(1L,2L,3L)));
        //print(uidShard);
        //list1.add(1L);
        //print(uidShard);
        Map<String,Integer> map=new HashMap<>();
        map.put("A",1);
        map.put("B",2);
        map.put("C",3);
        map.forEach((key,value)-> System.out.println(key+":"+value));
    }

    public static void print(Map<String, Map<String, List<Long>>> map){
        System.out.println(map);
    }
}
