package test;

import dao.HouseDao;
import dao.HouseDaoImpl;
import pojo.House;

public class HouseTest {
    public static void main(String[] args) {
        //addHouse();
        //addHouse();
        //updateHouse(5);
        //updateHouse(6);
        //deleteHouse(5);
        //deleteHouse(12);
        getHouse(4);
        getHouse(5);
    }

    public static void addHouse(){
        HouseDao houseDao = new HouseDaoImpl();
        House house = new House("pigfarts",100);
        if(houseDao.addHouse(house)){
            System.out.println(house.getName() + ", here I come");
        }
        else{
            System.out.println(house.getName() + ", I don't come");
        }
    }

    public static void updateHouse(int id){
        HouseDao houseDao = new HouseDaoImpl();
        House house = new House(id,"pigfarts",150);
        if(houseDao.updateHouseById(house)){
            System.out.println(house.getName() + " was updated ");
        }
        else{
            System.out.println(house.getName() + " wasn't updated");
        }
    }

    public static void deleteHouse(int id){
        HouseDao houseDao = new HouseDaoImpl();
        if(houseDao.deleteHouseById(id)){
            System.out.println(" was deleted ");
        }
        else{
            System.out.println(" wasn't deleted");
        }
    }

    public static void getHouse(int id){
        HouseDao houseDao = new HouseDaoImpl();
        System.out.println(houseDao.getHouseById(id));
    }
}
