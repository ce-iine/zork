package sdf;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class World {

    private final String file;
    private final Map<String, Room> rooms = new HashMap<>(); // map to store all room ids
    private String start;
    private Room currRoom;

    public World(String file) {
        this.file = file;
    }

    public RoomDescription start() {
        currRoom = rooms.get(start); // 'start' (look below) is stored as the id of a room, current room = start room
        System.out.printf("Current room: %s\n", currRoom.getName());
        System.out.printf("Room description:");
        return new RoomDescription(currRoom); // create new room description
    }

    public RoomDescription evaluate(String input) {
        String[] terms = input.split(" ");
        String request = terms[0];

        switch (request) {
            case Functions.GO:
                System.out.printf("going to: %s\n", terms[1]);
                if (currRoom.isAccessible(terms[1])) {
                    System.out.printf("===================\n");
                    String roomId = currRoom.getRoom(terms[1]).get();
                    // System.out.println("ids: " + rooms.keySet()); 
                    // System.out.printf("%s %b\n", roomId, rooms.containsKey(roomId));
                    currRoom = rooms.get(roomId); // set new currRoom to new room user want to go to
                    // System.out.printf("%s\n", currRoom);
                    System.out.printf("Entering room: %s\n", currRoom.getName());
                    return new RoomDescription(currRoom);
                }
                break;

            case Functions.TAKE:
                // currRoom.takeItem()

            case Functions.DROP:

                break;

            case Functions.INVENTORY:
                System.out.println(currRoom.getItems()); // user item not room item
                break;

            default:
                break;

        }
        return null;

    }

    public void setup() throws Exception {

        try (FileReader fr = new FileReader(file)) {
            BufferedReader br = new BufferedReader(fr);
            String line;
            Room room = null;

            while (null != (line = br.readLine())) {
                line = line.trim();
                String[] words = line.split(":");

                switch (words[0]) {
                    case "room":
                        String[] rooms = words[1].split(" ");
                        // System.out.printf("=======%s\n", rooms[1]);
                        room = new Room(rooms[1]);
                        saveRoom(room);
                        break;

                    case "name":
                        room.setName(words[1]);
                        break;

                    case "description":
                        String converted = words[1].replaceAll("<break>", "\n");
                        room.setDescription(converted);
                        break;

                    case "direction":
                        String[] setDir = words[1].split(" ");
                        room.setDirection(setDir[1], setDir[2]);
                        break;

                    case "items":
                        String[] toPut = words[1].split(" ");
                        String put = toPut[1];
                        // System.out.printf("===========%s\n", put);
                        room.putItem(put);
                        break;

                    case "start":
                        String[] startingRoom = words[1].split(" ");
                        start = startingRoom[1];
                        // System.out.printf("=====llllllll%s\n", start);
                        break;

                    default:

                }
            }
        }

        // for (String rId: rooms.keySet())
        //     System.out.printf(">>> %s: %s\n", rId, rooms.get(rId));

    }

    private void saveRoom(Room room) {
        rooms.put(room.getRoomId(), room);
    }

}
