# Maltese User Guide

Maltese is a **task management chatbot, optimised for use via a Command Line Interface** (CLI).

* Features
    * Adding a todo: todo
    * Adding a deadline: deadline
    * Adding an event: event
    * Listing all tasks: list
    * Marking a task as done: mark
    * Unmarking a task: unmark
    * Deleting a task: delete
    * Finding tasks: find
    * Exiting the program: bye
    * Saving the data
* Command summary

---

## Features

### Adding a todo: `todo`

Add a todo task to your task list.

Format: `todo DESCRIPTION`

Examples:
* `todo homework`

Expected output:
```
Added the following todo:
[T][ ] homework
```

### Adding a deadline: `deadline`

Add a deadline with a due date.

Format: `deadline DESCRIPTION /by DEADLINE`

Examples:
* `deadline assignment /by friday 3pm`

Expected output:
```
Added the following deadline:
[D][ ] assignment /by friday 3pm
```

### Adding an event: `event`

Add an event with start and end times.

Format: `event DESCRIPTION /from START_TIME /to END_TIME`

Examples:
* `event group meeting /from 2pm /to 3pm`

Expected output:
```
Added the following event:
[E][ ] group meeting /from 2pm /to 4pm
```

### Listing all tasks: `list`

Shows a list of all tasks in your task list.

Format: `list`

Expected output when tasks exist:
```
1.[T][ ] homework
2.[D][ ] lab /by 2359
3.[E][X] birthday /from 2pm /to 3pm
4.[T][ ] night
5.[D][ ] math homework /by 2359
6.[E][ ] homework2 /from 2pm /to 4pm
7.[T][ ] stuff
```

Expected output when no tasks exist:
```
List is empty yippee
```

### Marking a task as done: `mark`

Marks the specified task as completed.

Format: `mark INDEX`

* Marks the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `mark 2`

Expected output:
```
okie this task is done
```

### Unmarking a task: `unmark`

Marks the specified task as not completed.

Format: `unmark INDEX`

* Unmarks the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `unmark 2`

Expected output:
```
okie this task is still doing
```

### Deleting a task: `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …

Examples:
* `delete 5`

Expected output:
```
Deleting the following task:
[D][ ] math homework /by 2359
You have 8 tasks left
```

### Finding tasks: `find`

Finds tasks whose descriptions contain the given keyword.

Format: `find KEYWORD`

* The search is case-sensitive.
* Only the task description is searched.
* Partial matches are supported.

Examples:
* `find homework`

Expected output:
```
Here are the matching tasks in the list:
[T][ ] homework
[E][ ] homework2 /from 2pm /to 4pm
[T][ ] homework
```

### Exiting the program: `bye`

Exits the program.

Format: `bye`

Expected output:
```
NOOOOOOOOOOOOO. See you soon o.0!
```

### Saving the data

Maltese data are saved in tasks.txt automatically after any command that changes the data. There is no need to save manually.

---

## Command summary

| Action     | Format, Examples                                                                                                                                                 |
| ---------- | ---------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Todo**   | todo DESCRIPTION e.g., todo homework                                                                                                                           |
| **Deadline** | deadline DESCRIPTION /by DEADLINE e.g., deadline assignment /by friday 3pm                                                                              |
| **Event**  | event DESCRIPTION /from START_TIME /to END_TIME e.g., event group meeting /from 2pm /to 4pm                                                                      |
| **List**   | list                                                                                                                                                            |
| **Mark**   | mark INDEX e.g., mark 2                                                                                                                                         |
| **Unmark** | unmark INDEX e.g., unmark 2                                                                                                                                     |
| **Delete** | delete INDEX e.g., delete 5                                                                                                                                      |
| **Find**   | find KEYWORD e.g., find homework                                                                                                                                |
| **Bye**    | bye                                                                                                                                                             |
