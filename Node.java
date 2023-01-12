public class Node<E>{
    private E data;
    private Node<E> left;
    private Node<E> right;
    private Node<E> parent;
  
    public Node(E data){
        this.data = data;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public Node(E data, Node<E> left, Node<E> right, Node<E> parent){
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }
  
    public E get(){
        return data;
    }
  
    public void set(E data){
        this.data = data;
    }
  
    public Node<E> getLeft(){
        return left;
    }
  
    public Node<E> getRight(){
        return right;
    }
  
    public void setLeft(Node<E> add){
        this.left = add;
    }
  
    public void setRight(Node<E> add){
        this.right = add;
    }
  
    public void setParent(Node<E> add){
        this.parent = add;
    }
  
    public Node<E> getParent(){
        return parent;
    }
  
    @Override
    public String toString(){
        return data + "";
    }
 }
 