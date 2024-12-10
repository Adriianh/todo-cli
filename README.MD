# To-Do CLI
A simple yet powerful command-line tool to manage your tasks. **To-Do CLI** lets you stay organized directly from the terminal, with intuitive commands to add, list, complete, and delete tasks, all with support for multiple storage files.

## Features
- **Comprehensive task management:**
  - Add tasks with sub-tasks or descriptions as a list.
  - List all your pending and completed tasks.
  - Mark tasks as completed or delete them as needed.
- **Support for multiple storage files:**
  - Define different files to organize your tasks by projects or categories.
  - Switch easily between task files using a dedicated command.
- **User-friendly command-line interface:**
  - Clear commands and configurable options for a smooth experience.

## Installation

1. **Clone this repository:**
```bash
git clone https://github.com/adriianh/todo-cli.git
cd todo-cli
```
2. **Set up the environment:** Ensure you have Kotlin and Gradle installed on your system. Then, build the project:

```bash
./gradlew build
```
3. **Run the application:**

```bash
java -jar /bin/todo.jar
```

## Usage
The application supports the following commands:

1. `add` - **Add a new task**

Add a task with a name and optionally a list of descriptions or sub-tasks.

 **Example:**

```bash
java -jar /bin/todo.jar add Trip --description="Book flight,Find hotel,Pack bags"
```
2. `list` - **List all tasks**

Displays all tasks in the current file, including their status (pending or completed) and descriptions.

**Example:**

```bash
| ID | Name |             Description            | Done |
|----|------|:----------------------------------:|------|
| 1  | Trip | Book Flight, Find Hotel, Pack Bags | No   |
```

**Output:**

```yaml
ID: 1, Name: Trip, Done: No
  Description:
    1. Book flight
    2. Find hotel
    3. Pack bags
```

3. `done` - **Mark a task as completed**

Mark a specific task as completed using its ID.

**Example:**

```bash
java -jar /bin/todo.jar done 1
```

4. `edit` - **Edit task description**

Edit a specific task description using its ID.

**Example:**

```bash
java -jar /bin/todo.jar edit 1 --add="Buy food"
```

5. `delete` - **Delete a task**

Remove a specific task using its ID.

**Example:**

```bash
java -jar /bin/todo.jar delete 1
```

6. `file` - **Set the storage file path**

Change the file where tasks are stored. Ideal for organizing tasks into different projects or contexts.

**Example:**

```bash
java -jar /bin/todo.jar file "my_trip.json"
```

## Storage File Configuration
The default storage file is `tasks.json`. You can change this file using the `file` command. All data is stored in JSON format, making it easy to integrate or edit manually if needed.

## Libraries Used
This application uses the following libraries:

1. [Clikt](https://github.com/ajalt/clikt):
    - A library for building command-line interfaces in Kotlin.
    - Simplifies the management of commands, options, and arguments.

2. [Kotlinx Serialization](https://github.com/Kotlin/kotlinx.serialization):
    - Used for serializing and deserializing data in JSON format.
    - Efficiently saves and loads tasks from a JSON file.

3. Java IO:
    - File handling for data persistence.

## Upcoming Features
- Support for **priorities** and **deadlines** in tasks.
- Ability to **reorder** sub-tasks.
- Integration with notifications for reminders.

## Contribution
Contributions are welcome! If you find an issue or have an idea to improve the application:

- Open an *issue* on this repository.
- Fork the repository and send a *pull request* with your changes.

## License
This project is licensed under the MIT License.

