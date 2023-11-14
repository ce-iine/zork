package sdf;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Room {

   private final String roomId;
   private String name;
   private String description;
   private Map<String, String> directions = new HashMap<>(); // direction (N/S/E/W),roomid 
   private List<String> items = new LinkedList<>();

   public Room(String roomId) { 
    this.roomId = roomId; }

   public String getRoomId() { 
    return roomId; }

   public String getName() { 
    return name; }
   public void setName(String name) { 
    this.name = name; }

   public String getDescription() { 
    return description; }
   
   public void setDescription(String description) { 
		this.description = description; }


      // directions 
   public void setDirection(String dir, String roomId) {
      directions.put(dir, roomId); }

   public boolean isAccessible(String dir) {
      return directions.containsKey(dir); }

   public Optional<String> getRoom(String dir) {
      if (isAccessible(dir)){
         return Optional.of(directions.get(dir)); //if that direction exists for that room, something will return
// Optional.of(...) is wrapping the result of directions.get(dir) in an Optional container.
//it allows you to handle the possibility that the value might not exist (i.e., it's absent or null), 
// and it forces you to explicitly handle that case when you work with the value later on.
        } return Optional.empty(); //Returns an empty Optional instance.
   }



   public void putItem(String item) {
      this.items.add(item);
   }


   public List<String> getItems() {
      return Collections.unmodifiableList(this.items);
   }

   public Optional<String> takeItem(String item) {
      List<String> newList = this.items.stream()
         .filter(i -> !item.equals(i))
         .toList();
      if (newList.size() != items.size()) {
         items = newList;
         return Optional.of(item);
      }
      return Optional.empty();
   }
}

