package test;

import dao.GroupDao;
import dao.GroupDaoImpl;

public class TestGroup {
    public static void main(String[] args) {
        groupById(1);
        deleteById(6);
    }

    public static void groupById(int id){
        GroupDao groupDao = new GroupDaoImpl();
        System.out.println(groupDao.getGroupById(id));
    }

    public static void deleteById(int id){
        GroupDao groupDao = new GroupDaoImpl();
        System.out.println(groupDao.deleteGroupByID(id));
    }
}
