import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task deleteTask(int index) throws AuraFarmerException {
        if (index < 0 || index >= tasks.size()) {
            throw new AuraFarmerException("that task doesn't exist, no aura for you");
        }
        return tasks.remove(index);
    }

    public Task markTask(int index) throws AuraFarmerException {
        if (index < 0 || index >= tasks.size()) {
            throw new AuraFarmerException("that task doesn't exist, no aura for you");
        }
        tasks.get(index).markAsDone();
        return tasks.get(index);
    }

    public Task unmarkTask(int index) throws AuraFarmerException {
        if (index < 0 || index >= tasks.size()) {
            throw new AuraFarmerException("that task doesn't exist, no aura for you");
        }
        tasks.get(index).markAsNotDone();
        return tasks.get(index);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
