package pointToOffer;

import org.junit.Test;

//二叉树的中序遍历的下一点节点
//解题思路
//如果当前节点有右子树：右子树的最左侧节点
//如果当前节点没有右子树，且是其父亲的左节点：父节点
//如果当前节点没有右子树，且是其父亲的右节点：第一个左父节点（current.parent!=null&&current.parent.left==current）
public class NextTreeNode {
    public BinaryTreeNode Core(BinaryTreeNode node){
        if(node==null)
            return null;
        BinaryTreeNode next=null;

        //如果当前节点有右子树：右子树的最左侧节点
        if(node.right!=null){
            BinaryTreeNode nodeRight =node.right;
            while(nodeRight.left!=null){
                nodeRight=nodeRight.left;
            }
            next=nodeRight;
        }

        //如果当前节点没有右子树，且是其父亲的左节点：父节点
        if(node.right==null&&node.parent!=null&&node.parent.left==node){
            next=node.parent;
        }

        //如果当前节点没有右子树，且是其父亲的右节点：第一个左父节点（current.parent!=null&&current.parent.left==current）
        if(node.right==null&&node.parent!=null&&node.parent.right==node){
            BinaryTreeNode nodeParent =node.parent;
            while(nodeParent.parent!=null&&nodeParent.parent.left!=nodeParent){
                nodeParent=nodeParent.parent;
            }
            if(nodeParent.parent!=null&&nodeParent.parent.left==nodeParent){
                next=nodeParent.parent;
            }
        }

        return next;
    }

    @Test
    public void Context(){
        BinaryTreeNode binaryTreeNode1=new BinaryTreeNode('d');
        BinaryTreeNode binaryTreeNode2=new BinaryTreeNode('b');
        BinaryTreeNode binaryTreeNode3=new BinaryTreeNode('h');
        BinaryTreeNode binaryTreeNode4=new BinaryTreeNode('e');
        BinaryTreeNode binaryTreeNode5=new BinaryTreeNode('i');
        BinaryTreeNode binaryTreeNode6=new BinaryTreeNode('a');
        BinaryTreeNode binaryTreeNode7=new BinaryTreeNode('f');
        BinaryTreeNode binaryTreeNode8=new BinaryTreeNode('c');
        BinaryTreeNode binaryTreeNode9=new BinaryTreeNode('g');

        binaryTreeNode4.left=binaryTreeNode3;
        binaryTreeNode3.parent=binaryTreeNode4;
        binaryTreeNode4.right=binaryTreeNode5;
        binaryTreeNode5.parent=binaryTreeNode4;

        binaryTreeNode2.left=binaryTreeNode1;
        binaryTreeNode1.parent=binaryTreeNode2;
        binaryTreeNode2.right=binaryTreeNode4;
        binaryTreeNode4.parent=binaryTreeNode2;

        binaryTreeNode6.left=binaryTreeNode2;
        binaryTreeNode2.parent=binaryTreeNode6;
        binaryTreeNode6.right=binaryTreeNode8;
        binaryTreeNode8.parent=binaryTreeNode6;

        binaryTreeNode8.left=binaryTreeNode7;
        binaryTreeNode7.parent=binaryTreeNode8;
        binaryTreeNode8.right=binaryTreeNode9;
        binaryTreeNode9.parent=binaryTreeNode8;

        System.out.println(Core(binaryTreeNode3));
    }
}

class  BinaryTreeNode {
    //数据域
    public Character data;
    //左指针域
    public BinaryTreeNode left;
    //右指针域
    public BinaryTreeNode right;
    //父节点
    public BinaryTreeNode parent;

    //构造带有参数的构造方法
    public BinaryTreeNode(Character data) {
        this.data = data;
    }

    //重写toString方法
    public String toString() {
        return "BinaryTreeNode [data=" + data + ", left=" + left + ", right=" + right
                +"]";
    }
}
