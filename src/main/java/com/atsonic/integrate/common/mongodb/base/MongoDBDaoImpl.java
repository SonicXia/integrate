package com.atsonic.integrate.common.mongodb.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/12/17.
 */
@Service
public class MongoDBDaoImpl implements MongoDBDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 根据主键查询一个对象
     *
     * @param id 主键（唯一id）
     * @param entityClass 对象的类型
     * @return T 对象实例
     */
    @Override
    public <T> T findById(String id, Class<T> entityClass) {
        return mongoTemplate.findById(id,entityClass);
    }

    /**
     * 根据主键查询一个对象
     *
     * @param query       查询条件
     * @param entityClass 对象的类型
     * @return 对象实例
     */
    @Override
    public <T> T findOne(Query query, Class<T> entityClass) {
        return mongoTemplate.findOne(query,entityClass);
    }

    /**
     * 通过条件查询实体(集合)
     *
     * @param query
     */
    @Override
    public <T> List<T> find(Query query,Class<T> entityClass) {
        return this.mongoTemplate.find(query, entityClass);
    }

    /**
     * 通过条件查询实体(集合)
     *
     * @param query
     */
    @Override
    public <T> List<T> find(Query query, Class<T> entityClass, String collection) {
        return this.mongoTemplate.find(query, entityClass, collection);
    }

    /**
     * 通过条件查询实体(集合)的所有元素
     *
     * @param entityClass 对象的类型
     */
    @Override
    public <T> List<T> findAll(Class<T> entityClass) {
        return mongoTemplate.findAll(entityClass);
    }

    /**
     * 通过条件查询更新数据
     *
     * @param query
     * @param update
     * @param entityClass 对象的类型
     */
    @Override
    public <T>  void update(Query query, Update update,Class<T> entityClass) {
        this.mongoTemplate.updateFirst(query,update,entityClass);
    }

    /**
     * 保存一个对象到mongodb
     *
     * @param entity
     */
    @Override
    public <T> void save(T entity) {
        this.mongoTemplate.save(entity);
    }

    /**
     * 求数据总和
     *
     * @param query
     * @param entityClass 对象的类型
     * @return
     */
    @Override
    public <T> long count(Query query,Class<T> entityClass) {
        return this.mongoTemplate.count(query,entityClass);
    }

    @Override
    public <T> long count(Query query,Class<T> entityClass, String collection) {
        return this.mongoTemplate.count(query,entityClass,collection);
    }

}
