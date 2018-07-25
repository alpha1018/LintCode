tags: Stack, Tree, Design, BST

Build iterator to print ascending elemnts of BST. Inorder traversal BST. Need to maintain O(1) time, O(h) space.

画一下, BST in order traversal. 用stack记录最小值, 放在top. O(h) space.
每次消耗TreeNode, 都看看rightNode(其实就是下一个最小的candidate), 并且一条龙stack叠上rightNode所有的left子孙.

#### Stack
- 用O(h)空间的做法：
- 理解binary search tree inorder traversal的规律：
- 先找left.left.left ....left 到底，这里是加进stack; 然后考虑parent,然后再right.

#### Details 例如这题:
- stack里面top，也就是tree最左下角的node先考虑,取名rst.
- 其实这个rst拿出来以后, 它也同时是最底层left null的parent，算考虑过了最底层的parent。
- 最后就考虑最底层的parent.right, 也就是rst.right.
- 注意: next()其实有个while loop, 很可能是O(h).题目要求average O(1),所以也是okay的.


#### 用O(1)空间的做法: 不存stack, 时刻update current为最小值。
- 找下一个最小值,如果current有right child: 和用stack时的iteration类似,那么再找一遍current.right的left-most child,就是最小值了。
- 如果current没有right child: 那么就要找current node的右上parent, search in BinarySearchTree from root.
- 注意:
- 一定要确保找到的parent满足parent.left == current.
- 反而言之，如果current是parent的 right child, 那么下一轮就会重新process parent。
- 但是有错:binary search tree里面parent是小于right child的，也就是在之前一步肯定visit过，如此便会死循环。


```
/*
Design an iterator over a binary search tree with the following rules:
Elements are visited in ascending order (i.e. an in-order traversal)
next() and hasNext() queries run in O(1) time in average.
Example
For the following binary search tree, in-order traversal by using iterator is [1, 6, 10, 11, 12]
   10
 /    \
1      11
 \       \
  6       12
Challenge
Extra memory usage O(h), h is the height of the tree.
Super Star: Extra memory usage O(1)
Tags Expand 
Binary Tree LintCode Copyright Non Recursion Binary Search Tree Google LinkedIn Facebook
*/


/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 * Example of iterate a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    do something for node
 * } 
 */

public class BSTIterator {
    final Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode node = root;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode rst = stack.pop();
        if (rst.right != null) {
            TreeNode node = rst.right;
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
        }
        return rst.val;
    }
}
