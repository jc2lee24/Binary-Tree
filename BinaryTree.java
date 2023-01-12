import java.awt.Graphics;
import java.awt.Color;

public class BinaryTree<E extends Comparable<E>>{
   private Node<E> root;
   private int passes = 0;
   private int level = 0;
   
   public BinaryTree(){
       root = null;
   }
   public void add(E data){
       passes = 0;
       if(root == null){
           root = new Node<E>(data);
       }
       else{
           add(data, root);
       }
       if(!isBalanced()){
            balance();
       }

   }
 
   private Node<E> add(E data, Node<E> current){
       passes++;
 
       if (data.compareTo(current.get()) < 0) {
           if (current.getLeft() == null) {
               Node<E> toAdd = new Node<E>(data);
               toAdd.setParent(current);
               current.setLeft(toAdd);
               return toAdd;
 
               // System.out.println("Added");
            } 

            return add(data, current.getLeft());
       }
 
       if (data.compareTo(current.get()) > 0) {
           if (current.getRight() == null) {
               Node<E> toAdd = new Node<E>(data);
               toAdd.setParent(current);
               current.setRight(toAdd);
               return toAdd;
              
               // System.out.println("Added");
           } else {
               return add(data, current.getRight());
           }
       }
       return null;
   }
 
   public void drawMe(Graphics g, int x, int y){
        level = getLevel();
        if(root != null){
            drawMe(g, x, y, 1, root);
        }
   }
   private void drawMe(Graphics g, int x , int y, int depth, Node<E> current){
       //System.out.println("Depth: "+depth);
       g.setColor(Color.BLACK);
 
       g.drawString(current.get().toString(),x,y);
       if(current.getLeft() != null){
           int newX = x-10*((level+1)-depth);
           int newY=y+50;
           g.drawLine(x+3,y+5,newX+3,newY-15);
           drawMe(g, newX, newY, depth+1, current.getLeft());
       }
       if(current.getRight() != null){
           int newX = x+10*((level+1)-depth);
           int newY = y+50;
           g.drawLine(x+3,y+5,newX+3,newY-15);
           drawMe(g, newX, newY, depth+1, current.getRight());
       }
   }
   public E get(E data){
       passes = 0;
       return get(data, root);
   }
 
   private E get(E data, Node<E> current){
       passes++;
       if(current.get().equals(data)){
           return current.get();
       }
       else if(current.get().compareTo(data) < 0){
           if(current.getRight() != null)
               return get(data, current.getRight());
           else
               return null;
       }
       else if(current.get().compareTo(data) > 0){
           if(current.getLeft() != null)
               return get(data, current.getLeft());
 
           else
               return null;
       }
       return null;
   }
 
   public int getPasses(){
       return passes;
   }
 
   public String toString(){
       return inOrderString(root);
   }
   public String inOrderString(Node<E> current){
       String returnString = "";
       if(current != null){
           returnString += inOrderString(current.getLeft()) + " ";
           returnString += current.get() + " ";
           returnString += inOrderString(current.getRight());
       }
       return returnString;
   }
   public boolean contains(E data){
       return contains(data, root);
   }
   private boolean contains(E data, Node<E> current){
       if(data.equals(current.get())){
           return true;
       }
       else{
           if(data.compareTo(current.get()) > 0){
               if(current.getRight() == null){
                   return false;
               }
               current = current.getRight();
           }
           else if(data.compareTo(current.get()) < 0){
               if(current.getLeft() == null){
                   return false;
               }
               current = current.getLeft();
           }
           return contains(data, current);
       }
   }
 
   public void remove(E data){
       if(contains(data)){
           remove(data, root);
       }
    }

   private void remove(E data, Node<E> current){
       if(current.get().equals(data)){
            if(current.getLeft() == null && current.getRight() == null){//if has no children
               if(data.compareTo(current.getParent().get()) > 0){
                   current.getParent().setRight(null);
               }
               else if(data.compareTo(current.getParent().get()) < 0){
                   current.getParent().setLeft(null);
               }
           }
           else if(current.getRight() != null){//if has children
               E temp = getLowest(current.getRight());
               remove(temp);
               current.set(temp);
           }
       }
       else{
           //call remove passing in new current and parent
           if(data.compareTo(current.get()) > 0){
               current = current.getRight();
           }
           else if(data.compareTo(current.get()) < 0){
               current = current.getLeft();
           }
           if(current != null)
           remove(data, current);
 
           else
           return;
       }
   }

   public void clear(){
    root = null;
    }
  
   private E getLowest(Node<E> current){
       Node<E> temp = current;
       if(current.getLeft() != null){
           current = current.getLeft();
           return getLowest(current);
       }else{
           return temp.get();
       }
   }
 
   public String toStringPreOrder(){
       return toStringPreOrder(root);
   }
  
   private String toStringPreOrder(Node<E> current){
       if(current == null){
           return "";
       }else{
           return current.get().toString()+" "+toStringPreOrder(current.getLeft())+" "+toStringPreOrder(current.getRight());
       }
   }
 
   public int getHeight(){
       return getHeight(root);
   }
 
 
   private int getHeight(Node<E> current){
       if(current == null){
           return 0;
       }
       else{
           int left = getHeight(current.getLeft());
           int right = getHeight(current.getRight());
 
           if(left > right){
               return left + 1;
           }
           else{
               return right + 1;
           }
       }
   }
 
   public int getHeight(E data){
       return getHeight(getNode(data, root));
   }
 
    public int getLevel(){
       return getHeight(root) + 1;
    }
 
    private Node<E> getNode(E data, Node<E> current){
        if(current.get().equals(data)){
            return current;
        }
        else{
            if(data.compareTo(current.get()) < 0){
                return getNode(data, current.getLeft());
            }
            else{
                return getNode(data,current.getRight());
            }
        }
    }
 
    public Boolean isBalanced(){
       return isBalanced(root);
    }
 
   private Boolean isBalanced(Node<E> current){
       if(current == null){
           return true;
       }
       int left = getHeight(current.getLeft());
       int right = getHeight(current.getRight());
       int diff = Math.abs(left-right);
      
       if(diff <= 1){
           return isBalanced(current.getLeft()) && isBalanced(current.getRight());
       }
       return false;
    }

    public void balance(){
        executeRightLeft(root);
        executeLeftRight(root);
        executeRotateRight(root);
        executeRotateLeft(root);
    }

    public void executeLeftRight(Node<E> current){
        if(detectLeftRight(current)){
            leftRightRotation(current);
        }
    }

    public void executeRightLeft(Node<E> current){
        if(detectRightLeft(current)){
            rightLeftRotation(current);
        }
    }

    public void executeRotateLeft(Node<E> current){
        if(detectLeft(current)){
            rotateLeft(current);
        }
        else if(checkChild(current).equals("both")){
            executeRotateRight(current.getRight());
            executeRotateLeft(current.getLeft());
        }
        else if(checkChild(current).equals("left")){
            executeLeftRight(current);
            executeRotateRight(current.getLeft());
        }
        else if(checkChild(current).equals("right")){
            executeRightLeft(current);
            executeRotateLeft(current.getRight());
        }
        else if(checkChild(current).equals("none")){
            return;
        }
    }
    
    public void executeRotateRight(Node<E> current){
        if(detectRight(current)){
            rotateRight(current);
        }
        else if(checkChild(current).equals("both")){
            executeRotateRight(current.getRight());
            executeRotateLeft(current.getLeft());
        }
        else if(checkChild(current).equals("left")){
            executeLeftRight(current);
            executeRotateLeft(current.getRight());
        }
        else if(checkChild(current).equals("right")){
            executeRightLeft(current);
            executeRotateRight(current);
        }
    }

    public boolean detectLeft(Node<E> current){
        if(checkChild(current).equals("right")){
            if(checkChild(current.getRight()).equals("right")){
                return true;
            }
        }
        return false;
    }

    public boolean detectRight(Node<E> current){
        if(checkChild(current).equals("left")){
            if(checkChild(current.getLeft()).equals("left")){
                return true;
            }
        }
        return false;
    }

    public boolean detectRightLeft(Node<E> current){
        if(checkChild(current).equals("right")){
            if(checkChild(current.getRight()).equals("left")){
                return true;
            }
        }
        return false;
    }
   
    public boolean detectLeftRight(Node<E> current){
        if(checkChild(current).equals("left")){
            if(checkChild(current.getLeft()).equals("right")){
                return true;
            }
        }
        return false;
    }

    public void rotateRight(Node<E> current){
        Node<E> left = current.getLeft();
        current.setLeft(null);
        left.setRight(current);  
        if(current.getParent() == null){
            root = left;     
        }
        else{
            left.setParent(current.getParent());
            current.getParent().setRight(left);
        }
    }

    public void rotateLeft(Node<E> current){
        Node<E> right = current.getRight();
        current.setRight(null);
        right.setLeft(current);  
        if(current.getParent() == null){
            root = right;     
        }
        else{
            right.setParent(current.getParent());
            current.getParent().setLeft(right);
        }
    }

    public void leftRightRotation(Node<E> current){
        rotateLeft(current.getLeft());
        rotateRight(current);
    }

    public void rightLeftRotation(Node<E> current){
        rotateRight(current.getRight());
        rotateLeft(current);
    }

    public String checkChild(Node<E> current){
        if(current.getLeft() == null && current.getRight() == null){
            return "none";
        }
        else if(current.getLeft() == null && current.getRight() != null){
            return "right";
        }
        else if(current.getLeft() != null && current.getRight() == null){
            return "left";
        }
        return "both";
    }


}
 