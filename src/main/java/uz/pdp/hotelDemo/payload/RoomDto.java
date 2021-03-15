package uz.pdp.hotelDemo.payload;

import lombok.Data;

@Data
public class RoomDto {
    private Integer number;
    private Integer floor;
    private Integer size;
    private Integer hotelId;
}
