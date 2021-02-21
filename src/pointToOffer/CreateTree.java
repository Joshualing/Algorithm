package pointToOffer;

public class CreateTree {

    public static TreeNode rebuildBinaryTree(int preorder[], int inorder[]) {
        if (preorder == null || inorder == null) { //如果前序或者中序有一个是空直接返回
            return null;
        }
        // 定义构建二叉树的核心算法
        TreeNode root = rebuildBinaryTreeCore(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
        return root;
    }

    // 构建二叉树的核心算法
    public static TreeNode rebuildBinaryTreeCore(int preorder[], int startPreorder,
                                          int endPreorder, int inorder[], int startInorder, int endInorder) {
        if (startPreorder > endPreorder || startInorder > endInorder) { //停止递归的条件
            return null;
        }
        TreeNode root = new TreeNode(preorder[startPreorder]);
        for (int i = startInorder; i <= endInorder; i++) {
            if (preorder[startPreorder] == inorder[i]) {
                // 其中（i - startInorder）为中序排序中左子树结点的个数
                //左子树
                root.left = rebuildBinaryTreeCore(preorder, startPreorder + 1,
                        startPreorder + (i - startInorder), inorder,
                        startInorder, i - 1);
                //右子树
                root.right = rebuildBinaryTreeCore(preorder, (i - startInorder)
                                + startPreorder + 1, endPreorder, inorder, i + 1,
                        endInorder);

            }
        }
        return root;
    }

    public static void main(String[] args) {
        int preorder[] = {1, 2, 4, 7, 3, 5, 6, 8};
        int inorder[] = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode treeNode = rebuildBinaryTree(preorder, inorder);
        System.out.println(treeNode);
    }
}

class TreeNode {
    //数据域
    public int data;
    //左指针域
    public TreeNode left;
    //右指针域
    public TreeNode right;

    //构造带有参数的构造方法
    public TreeNode(int data) {
        this.data = data;
    }

    //重写toString方法
    public String toString() {
        return "TreeNode [data=" + data + ", left=" + left + ", right=" + right
                + "]";
    }
}