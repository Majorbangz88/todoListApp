package com.Joel.todolistapp.data.reposittories;

import com.Joel.todolistapp.data.models.TaskCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskCategoryRepository extends MongoRepository<TaskCategory, String> {
}
