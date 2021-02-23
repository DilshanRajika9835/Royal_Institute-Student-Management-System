package dao;/*@author:Dilshan Rajika Withanachchi*/

import entity.SuperEntity;

import java.io.Serializable;
import java.util.List;

public interface SuperDAO<Entity extends SuperEntity,ID extends Serializable> {
    public  boolean add(Entity entity)throws Exception;
    public  boolean delete(ID id)throws Exception;
    public  boolean update(Entity entity)throws Exception;
    public  Entity find(ID id)throws Exception;
    public List<Entity> findall()throws Exception;
}
