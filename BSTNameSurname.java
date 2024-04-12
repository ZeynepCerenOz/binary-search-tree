import java.util.ArrayList;
import java.util.List;

public class BSTNameSurname {
    Node root;

    public BSTNameSurname(){
        this.root=null;
    }

    public void insertByNameSurname(Student student){
        Node newNode=new Node(student);
        if(this.root==null){
            this.root=newNode;
        }
        else {
            insertByNameSurnameHelper(this.root, newNode);
        }
    }
    private void insertByNameSurnameHelper(Node current, Node newNode){
        if (newNode.surnameName.compareTo(current.surnameName)<0){
            if (current.left==null){
                current.left=newNode;
            }
            else {
                insertByNameSurnameHelper(current.left, newNode);
            }
        }
        else if(newNode.surnameName.compareTo(current.surnameName)>0){
            if (current.right==null){
                current.right=newNode;
            }
            else {
                insertByNameSurnameHelper(current.right, newNode);
            }
        }
    }
    private static Node minimumElement(Node root){
        if(root.left==null){
            return root;
        }
        else {
            return minimumElement(root.left);
        }
    }
    private static Node maximumElement(Node root){
        if(root.right==null){
            return root;
        }
        else {
            return maximumElement(root.right);
        }
    }

    public static Node deleteByNameSurname(Node root, String surnameName){
        if(root==null){
            return null;
        }
        if (root.surnameName.compareTo(surnameName)>0){
            root.left= deleteByNameSurname(root.left,surnameName);
        }
        else if (root.surnameName.compareTo(surnameName)<0){
            root.right= deleteByNameSurname(root.right,surnameName);
        }
        else {
            if (root.left!=null && root.right!=null){
                Node tmp=root;
                Node minNodeForRight=minimumElement(tmp.right);
                root.surnameName=minNodeForRight.surnameName;
                root.right= deleteByNameSurname(root.right,minNodeForRight.surnameName);
            }
            else if(root.left!=null){
                root=root.left;
            }
            else if (root.right!=null){
                root=root.right;
            }
            else root=null;
        }
        return root;
    }

    //first surname, then name is written to the argument
    public void findStudentByName(String surnameName){
       if(exactSearchBySurnameName(surnameName)==null) {
            System.out.println("Student is not found");
        }
        else{
            Node node= exactSearchBySurnameName(surnameName);
            System.out.println("Student Id: "+node.ID+"  Name: "+node.name+"  Surname: "+node.surname+"  Age: "+node.student.age+"  GPA: "+node.student.GPA);
        }

    }
    private Node exactSearchBySurnameName(String surnameName){
        return exactSearchBySurnameNameHelper(this.root,surnameName);
    }

    private Node exactSearchBySurnameNameHelper(Node node, String surnameName){
        if(node==null || node.surnameName.equals(surnameName)){
            return node;
        }
        if (node.surnameName.compareTo(surnameName)>0){
            return exactSearchBySurnameNameHelper(node.left,surnameName);
        }
       else {
            return exactSearchBySurnameNameHelper(node.right,surnameName);
        }

    }


    //first surname, then name is written to each argument
    public void findStudentIntervalByName(String lowerBoundSurnameName, String upperBoundSurnameName ){
        List<Node> students=intervalSearchBySurnameName(lowerBoundSurnameName,upperBoundSurnameName);
        for(Node node : students){
            System.out.println("\nStudent Id: "+node.ID+"  Name: "+node.name+"  Surname: "+node.surname+"  Age: "+node.student.age+"  GPA: "+node.student.GPA);
        }
    }

    private List<Node> intervalSearchBySurnameName(String lowerBoundSurnameName, String upperBoundSurnameName){
        List<Node> result =new ArrayList<>();
        if (lowerBoundSurnameName==null){
            lowerBoundSurnameName=minimumElement(this.root).surnameName;
        }
        if (upperBoundSurnameName==null){
            upperBoundSurnameName=maximumElement(this.root).surnameName;
        }
        intervalSearchBySurnameNameHelper(root, lowerBoundSurnameName, upperBoundSurnameName, result);
        return result;
    }
    private void intervalSearchBySurnameNameHelper(Node node, String lowerBoundSurnameName, String upperBoundSurnameName, List<Node> result){
        if (node==null){
            return;
        }
        if (node.surnameName.compareTo(lowerBoundSurnameName)>0){
            intervalSearchBySurnameNameHelper(node.left,lowerBoundSurnameName,upperBoundSurnameName,result);
        }
        if (node.surnameName.compareTo(lowerBoundSurnameName)>=0 && node.surnameName.compareTo(upperBoundSurnameName)<=0){
            result.add(node);
        }
        if (node.surnameName.compareTo(upperBoundSurnameName)<0){
            intervalSearchBySurnameNameHelper(node.right,lowerBoundSurnameName,upperBoundSurnameName,result);
        }
    }

}
