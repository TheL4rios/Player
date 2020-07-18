package dataStructure;

import player.Player;

/**
 *
 * @author larios
 */
public class CircularDoublyLinkedList {
    private Node start;
    private int size = 0;
    
    private void add(Player player, Node node) {
        if(node.next != start) {
            add(player, node.next);
        } else {
            Node newNode = new Node(player, node, node.next, Node.LIST);
            node.next = newNode;
            start.previous = newNode;
            size++;
        }
    }
    
    public void add(Player player) {
        if (start == null) {
            start = new Node(player, start, start, Node.LIST);
            size++;
            return;
        }
        
        if (size < 2) {
            Node newNode = new Node(player, start, start, Node.LIST);
            start.next = newNode;
            start.previous = newNode;
            size++;
            return;
        }
        
        add(player, this.start);
    }
    
    public void remove() {
        switch(size) {
            case 1: start = null; break;
            case 2: start.next = start; start.previous = start; break;
            default: remove(this.start); 
        }
        size--;
    }
    
    private void remove(Node node) {
        if (node.next != start) {
            remove(node.next);
        } else {
            node = node.previous;
            node.next = start;
            start.previous = node;
        }
    }
    
    public Node getStart() {
        return start;
    }
    
    public int getSize() {
        return size;
    }
}
