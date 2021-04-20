//package basicSkill.redisDesign;
//
//import java.util.Random;
//
//public class SkipListDemo<T> {
//
//    public static final int SKIPLIST_MAX_LEVEL;
//    public static final Random random;
//
//    static {
//        SKIPLIST_MAX_LEVEL = 32;
//        random = new Random();
//    }
//
//    /**
//     * 跳跃表的节点
//     *
//     * @param <T>
//     */
//    static class SkipListNode<T> {
//        double score;
//        T obj;
//        SkipListNode backward;
//        int levels;             //节点的层数
//
//        SkipListLevel[] level = new SkipListLevel[levels];
//
//        /**
//         * 节点中的层
//         * forward层的前进指针
//         */
//        static class SkipListLevel {
//            SkipListNode forward;
//        }
//    }
//
//    /**
//     * 跳跃表
//     */
//    static class SkipList {
//        SkipListNode Head;    //头指针
//        SkipListNode tail;    //尾指针
//        int level;            //层数最大的那个节点的层数
//        int length;           //跳跃表目前包含节点的数量
//    }
//
//    public static SkipList skipListCreate() {
//        SkipList sl = new SkipList();
//        sl.Head = SkipListNodeCreate(SKIPLIST_MAX_LEVEL, 0.0, null);
//
//        sl.tail = null;
//        sl.level = 0;
//        sl.length = 0;
//        return sl;
//    }
//
//    public static SkipListNode SkipListNodeCreate(int levels, double score, Object obj) {
//        SkipListNode node = new SkipListNode();
//        node.levels = levels;
//        node.score = score;
//        node.obj = obj;
//        node.backward = null;
//        node.level=new SkipListNode.SkipListLevel[levels];
//        //int p;
//        //while((p=levels)>0){
//        //    node.level[--p].forward=new SkipListNode();
//        //}
//        return node;
//    }
//
//    //获取层数
//    private static int getRandomLevel() {
//        int level = 1;
//        while (random.nextInt() % 2 == 0) {
//            level++;
//        }
//        if (level > SKIPLIST_MAX_LEVEL)
//            level = SKIPLIST_MAX_LEVEL;
//        return level;
//    }
//
//    //search
//    public static SkipListNode searchNode(SkipList sl, double score) {
//        //获取sl的头节点，以及其最上层节点
//        SkipListNode head = sl.Head;
//        //从最上层的链的开头开始
//        int currentLevel = head.levels;
//        while (currentLevel > 0) {
//            SkipListNode.SkipListLevel levelPnt = head.level[currentLevel]; //获取头节点的每一层的入口
//            while (levelPnt.forward != null) {
//                //假设当前位置为p，它向右指向的节点为q，且q的值为y，将 y 与 x 作比较：
//                // x == y，输出查询成功及相关信息；
//                // x > y, 从p向右移动到q的位置；
//                // x < y, 从p向下移动一层；
//                if (levelPnt.forward.score == score)
//                    return levelPnt.forward;
//                if (levelPnt.forward.score > score)
//                    levelPnt = levelPnt.forward.level[currentLevel];
//                if (levelPnt.forward.score < score)
//                    break;
//            }
//            currentLevel--;
//        }
//        return null;
//    }
//
//    //insert
//    public static void insertNode(SkipList sl, double score, Object obj) {
//        int level = getRandomLevel();
//        SkipListNode newNode = SkipListNodeCreate(level, score, obj);
//        while ((level) > 0) {
//            SkipListNode head = sl.Head;
//            //当前新节点的层数
//            SkipListNode.SkipListLevel levelPnt = head.level[level];
//            if(levelPnt==null)
//                levelPnt=
//            while (levelPnt.forward != null) {
//                if (levelPnt.forward.score >= newNode.score) {
//                    newNode.level[level].forward = levelPnt.forward;
//                    levelPnt.forward = newNode;
//                }
//            }
//            if (levelPnt.forward == null) {
//                levelPnt.forward = newNode;
//            }
//            level--;
//        }
//    }
//
//    public static void main(String[] args) {
//        SkipList skipList = skipListCreate();
//        System.out.println("==========开始创建=========");
//        insertNode(skipList,1,"A");
//        insertNode(skipList,2,"B");
//        insertNode(skipList,3,"C");
//        insertNode(skipList,4,"D");
//        insertNode(skipList,5,"E");
//        System.out.println("===========创建结束========");
//
//        SkipListNode skipListNode = searchNode(skipList, 3);
//        System.out.println(skipListNode.obj);
//    }
//
//}
