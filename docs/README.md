# AuraFarmer User Guide

AuraFarmer is a task management chatbot that helps you keep track of todos, deadlines, and events via a simple chat interface.

## Quick Start

1. Ensure you have Java 17 installed.
2. Download the latest `aurafarmer.jar` from the releases page.
3. Run `java -jar aurafarmer.jar`.

## Features

### Adding a todo: `todo`

Adds a task with no date.

Format: `todo DESCRIPTION`

Example: `todo read book`

```
aura: got it. i've added this task:
aura:   [T][ ] read book
aura: now you have 1 tasks in the list.
```

### Adding a deadline: `deadline`

Adds a task with a due date.

Format: `deadline DESCRIPTION /by DATE`

- `DATE` can be in `yyyy-MM-dd` format (e.g. `2024-12-31`) or plain text.

Example: `deadline return book /by 2024-12-31`

```
aura: got it. i've added this task:
aura:   [D][ ] return book (by: Dec 31 2024)
aura: now you have 2 tasks in the list.
```

### Adding an event: `event`

Adds a task that spans a time period.

Format: `event DESCRIPTION /from DATE /to DATE`

Example: `event team meeting /from 2024-01-15 /to 2024-01-16`

```
aura: got it. i've added this task:
aura:   [E][ ] team meeting (from: Jan 15 2024 to: Jan 16 2024)
aura: now you have 3 tasks in the list.
```

### Listing all tasks: `list`

Shows all tasks in the list.

Format: `list`

```
aura: here are the tasks in your list:
aura: 1.[T][ ] read book
aura: 2.[D][ ] return book (by: Dec 31 2024)
aura: 3.[E][ ] team meeting (from: Jan 15 2024 to: Jan 16 2024)
```

### Marking a task as done: `mark`

Marks a task as completed.

Format: `mark INDEX`

Example: `mark 1`

```
aura: nice! i've marked this task as done:
aura:   [T][X] read book
```

### Unmarking a task: `unmark`

Marks a task as not done.

Format: `unmark INDEX`

Example: `unmark 1`

```
aura: ok, i've marked this task as not done yet:
aura:   [T][ ] read book
```

### Deleting a task: `delete`

Removes a task from the list.

Format: `delete INDEX`

Example: `delete 2`

```
aura: noted. i've removed this task:
aura:   [D][ ] return book (by: Dec 31 2024)
aura: now you have 2 tasks in the list.
```

### Finding tasks: `find`

Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`

Example: `find book`

```
aura: here are the matching tasks in your list:
1.[T][ ] read book
```

### Sorting tasks: `sort`

Sorts all tasks chronologically by date. Deadlines are sorted by their due date, events by their start date. Todos (with no date) are placed at the end.

Format: `sort`

```
aura: tasks sorted by date:
aura: 1.[E][ ] team meeting (from: Jan 15 2024 to: Jan 16 2024)
aura: 2.[D][ ] return book (by: Dec 31 2024)
aura: 3.[T][ ] read book
```

### Exiting the program: `bye`

Exits the application.

Format: `bye`

## Command Summary

| Command    | Format                                      |
|------------|---------------------------------------------|
| `todo`     | `todo DESCRIPTION`                          |
| `deadline` | `deadline DESCRIPTION /by DATE`             |
| `event`    | `event DESCRIPTION /from DATE /to DATE`     |
| `list`     | `list`                                      |
| `mark`     | `mark INDEX`                                |
| `unmark`   | `unmark INDEX`                              |
| `delete`   | `delete INDEX`                              |
| `find`     | `find KEYWORD`                              |
| `sort`     | `sort`                                      |
| `bye`      | `bye`                                       |
