
package object_oriented_programming_demo_2;


public class MakePerson extends Human{

   public MakePerson 
(String name, String nationality, String sex, int feet, int inches, int age, int weight) {
super(name, nationality, sex, feet, inches, age, weight);

    }

    public void sayHello(){
        System.out.println("hello, my name is "+ name);
              
    }
}
    

