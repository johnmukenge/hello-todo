package it.johnson.service;

import it.johnson.entity.Todo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Transactional //this annotation is used to make sure that all the methods in this class are transactional ()method
public class TodoService {

    // Entity manager is used to persist the entity into the database
    @PersistenceContext
    EntityManager entityManager;

    public Todo createTodo(Todo todo) {
        // Persist into db
        entityManager.persist(todo);
        return todo;
    }

    public Todo update(Todo todo) {
        entityManager.merge(todo);
        return todo;
    }

    public Todo findTodoById(Long id) {
        return entityManager.find(Todo.class, id);
    }

    public void deleteTodoById(Long id) {
        Todo todo = findTodoById(id);
        entityManager.remove(todo);
    }

    public List<Todo> getTodos() {
        return entityManager.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
    }

    public List<Todo> getTodosByTask(String task) {
        return entityManager.createQuery("SELECT t FROM Todo t WHERE t.task LIKE :task", Todo.class)
                .setParameter("task", "%" + task + "%")
                .getResultList();
    }
}
