package dao;

import connectionManager.ConnectionManager;
import connectionManager.ConnectionManagerJDNCImpl;
import pojo.Group;
import pojo.House;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GroupDaoImpl implements GroupDao{
    private static ConnectionManager connectionManager = ConnectionManagerJDNCImpl.getInstanse();
    private static int HOUSE_ID_DEFAULT = -1;

    @Override
    public int addGroup(Group group) {
        try {
            int houseId = getHouseId(group.getHouse());
            if(houseId == HOUSE_ID_DEFAULT){
                return HOUSE_ID_DEFAULT;
            }

            Connection connection = connectionManager.getConnection();
            try(PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO \"group\" WHERE VALUES(DEFAULT, ?, ?)"
            )) {
                statement.setInt(1,group.getCourse());
                statement.setInt(2, group.getHouse().getId());
                ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()){
                    System.out.println(resultSet.getInt("id"));
                }
                return HOUSE_ID_DEFAULT;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return HOUSE_ID_DEFAULT;
        }
    }


    private int getHouseId(House house){
        HouseDao houseDao = new HouseDaoImpl();
        if(house == null){
            return HOUSE_ID_DEFAULT;
        }

        if(house.getId() != HOUSE_ID_DEFAULT){
            return house.getId();
        }

        house = houseDao.getHouseByName(house.getName());
        if( house == null ){
            return HOUSE_ID_DEFAULT;
        } else {
            return house.getId();
        }
    }

    @Override
    public boolean deleteGroupByID(int groupID) {
        try{
            // возможно, здесь лучше кинуть исключение, что на данную запись есть ссылки
            if(!checkStudentsOnDelete(groupID)){
                return false;
            }

            Connection connection = connectionManager.getConnection();
            try( PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM \"group\" WHERE id = ?"
            ) ) {
                statement.setInt(1, groupID);
                return statement.executeUpdate() != 0 ;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean checkStudentsOnDelete(int groupID){
        // здесь должна быть проверка на то, то в таблице студентов не осталось записей
        return true;
    }

    @Override
    public boolean updateGroup(Group group) {
        try{
            Connection connection = connectionManager.getConnection();
            try( PreparedStatement statement = connection.prepareStatement(
                    "UPDATE \"group\" SET course = ?, house = ? WHERE id = ?"
            )) {
                statement.setInt(1,group.getCourse());
                statement.setInt(2, group.getHouse().getId());
                statement.setInt(3, group.getId());
                // возможно, здесь лучше кинуть исключение, что записи с таким id не было
                return statement.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Group getGroupById(int id) {
        try {
            Connection connection = connectionManager.getConnection();
            try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM \"group\" " +
                    "JOIN houses on \"group\".house = houses.id WHERE \"group\".id = ?"
            )){
                statement.setInt(1, id);
                return getGroup(statement.executeQuery());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Group getGroup(ResultSet resultSet) throws SQLException{
        if(resultSet.next()){
            House house = new House(resultSet.getInt("house"), resultSet.getString("name"), resultSet.getInt("score"));
            Group group = new Group(resultSet.getInt("id"), resultSet.getInt("course"), house);
            return group;
        }
        return null;
    }
}
