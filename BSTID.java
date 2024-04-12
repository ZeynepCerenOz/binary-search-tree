import java.util.ArrayList;
import java.util.List;

public class BSTID {//binary search tree for ID
    Node root;

    public BSTID(){
        this.root=null;
    }

    public void insertByID(Student student){
        Node newNode= new Node(student);
        if(this.root==null){
            this.root=newNode;
        }
        else {
            insertByIDHelper(this.root, newNode);
        }
    }
    private void insertByIDHelper(Node current, Node newNode){

        if (newNode.ID<current.ID){
            if(current.left==null){
                current.left=newNode;
            }
            else{
                insertByIDHelper(current.left, newNode);
            }
        }
        else if(newNode.ID>current.ID){
            if(current.right==null){
                current.right=newNode;
            }
            else{
                insertByIDHelper(current.right, newNode);
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

    public static Node deleteById(Node root,int id){
        if(root==null){
            return null;
        }
        if (root.ID>id){
            root.left=deleteById(root.left,id);
        }
        else if (root.ID<id){
            root.right=deleteById(root.right,id);
        }
        else {
            if (root.left!=null && root.right!=null){
                Node tmp=root;
                Node minNodeForRight=minimumElement(tmp.right);
                root.ID=minNodeForRight.ID;
                root.right=deleteById(root.right,minNodeForRight.ID);
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

    public void findStudentByID(int id){
        if (exactSearchById(id)!=null){
            Node node=exactSearchById(id);
            System.out.println("Student Id: "+node.ID+"  Name: "+node.name+"  Surname: "+node.surname+"  Age: "+node.student.age+"  GPA: "+node.student.GPA);
        }
        else {
            System.out.println("Student is not found");
        }
    }
    public Node exactSearchById(int id){
        return exactSearchByIdHelper(root,id);
    }

    private Node exactSearchByIdHelper(Node node,int id){
        if(node==null || node.ID==id){
            return node;
        }
        if (node.ID>id){
            return exactSearchByIdHelper(node.left,id);
        }
        else {
            return exactSearchByIdHelper(node.right,id);
        }
    }

    public void findStudentIntervalById(Integer lowerBound, Integer upperBound){
        List<Node> students =intervalSearchById(lowerBound,upperBound);
        for(Node node : students){
            System.out.println("\nStudent Id: "+node.ID+"  Name: "+node.name+"  Surname: "+node.surname+"  Age: "+node.student.age+"  GPA: "+node.student.GPA);
        }
    }
    private List<Node> intervalSearchById(Integer lowerBound, Integer upperBound){
        List<Node> result =new ArrayList<>();
        if (lowerBound==null){
            lowerBound=minimumElement(this.root).ID;
        }
        if (upperBound==null){
            upperBound=maximumElement(this.root).ID;
        }
        intervalSearchByIdHelper(root, lowerBound, upperBound, result);
        return result;
    }
    private void intervalSearchByIdHelper(Node node, int lowerBound, int upperBound, List<Node> result){
        if (node==null){
            return;
        }
        if (node.ID>lowerBound){
            intervalSearchByIdHelper(node.left,lowerBound,upperBound,result);
        }
        if (node.ID>=lowerBound && node.ID<=upperBound){
            result.add(node);
        }
        if (node.ID<upperBound){
            intervalSearchByIdHelper(node.right,lowerBound,upperBound,result);
        }
    }
}
