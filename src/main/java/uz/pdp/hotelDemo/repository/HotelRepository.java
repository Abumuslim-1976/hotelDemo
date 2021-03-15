package uz.pdp.hotelDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelDemo.entity.Hotel;

public interface HotelRepository extends JpaRepository<Hotel,Integer> {

}
