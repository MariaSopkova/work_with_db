package dao;

import pojo.House;

public interface HouseDao {
    public House getHouseById(int id);
    public House getHouseByName(String name);
    public boolean addHouse(House house);
    public boolean updateHouseById(House house);
    public boolean deleteHouseById(int houseId);
}
