package dao;

import pojo.Group;

public interface GroupDao {
    public int addGroup(Group group);
    public boolean deleteGroupByID(int groupID);
    public boolean updateGroup(Group group);
    public Group getGroupById(int id);
}
