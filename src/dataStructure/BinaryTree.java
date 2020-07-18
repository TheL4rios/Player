package dataStructure;

import java.util.ArrayList;
import player.Player;

/**
 *
 * @author larios
 */
public class BinaryTree {
    private Node root;
    private final ArrayList<String> names;
    
    public BinaryTree() {
        names = new ArrayList<>();
    }
    
    private void add(Player player, Node root) {
        if(root.player.getName().compareToIgnoreCase(player.getName()) < 0) {
            if (root.right == null) {
                root.right = new Node(player, null, null, Node.TREE);
            } else {
                add(player, root.right);
            }
            
            return;
        }
        
        if (root.left == null) {
            root.left = new Node(player, null, null, Node.TREE);
        } else {
            add(player, root.left);
        }
    }
    
    private Player find(String name, Node root) {
        int compare = name.compareToIgnoreCase(root.player.getName());
        
        if (compare > 0) {
            return find(name, root.right);
        } else if (compare < 0) {
            return find(name, root.left);
        } 
        
        return root.player;
    }
    
    public Player find(String name) {
        if (root == null) {
            return null;
        }
        
        return find(name, this.root);
    }
    
    public void add(Player player) {
        if(root == null) {
            root = new Node(player, null, null, Node.TREE);
            return;
        }
        
        add(player, this.root);
    }
    
    public void showTree() {
        showTree(this.root);
    }
    
    private void showTree(Node node) {
        if (node == null) {
            return;
        }

        showTree(node.left);
        System.out.print(node.player.getName() + "\n");
        names.add(node.player.getName());
        showTree(node.right);
    }
    
    public ArrayList<String> getNames() {
        return names;
    }
}
