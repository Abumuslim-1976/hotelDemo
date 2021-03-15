package uz.pdp.hotelDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.hotelDemo.entity.Hotel;
import uz.pdp.hotelDemo.repository.HotelRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    HotelRepository hotelRepository;

    @PostMapping
    public String createHotel(@RequestBody Hotel hotel) {
        hotelRepository.save(hotel);
        return "Hotel saqlandi";
    }

    @GetMapping
    public Page<Hotel> getHotel(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Hotel> all = hotelRepository.findAll(pageable);
        return all;
    }

    @DeleteMapping("/{id}")
    public String deleteHotel(@PathVariable Integer id) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (!byId.isPresent())
            return "hotel topilmadi";

        hotelRepository.deleteById(id);
        return "hotel o`chirildi";
    }

    @PutMapping("/{id}")
    public String editHotel(@PathVariable Integer id,@RequestBody Hotel hotel) {
        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isPresent()) {
            Hotel hotelEdited = byId.get();
            hotelEdited.setName(hotel.getName());
            hotelRepository.save(hotelEdited);
            return "hotel tahrirlandi";
        }
        return "hotel topilmadi";
    }

}
