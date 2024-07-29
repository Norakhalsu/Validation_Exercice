package com.example.event_validation.Controller;


import com.example.event_validation.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/event")
public class EventController {

    ArrayList<Event> events = new ArrayList<Event>();

    @GetMapping("/get") // display all Events (With ResponseEntity)
    public ResponseEntity getEvents() {
        return ResponseEntity.ok(events);
    }

    @PostMapping("/add") // creat new events
    public ResponseEntity addEvent( @Valid @RequestBody Event event , Errors errors) {

        if (errors.hasErrors()) {
           String message= errors.getFieldError().getDefaultMessage();
           return ResponseEntity.status(400).body(message);
        }
        events.add(event);
        return ResponseEntity.ok("Event added");
    }

    @PutMapping("/update/{index}")// update event
    public ResponseEntity updateEvent( @PathVariable int index, @Valid @RequestBody Event event , Errors errors) {
        if (errors.hasErrors()) {
            String message= errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        events.set(index, event);
        return ResponseEntity.ok("Event updated");
    }

    @DeleteMapping("/delete/{index}")// delete event
    public ResponseEntity deleteEvent( @PathVariable int index) {
        events.remove(index);
        return ResponseEntity.ok("Event deleted");
    }



////--------------------
    @PutMapping("/change/{index}")// change capacity
    public ResponseEntity changeEventCapacity(  @Valid @RequestBody String capacity ,@PathVariable int index, Errors errors ) {

        if (errors.hasErrors()) {
            String message=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
           events.get(index).setCapacity(capacity);
            return ResponseEntity.ok("Capacity changed");
    }



    @GetMapping("/search/{id}")// search for event by id
    public ResponseEntity searchEvent( @PathVariable String id) {
        for (Event event : events) {
            if (event.getId().equals(id)){
                return ResponseEntity.ok(event);
            }
        }
        return ResponseEntity.ok("Event not found");
    }

}
