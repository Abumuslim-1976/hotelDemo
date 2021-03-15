package uz.pdp.hotelDemo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.hotelDemo.entity.Room;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    Page<Room> findByHotelId(Integer hotel_id, Pageable pageable);
}
