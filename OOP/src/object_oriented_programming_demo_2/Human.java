
package object_oriented_programming_demo_2;

import java.util.Random;


public abstract class Human {
    
  public String name;
   public String nationality;
   public String sex;
   public int feet; 
   public int inches; 
   public int age;
   public int weight;
   public boolean dead = false;
   public String[] jokes = {"joke1","joke2","joke3","joke4","5","6","7","8","9","10"};
  
   
 public Human 
(String name,String nationality,String sex,int feet,int inches,int age,int weight){
    this.name = name;
    this.nationality = nationality;
    this.feet = feet;
    this.inches = inches;
    this.age = age;
    this.sex = sex;
    this.weight = weight;
   
  }
  public void sayHello(){
      
      System.out.println("Hello, my name is "+name);
      
  }
  public void tellAJoke(){
      
  Random r = new Random();
  int random = r.nextInt(jokes.length);
    
      System.out.println(jokes[random]);
  }
  public void die(){
      System.out.println(name+" has left the server");
      
  while(dead == true){  
      System.out.println("    F     \n");
  } 
    
  }
  public void eat(String food){
      System.out.println("eating "+food+"\n");
      System.out.println(name+"is now full, yay!");
  }
  
}
    

