# Task Management System

This guide outlines the steps to run the task management system
### Default Admin User
When the application is first started, a default admin user is created with the following credentials:

- **Email:** `admin@gmail.com`
- **Password:** `123`

  it's recommonded to change admin password directly using the change-password endpoint provided
---

## Prerequisites

Ensure the following are installed and configured on your system:

1. **Java Development Kit (JDK)**:

   - Version: `17` or later.
   - Verify installation:
     ```bash
     java -version
     ```

2. **Maven**:

   - Verify installation:
     ```bash
     mvn -version
     ```

3. **Docker & Docker Compose**:

   - Verify Docker installation:
     ```bash
     docker --version
     ```
   - Verify Docker Compose installation:
     ```bash
     docker-compose --version
     ```

4. **Git**:

   - Verify installation:
     ```bash
     git --version
     ```

---

## Clone the Repository

1. Clone the application repository:

   ```bash
   git clone <repository_url>
   ```

   Replace `<repository_url>` with the URL of your GitHub repository.

2. Navigate to the project directory:

   ```bash
   cd <project_name>
   ```



## Run Database Using Docker Compose

1. Start the PostgreSQL database container:

   ```bash
   docker-compose up -d
   ```

2. Verify the container is running:

   ```bash
   docker ps
   ```

   Ensure a PostgreSQL container is listed and running on port `5432`.

---

## Run the Application

1. Use Maven to run the application:

   ```bash
   mvn spring-boot:run
   ```

2. Alternatively, build the project and run the JAR file:

   ```bash
   mvn clean package
   java -jar target/<application_name>-<version>.jar
   ```

   Replace `<application_name>` and `<version>` with the actual JAR file name generated in the `target` directory.

3. Or you can directly it from the ide. 

---

## Access the Application

1. Open a web browser and navigate to:

   ```
   http://localhost:8080/{api endpoints}
   api documentation is provided below 
   ```

2. Verify the application is running and connected to the PostgreSQL database.

---


# API Documentation

## AdminController Endpoints

### Get User by ID
**GET /admin/users/{id}**
- **Description**: Retrieves a user by their ID.
- **Path Variables**:
  - `id` (long): ID of the user to retrieve.
- **Response**:
  - `200 OK`: UserDto object containing user details.
- **Errors**:
  - `404 Not Found`: User not found.

### Get All Users
**GET /admin/users**
- **Description**: Retrieves a paginated list of all users.
- **Query Parameters**:
  - `page` (int, optional, default: 0): Page number.
  - `size` (int, optional, default: 10): Page size.
- **Response**:
  - `200 OK`: Paginated list of UserDto objects.

### Delete User
**DELETE /admin/users/{id}**
- **Description**: Deletes a user by their ID.
- **Path Variables**:
  - `id` (long): ID of the user to delete.
- **Response**:
  - `200 OK`: Success message.
- **Errors**:
  - `404 Not Found`: User not found.

## HistoryController Endpoints

### Get History
**GET /history**
- **Description**: Retrieves the history of actions.
- **Response**:
  - `200 OK`: List of HistoryEntity objects.

### Clear History
**DELETE /history/clear**
- **Description**: Clears all history entries.
- **Response**:
  - `200 OK`: Success message.

## TaskController Endpoints

### Create Task
**POST /tasks/**
- **Description**: Creates a new task.
- **Request Body**:
  - TaskDto object containing task details.
- **Response**:
  - `201 Created`: Created TaskDto object.
- **Errors**:
  - `404 Not Found`: User not found.

### Get Task by ID
**GET /tasks/{id}**
- **Description**: Retrieves a task by its ID.
- **Path Variables**:
  - `id` (long): ID of the task to retrieve.
- **Response**:
  - `200 OK`: TaskDto object containing task details.
- **Errors**:
  - `404 Not Found`: Task not found.

### Get All Tasks
**GET /tasks/**
- **Description**: Retrieves a paginated list of all tasks.
- **Query Parameters**:
  - Pageable parameters.
- **Response**:
  - `200 OK`: Paginated list of TaskDto objects.

### Update Task (Full)
**PUT /tasks/{id}**
- **Description**: Fully updates a task.
- **Path Variables**:
  - `id` (long): ID of the task to update.
- **Request Body**:
  - TaskDto object containing updated task details.
- **Response**:
  - `200 OK`: Updated TaskDto object.
- **Errors**:
  - `404 Not Found`: Task not found.

### Update Task (Partial)
**PATCH /tasks/{id}**
- **Description**: Partially updates a task.
- **Path Variables**:
  - `id` (long): ID of the task to update.
- **Request Body**:
  - Partial TaskDto object with fields to update.
- **Response**:
  - `200 OK`: Updated TaskDto object.
- **Errors**:
  - `404 Not Found`: Task not found.

### Delete Task
**DELETE /tasks/{id}**
- **Description**: Deletes a task by its ID.
- **Path Variables**:
  - `id` (long): ID of the task to delete.
- **Response**:
  - `200 OK`: Success message.
- **Errors**:
  - `404 Not Found`: Task not found.

### Change Task Status
**PUT /tasks/{id}/changestatus**
- **Description**: Changes the status of a task.
- **Path Variables**:
  - `id` (long): ID of the task.
- **Request Body**:
  - JSON object with `status` field.
- **Response**:
  - `200 OK`: Success message.
- **Errors**:
  - `404 Not Found`: Task not found.

### Search Tasks
**GET /tasks/search**
- **Description**: Searches tasks based on criteria.
- **Query Parameters**:
  - `title` (String, optional): Task title.
  - `description` (String, optional): Task description.
  - `status` (String, optional): Task status.
  - `dueDate` (Date, optional): Task due date (yyyy-MM-dd).
  - `priority` (String, optional): Task priority.
- **Response**:
  - `200 OK`: List of matching TaskDto objects.

## UserController Endpoints

### Authenticate
**POST /users/authenticate**
- **Description**: Authenticates a user and returns a token.
- **Request Body**:
  - UserEntity object with `email` and `password`.
- **Response**:
  - `200 OK`: Authentication token.
- **Errors**:
  - `404 Not Found`: User not found.

### Register
**POST /users/register**
- **Description**: Registers a new user.
- **Request Body**:
  - UserEntity object with user details.
- **Response**:
  - `201 Created`: Registered UserEntity object.

### Get Profile
**GET /users/me**
- **Description**: Retrieves the profile of the currently authenticated user.
- **Response**:
  - `200 OK`: UserDto object containing user details.
- **Errors**:
  - `404 Not Found`: User not found.

### Update User (Full)
**PUT /users/{id}**
- **Description**: Fully updates a user.
- **Path Variables**:
  - `id` (long): ID of the user to update.
- **Request Body**:
  - UserDto object with updated details.
- **Response**:
  - `200 OK`: Updated UserDto object.
- **Errors**:
  - `404 Not Found`: User not found.

### Update User (Partial)
**PATCH /users/{id}**
- **Description**: Partially updates a user.
- **Path Variables**:
  - `id` (long): ID of the user to update.
- **Request Body**:
  - Partial UserDto object with fields to update.
- **Response**:
  - `200 OK`: Updated UserDto object.
- **Errors**:
  - `404 Not Found`: User not found.

### Change Password
**POST /change-password**
- **Description**: Changes the password for a user.
- **Request Body**:
  - JSON object with `email`, `oldPassword`, and `newPassword` fields.
- **Response**:
  - `200 OK`: Success message.
- **Errors**:
  - `404 Not Found`: User not found.

**Note**: This endpoint is available for both admins and users.



