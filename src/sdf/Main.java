package sdf;

import java.io.Console;

public class Main {

    public static void main(String[] args) throws Exception {

        World world = new World(args[0]);
        world.setup(); 

        System.out.println("Welcome to zork game");

        RoomDescription playStart = world.start(); // shows description of the starting room 

        Console cons = System.console();
        while (true) {
            String input = cons.readLine("enter > ");
            playStart = world.evaluate(input);

        }

    }

}