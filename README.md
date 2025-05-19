# OOP Semester Group Project

A modular Java application featuring four optimized object-oriented components with interactive GUI linkages.

## Project Structure
​**Four Core Modules**​
1.      ​**Module A**​ - Bankmanagementsystem
2.      ​**Module B**​ - shape
3.      ​**Module C**​ - Zoo
4.      ​**Module D**​ - RestaurantManagementSystem

​**Key Integration**​
- Modules interact through unified GUI interfaces
- Cross-module method invocation (e.g. Module C's GUI triggers Module B's interface)


## Build

make sure java,javac are in your PATH, or use the full path to the java and javac commands

```bash
git clone
cd OOPend-semester-group-project
javac -cp ./src src/MainGUI/MainGUI.java -d build/
```

## Run

```bash
java.exe -cp ./build MainGUI.MainGUI
```

## ⚙️ Technical Specifications
- ​**Language**: Java 18.0.2
- ​**Testing**: JUnit 4.13.2
- ​**Paradigm**: Object-Oriented Design (Encapsulation/Inheritance/Polymorphism applied)
- ​**GUI**: Java Swing/AWT
