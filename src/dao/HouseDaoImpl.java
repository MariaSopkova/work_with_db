package dao;

import connectionManager.ConnectionManager;
import connectionManager.ConnectionManagerJDNCImpl;
import pojo.House;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HouseDaoImpl implements HouseDao {
    private static ConnectionManager connectionManager = ConnectionManagerJDNCImpl.getInstanse();
    @Override
    public House getHouseById(int id) {
        try {
            Connection connection = connectionManager.getConnection();
            try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM houses WHERE id = ?"
            )){
                statement.setInt(1, id);
                return getHouse(statement.executeQuery());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public House getHouseByName(String name)
    {
        try {
            Connection connection = connectionManager.getConnection();
            try(PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM houses WHERE name = ?"
            )){
                statement.setString(1, name);
                return getHouse(statement.executeQuery());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private House getHouse(ResultSet resultSet) throws SQLException{
        if(resultSet.next()){
            House house = new House(resultSet.getInt("id"));
            house.setName(resultSet.getString("name"));
            house.setScore(resultSet.getInt("score"));
            return house;
        }
        return null;
    }

    @Override
    public boolean addHouse(House house) {
        try {
            Connection connection = connectionManager.getConnection();
            try( PreparedStatement statement = connection.prepareStatement(
                    // название, id, результат
                    "INSERT INTO houses VALUES (?, DEFAULT, ?)"
            )) {
                statement.setString(1,house.getName());
                if (house.getScore() > 0){
                    statement.setInt(2, house.getScore());
                }
                statement.execute();
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateHouseById(House house) {
        try{
            Connection connection = connectionManager.getConnection();
            try( PreparedStatement statement = connection.prepareStatement(
                    "UPDATE houses SET name = ?, score = ? WHERE id = ?"
            )) {
                statement.setString(1,house.getName());
                statement.setInt(2, house.getScore());
                statement.setInt(3, house.getId());
                // возможно, здесь лучше кинуть исключение, что записи с таким id не было
                return statement.executeUpdate() != 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteHouseById(int houseId) {
        try{
            // возможно, здесь лучше кинуть исключение, что на данную запись есть ссылки
            if(!checkGroupOnDelete(houseId)){
                return false;
            }

            Connection connection = connectionManager.getConnection();
            try( PreparedStatement statement = connection.prepareStatement(
                    "DELETE FROM houses WHERE id = ?"
            ) ) {
                statement.setInt(1, houseId);
                return statement.executeUpdate() != 0 ;
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private boolean checkGroupOnDelete(int houseId){
        return true;
    }
}
