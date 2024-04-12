public class Node {
    int ID;
    String name;
    String surname;

    String surnameName;
    Student student;
    Node right;
    Node left;

    public Node(Student student){
        this.ID=student.ID;
        this.name=student.name;
        this.surname=student.surname;

        this.surnameName=student.surname+" "+student.name;
        this.student=student;
        this.right=null;
        this.left=null;
    }

}
