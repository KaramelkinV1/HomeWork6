package ru.netology.javaqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TasksTest {

    // SimpleTask
    @Test
    public void simpleTaskShouldMatchWhenQueryInTitle() {
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");
        Assertions.assertTrue(task.matches("родител"));
    }

    @Test
    public void simpleTaskShouldNotMatchWhenQueryNotInTitle() {
        SimpleTask task = new SimpleTask(5, "Позвонить родителям");
        Assertions.assertFalse(task.matches("Хлеб"));
    }

    // Epic
    @Test
    public void epicShouldMatchWhenQueryInFirstSubtask() {
        Epic epic = new Epic(55, new String[]{"Молоко", "Яйца", "Хлеб"});
        Assertions.assertTrue(epic.matches("Молоко"));
    }

    @Test
    public void epicShouldMatchWhenQueryInLastSubtask() {
        Epic epic = new Epic(55, new String[]{"Молоко", "Яйца", "Хлеб"});
        Assertions.assertTrue(epic.matches("Хлеб"));
    }

    @Test
    public void epicShouldNotMatchWhenQueryNotInSubtasks() {
        Epic epic = new Epic(55, new String[]{"Молоко", "Яйца", "Хлеб"});
        Assertions.assertFalse(epic.matches("Сыр"));
    }

    @Test
    public void epicShouldNotMatchWhenNoSubtasks() {
        Epic epic = new Epic(55, new String[0]);
        Assertions.assertFalse(epic.matches("Молоко"));
    }

    // Meeting
    @Test
    public void meetingShouldMatchWhenQueryInTopic() {
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");
        Assertions.assertTrue(meeting.matches("версии"));
    }

    @Test
    public void meetingShouldMatchWhenQueryInProject() {
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");
        Assertions.assertTrue(meeting.matches("НетоБанка"));
    }

    @Test
    public void meetingShouldNotMatchWhenQueryOnlyInStart() {
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");
        Assertions.assertFalse(meeting.matches("вторник"));
    }

    @Test
    public void meetingShouldNotMatchWhenQueryNowhere() {
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");
        Assertions.assertFalse(meeting.matches("Сыр"));
    }

    // Task
    @Test
    public void baseTaskShouldNeverMatch() {
        Assertions.assertFalse(new Task(1).matches("что угодно"));
    }

    @Test
    public void tasksWithSameIdShouldBeEqual() {
        Assertions.assertEquals(new Task(1), new Task(1));
        Assertions.assertEquals(new Task(1).hashCode(), new Task(1).hashCode());
    }

    @Test
    public void gettersShouldReturnValues() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить");
        String[] subtasks = {"Молоко"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(555, "Тема", "Проект", "Старт");

        Assertions.assertEquals(5, simpleTask.getId());
        Assertions.assertEquals("Позвонить", simpleTask.getTitle());
        Assertions.assertArrayEquals(subtasks, epic.getSubtasks());
        Assertions.assertEquals("Тема", meeting.getTopic());
        Assertions.assertEquals("Проект", meeting.getProject());
        Assertions.assertEquals("Старт", meeting.getStart());
    }
    @Test
    public void shouldBeEqualToItself() {
        Task task = new Task(1);
        Assertions.assertTrue(task.equals(task));
    }

    @Test
    public void shouldNotBeEqualToNull() {
        Task task = new Task(1);
        Assertions.assertFalse(task.equals(null));
    }

    @Test
    public void shouldNotBeEqualToObjectOfAnotherClass() {
        Task task = new Task(1);
        SimpleTask simpleTask = new SimpleTask(1, "Позвонить");
        Assertions.assertFalse(task.equals(simpleTask));
        Assertions.assertFalse(task.equals("строка"));
    }

    @Test
    public void shouldNotBeEqualWhenIdDiffers() {
        Assertions.assertNotEquals(new Task(1), new Task(2));
    }
}
