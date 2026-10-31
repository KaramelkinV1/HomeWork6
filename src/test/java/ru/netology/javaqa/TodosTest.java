package ru.netology.javaqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {

    private Todos prepareTodos(SimpleTask simpleTask, Epic epic, Meeting meeting) {
        Todos todos = new Todos();
        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);
        return todos;
    }

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");

        Todos todos = prepareTodos(simpleTask, epic, meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindOneSimpleTask() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        Epic epic = new Epic(55, new String[]{"Молоко", "Яйца", "Хлеб"});
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");
        Todos todos = prepareTodos(simpleTask, epic, meeting);

        Assertions.assertArrayEquals(new Task[]{simpleTask}, todos.search("родител"));
    }

    @Test
    public void shouldFindOneEpic() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        Epic epic = new Epic(55, new String[]{"Молоко", "Яйца", "Хлеб"});
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");
        Todos todos = prepareTodos(simpleTask, epic, meeting);

        Assertions.assertArrayEquals(new Task[]{epic}, todos.search("Яйца"));
    }

    @Test
    public void shouldFindOneMeeting() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        Epic epic = new Epic(55, new String[]{"Молоко", "Яйца", "Хлеб"});
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");
        Todos todos = prepareTodos(simpleTask, epic, meeting);

        Assertions.assertArrayEquals(new Task[]{meeting}, todos.search("НетоБанка"));
    }

    @Test
    public void shouldFindSeveralTasks() {
        SimpleTask simpleTask = new SimpleTask(5, "Купить хлеб и позвонить");
        Epic epic = new Epic(55, new String[]{"Молоко", "позвонить", "Хлеб"});
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");
        Todos todos = prepareTodos(simpleTask, epic, meeting);

        Assertions.assertArrayEquals(new Task[]{simpleTask, epic}, todos.search("позвонить"));
    }

    @Test
    public void shouldFindNothing() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        Epic epic = new Epic(55, new String[]{"Молоко", "Яйца", "Хлеб"});
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения",
                "Приложение НетоБанка", "Во вторник после обеда");
        Todos todos = prepareTodos(simpleTask, epic, meeting);

        Assertions.assertArrayEquals(new Task[0], todos.search("Сыр"));
    }

    @Test
    public void shouldFindNothingInEmptyTodos() {
        Todos todos = new Todos();
        Assertions.assertArrayEquals(new Task[0], todos.search("что-то"));
    }
}