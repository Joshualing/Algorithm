package basicSkill;

import java.util.HashMap;
import java.util.Map;

public class LruCache {
    //map负责查找,构建一个虚拟的双向链表，它里面安装的是一个个的Node节点，作为数据载体

    //1.构造一个Node节点，作为数据载体
    class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        public Node() {
            this.prev = this.next = null;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }
    }

    //2.构造一个双向队列,里面安放的就是我们的Node
    class DoubleLinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        public DoubleLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        public void addHead(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        public void removeNode(Node<K, V> node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
        }

        public Node getLast() {
            return tail.prev;
        }
    }

    private int cacheSize;
    Map<Integer, Node<Integer, Integer>> map;
    DoubleLinkedList<Integer,Integer>doubleLinkedList;

    public LruCache(int cacheSize){
        this.cacheSize=cacheSize;
        map=new HashMap<>();
        doubleLinkedList=new DoubleLinkedList<>();
    }

    public int get(int key){
        if(!map.containsKey(key)){
            return -1;
        }
        Node<Integer, Integer> node = map.get(key);
        doubleLinkedList.removeNode(node);
        doubleLinkedList.addHead(node);
        return node.value;
    }

    public void put(int key, int value){
        if(map.containsKey(key)){
            Node<Integer, Integer> node = map.get(key);
            //替换值
            node.value=value;
            map.put(key,node);
            //先摘后续
            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
            return;
        }else{
            if(map.size()==cacheSize){
                //取到最后一个节点
                Node lastNode = doubleLinkedList.getLast();
                //从map中移除
                map.remove(lastNode.key);
                //从双向链表中移除
                doubleLinkedList.removeNode(lastNode);
                //放入map以及往双向链表上添加
                Node<Integer, Integer> newNode = new Node<>(key, value);
                map.put(key,newNode);
                doubleLinkedList.addHead(newNode);
                return;
            }
        }

        Node<Integer, Integer> newNode = new Node<>(key, value);
        map.put(key,newNode);
        doubleLinkedList.addHead(newNode);

    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        Node node=doubleLinkedList.head.next;
        while(node!=doubleLinkedList.tail){
            sb.append(node.value).append(" ");
            node=node.next;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LruCache lruCache=new LruCache(3);

        lruCache.put(1,1);
        lruCache.put(2,2);
        lruCache.put(3,3);
        lruCache.put(4,4);
        //System.out.println(lruCache.map.keySet());
        System.out.println(lruCache);
        lruCache.put(4,4);
        //System.out.println(lruCache.map.keySet());
        System.out.println(lruCache);
        lruCache.put(1,1);
        //System.out.println(lruCache.map.keySet());
        System.out.println(lruCache);
        lruCache.put(3,3);
        //System.out.println(lruCache.map.keySet());
        System.out.println(lruCache);
        lruCache.put(5,5);
        //System.out.println(lruCache.map.keySet());
        System.out.println(lruCache);
    }

}
