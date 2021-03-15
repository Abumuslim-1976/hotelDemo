package uz.pdp.hotelDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelDemo.entity.Hotel;
import uz.pdp.hotelDemo.entity.Room;
import uz.pdp.hotelDemo.payload.RoomDto;
import uz.pdp.hotelDemo.repository.HotelRepository;
import uz.pdp.hotelDemo.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    HotelRepository hotelRepository;
    @Autowired
    RoomRepository roomRepository;

    @GetMapping("/findRoom/{byHotelId}")
    public Page<Room> getRoomByHotelId(@PathVariable Integer byHotelId,@RequestParam int page) {
        Pageable pageable = PageRequest.of(page,10);
        Page<Room> getAllByHotelId = roomRepository.findByHotelId(byHotelId, pageable);
        return getAllByHotelId;
    }

    @GetMapping
    public Page<Room> getRoom(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Room> all = roomRepository.findAll(pageable);
        return all;
    }

    @PostMapping("/{hotelId}")
    public String createRoom(@PathVariable Integer hotelId, @RequestBody RoomDto roomDto) {
        Optional<Hotel> hotelById = hotelRepository.findById(hotelId);
        if (!hotelById.isPresent())
            return "hotel yo`q";

        Room room = new Room();
        room.setNumber(roomDto.getNumber());
        room.setSize(roomDto.getSize());
        room.setFloor(roomDto.getFloor());
        room.setHotel(hotelById.get());
        roomRepository.save(room);
        return "room saqlandi";
    }

    @DeleteMapping("/{id}")
    public String deleteRoom(@PathVariable Integer id) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (!optionalRoom.isPresent())
            return "room topilmadi";

        roomRepository.deleteById(id);
        return "room o`chirib tashlandi";
    }

    @PutMapping("/{id}")
    public String editedRoom(@PathVariable Integer id,@RequestBody RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findById(id);
        if (optionalRoom.isPresent()) {

            Optional<Hotel> optionalHotel = hotelRepository.findById(roomDto.getHotelId());
            if (!optionalHotel.isPresent())
                return "bunday ID lik hotel mavjud emas";

            Room room = optionalRoom.get();
            room.setNumber(roomDto.getNumber());
            room.setSize(roomDto.getSize());
            room.setFloor(roomDto.getFloor());
            room.setHotel(optionalHotel.get());
            roomRepository.save(room);
            return "room tahrirlandi";
        }
        return "room topilmadi";
    }

}
