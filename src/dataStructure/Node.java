package dataStructure;

import player.Player;

/**
 *
 * @author larios
 */
public class Node {
    public static final short LIST = 0;
    public static final short TREE = 1;
    
    public Player player;
    public Node previous; // left
    public Node next; // right
    
    // --- Tree --- //
    public Node right;
    public Node left;
    
    public Node(Player player, Node previous, Node next, short type) {
        this.player = player;
        
        if(type == LIST) {
            this.previous = previous;
            this.next = next;
            return;
        }
        
        // --- tree --- //
        this.right = next;
        this.left = previous;
    }
}
