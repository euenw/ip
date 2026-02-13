package aurafarmer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import aurafarmer.task.Task;
import aurafarmer.task.Todo;

public class TaskListTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void addTask_singleTask_sizeIncreases() {
        taskList.addTask(new Todo("read book"));
        assertEquals(1, taskList.size());
    }

    @Test
    public void addTask_multipleTasks_sizeCorrect() {
        taskList.addTask(new Todo("task 1"));
        taskList.addTask(new Todo("task 2"));
        taskList.addTask(new Todo("task 3"));
        assertEquals(3, taskList.size());
    }

    @Test
    public void deleteTask_validIndex_returnsDeletedTask() throws AuraFarmerException {
        taskList.addTask(new Todo("read book"));
        taskList.addTask(new Todo("return book"));
        Task deleted = taskList.deleteTask(0);
        assertEquals("read book", deleted.getDescription());
        assertEquals(1, taskList.size());
    }

    @Test
    public void deleteTask_negativeIndex_throwsException() {
        taskList.addTask(new Todo("read book"));
        assertThrows(AuraFarmerException.class, () -> taskList.deleteTask(-1));
    }

    @Test
    public void deleteTask_indexTooLarge_throwsException() {
        taskList.addTask(new Todo("read book"));
        assertThrows(AuraFarmerException.class, () -> taskList.deleteTask(5));
    }

    @Test
    public void deleteTask_emptyList_throwsException() {
        assertThrows(AuraFarmerException.class, () -> taskList.deleteTask(0));
    }

    @Test
    public void markTask_validIndex_taskMarkedDone() throws AuraFarmerException {
        taskList.addTask(new Todo("read book"));
        Task marked = taskList.markTask(0);
        assertTrue(marked.isDone());
    }

    @Test
    public void markTask_invalidIndex_throwsException() {
        assertThrows(AuraFarmerException.class, () -> taskList.markTask(0));
    }

    @Test
    public void unmarkTask_validIndex_taskMarkedNotDone() throws AuraFarmerException {
        taskList.addTask(new Todo("read book"));
        taskList.markTask(0);
        Task unmarked = taskList.unmarkTask(0);
        assertFalse(unmarked.isDone());
    }

    @Test
    public void unmarkTask_invalidIndex_throwsException() {
        assertThrows(AuraFarmerException.class, () -> taskList.unmarkTask(0));
    }

    @Test
    public void markTask_negativeIndex_throwsException() {
        taskList.addTask(new Todo("read book"));
        assertThrows(AuraFarmerException.class, () -> taskList.markTask(-1));
    }
}
