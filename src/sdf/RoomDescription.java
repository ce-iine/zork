package sdf;

public class RoomDescription {

    public RoomDescription(Room room) { 
        
        System.out.printf("%s\n", room.getDescription());
        
        if (room.getItems() != null) {
            System.out.printf("This room has item: %s\n", room.getItems());
        } 
    }
    
}
