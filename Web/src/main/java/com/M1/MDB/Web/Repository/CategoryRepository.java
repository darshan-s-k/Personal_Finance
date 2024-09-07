package com.M1.MDB.Web.Repository;

import com.M1.MDB.Web.Model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends MongoRepository<Category, String> {
    List<Category> findByUserId(String userId);
    Category findByUserIdAndName(String userId, String name);
}
