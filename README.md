# SQL INTERVIEW GUIDE
| **Problem Statement**                                                                                                      | **Difficulty** | **Topic**           |
| -------------------------------------------------------------------------------------------------------------------------- | -------------- | ------------------- |
| **Basic SQL Queries**                                                                                                      |                |                     |
| 1. Retrieve all employees and their department names.                                                                      | Easy           | Basic SELECT & JOIN |
| 2. List all employees who have a salary greater than $60,000.                                                              | Easy           | WHERE & Filtering   |
| 3. Get all departments with their respective IDs.                                                                          | Easy           | SELECT              |
| 4. Retrieve employee names whose first name starts with "A".                                                               | Easy           | LIKE                |
| 5. List the top 5 highest-paid employees.                                                                                  | Medium         | LIMIT, ORDER BY     |
| **Joins and Relationships**                                                                                                |                |                     |
| 6. Retrieve employees and the projects they are working on.                                                                | Medium         | INNER JOIN          |
| 7. Get the names of employees who belong to the "HR" department.                                                           | Medium         | INNER JOIN          |
| 8. Retrieve the employees who are not assigned to any project.                                                             | Medium         | LEFT JOIN & NULL    |
| 9. Find employees working on more than one project.                                                                        | Medium         | JOIN & GROUP BY     |
| 10. Get employees who are in the "Marketing" department but not in any project.                                            | Medium         | LEFT JOIN           |
| **Aggregates and Grouping**                                                                                                |                |                     |
| 11. Find the total salary paid by the company.                                                                             | Easy           | SUM()               |
| 12. Retrieve the average salary by department.                                                                             | Medium         | GROUP BY            |
| 13. Get the number of employees in each department.                                                                        | Medium         | COUNT() & GROUP BY  |
| 14. Retrieve departments with more than 5 employees.                                                                       | Medium         | GROUP BY & HAVING   |
| 15. Find the highest salary in the company.                                                                                | Easy           | MAX()               |
| 16. Retrieve the employee with the second-highest salary.                                                                  | Hard           | Subquery, LIMIT     |
| 17. Get the total number of employees in the company.                                                                      | Easy           | COUNT()             |
| 18. Retrieve the department with the highest average salary.                                                               | Hard           | GROUP BY & AVG()    |
| 19. List the top 3 departments with the highest average salary.                                                            | Hard           | GROUP BY & ORDER BY |
| 20. Calculate the total number of projects each department is working on.                                                  | Medium         | COUNT() & GROUP BY  |
| **Subqueries**                                                                                                             |                |                     |
| 21. Get the employees with the highest salary.                                                                             | Medium         | Subquery            |
| 22. Retrieve employees who earn more than the average salary of the company.                                               | Hard           | Subquery            |
| 23. Find employees who earn more than the average salary of their department.                                              | Hard           | Subquery            |
| 24. List employees who earn more than the average salary of their department (using a subquery).                           | Hard           | Subquery            |
| 25. Get the department with the highest number of projects.                                                                | Hard           | Subquery            |
| **Advanced SQL Queries**                                                                                                   |                |                     |
| 26. Find the employees who have worked on the most projects.                                                               | Hard           | JOIN, GROUP BY      |
| 27. Retrieve all employees who have worked on exactly two projects.                                                        | Hard           | GROUP BY & HAVING   |
| 28. Retrieve employees whose salary is higher than the average salary of all employees, but lower than the maximum salary. | Hard           | Subquery            |
| 29. List departments with the highest total project cost.                                                                  | Hard           | JOIN & SUM()        |
| 30. Retrieve employees working on projects that belong to the "Engineering" department.                                    | Medium         | JOIN & WHERE        |
| 31. Get the department names and total number of employees for departments with more than 3 employees.                     | Medium         | GROUP BY, HAVING    |
| 32. List employees who have worked on all projects in the "HR" department.                                                 | Hard           | JOIN, Subquery      |
| 33. Get the employee name(s) with the lowest salary.                                                                       | Medium         | MIN()               |
| 34. Find the employee(s) who joined in the same year as the highest-paid employee.                                         | Medium         | JOIN, YEAR()        |
| 35. List projects that have the same number of employees assigned.                                                         | Hard           | GROUP BY, HAVING    |
| **Update and Delete Operations**                                                                                           |                |                     |
| 36. Increase the salary of all employees by 10%.                                                                           | Easy           | UPDATE              |
| 37. Delete all employees in the "Marketing" department.                                                                    | Medium         | DELETE              |
| 38. Change the department of employee "Alice" to "HR".                                                                     | Easy           | UPDATE              |
| 39. Delete a project with no employees assigned.                                                                           | Medium         | DELETE, JOIN        |
| 40. Update the salary of employees working on a project "A" by 5%.                                                         | Medium         | UPDATE              |
| **Complex Joins**                                                                                                          |                |                     |
| 41. Retrieve all employees along with their department name and project name.                                              | Medium         | JOINs               |
| 42. Find employees working on projects for the "Engineering" department.                                                   | Medium         | JOINs               |
| 43. Retrieve all employees who are working on both "Project A" and "Project B".                                            | Hard           | JOIN, INTERSECT     |
| 44. List employees working on any project, but not assigned to "Project D".                                                | Medium         | NOT IN, JOIN        |
| 45. Get the projects assigned to employees in the "HR" department, but exclude "Project X".                                | Medium         | JOIN, NOT IN        |
| **Window Functions**                                                                                                       |                |                     |
| 46. Retrieve the top 3 highest-paid employees using window functions.                                                      | Hard           | ROW_NUMBER()        |
| 47. Get the rank of employees based on salary within their department.                                                     | Hard           | RANK()              |
| 48. Find the cumulative salary of employees, sorted by joining date.                                                       | Hard           | SUM() OVER()        |
| 49. Retrieve the 5th highest salary using window functions.                                                                | Hard           | ROW_NUMBER()        |
| 50. List employees with their salary and the average salary of their department.                                           | Medium         | AVG() OVER()        |
| **Database Design and Normalization**                                                                                      |                |                     |
| 51. Design a database schema for a library system.                                                                         | Hard           | Database Design     |
| 52. Normalize the following schema (First Normal Form).                                                                    | Hard           | Normalization       |
| 53. Design a schema to handle orders and products in an e-commerce system.                                                 | Hard           | Database Design     |
| 54. Create a table for storing employee benefits and their types.                                                          | Medium         | Database Design     |
| 55. Design a database for a simple blog system with authors and posts.                                                     | Medium         | Database Design     |
| **Indexes and Optimization**                                                                                               |                |                     |
| 56. Create an index on the employee's `LastName` column.                                                                   | Medium         | Indexing            |
| 57. Optimize a query that retrieves employee names based on their department.                                              | Hard           | Query Optimization  |
| 58. Create a composite index on `FirstName` and `LastName` for the `Employees` table.                                      | Medium         | Indexing            |
| 59. Determine if creating an index on `Salary` will improve performance in a query.                                        | Hard           | Indexing            |
| 60. Retrieve employees with a specific salary range using an optimized query.                                              | Medium         | Query Optimization  |
| **Data Types and Constraints**                                                                                             |                |                     |
| 61. Define a primary key constraint for the `EmployeeID` field.                                                            | Easy           | Constraints         |
| 62. Create a unique constraint on the `Email` field for the Employees table.                                               | Medium         | Constraints         |
| 63. Add a foreign key constraint to the `ProjectID` column in the `Employee_Project` table.                                | Medium         | Constraints         |
| 64. Implement a check constraint on the `Salary` column to ensure it is greater than 0.                                    | Medium         | Constraints         |
| 65. Create a `NOT NULL` constraint on the `DepartmentName` field in the Departments table.                                 | Easy           | Constraints         |
| **Transactions**                                                                                                           |                |                     |
| 66. Implement a transaction to update an employee's salary and department in one operation.                                | Medium         | Transactions        |
| 67. Write a transaction that ensures data integrity when updating multiple employee records.                               | Hard           | Transactions        |
| 68. Rollback a transaction in case of an error during an update operation.                                                 | Medium         | Transactions        |
 
| #                                             | **Problem Statement**                                                                    | **Difficulty** | **Topic**        |
| --------------------------------------------- | ---------------------------------------------------------------------------------------- | -------------- | ---------------- |
| **Transactions (continued)**                  |                                                                                          |                |                  |
| 69                                            | Commit a transaction after successfully inserting a new employee and assigning a project | Medium         | Transactions     |
| 70                                            | Use SAVEPOINT to rollback only part of a transaction                                     | Hard           | Transactions     |
| **Date & Time Functions**                     |                                                                                          |                |                  |
| 71                                            | Retrieve employees who joined in the last 2 years                                        | Easy           | Date Functions   |
| 72                                            | Find employees who joined in the same month                                              | Medium         | Date Functions   |
| 73                                            | Calculate total experience (in years) of each employee                                   | Medium         | DATEDIFF         |
| 74                                            | Find departments with employees hired before 2019                                        | Medium         | Date + JOIN      |
| **String Functions**                          |                                                                                          |                |                  |
| 75                                            | Concatenate first name and last name as FullName                                         | Easy           | String Functions |
| 76                                            | Find employees whose last name length is greater than 5                                  | Easy           | LENGTH           |
| 77                                            | Convert all department names to uppercase                                                | Easy           | UPPER            |
| 78                                            | Find employees whose email ends with “@company.com”                                      | Medium         | LIKE             |
| **CASE Statements**                           |                                                                                          |                |                  |
| 79                                            | Categorize employees as High / Medium / Low salary                                       | Medium         | CASE             |
| 80                                            | Display “Experienced” if employee joined before 2020 else “Fresher”                      | Medium         | CASE             |
| **NULL Handling**                             |                                                                                          |                |                  |
| 81                                            | Replace NULL salaries with 0                                                             | Easy           | COALESCE         |
| 82                                            | Find employees whose manager is NULL                                                     | Medium         | NULL             |
| 83                                            | Count employees excluding NULL department IDs                                            | Medium         | NULL             |
| **Set Operations**                            |                                                                                          |                |                  |
| 84                                            | Retrieve employees working on Project A or Project B                                     | Medium         | UNION            |
| 85                                            | Retrieve employees working on both Project A and Project B                               | Hard           | INTERSECT        |
| 86                                            | Retrieve employees working on Project A but not Project B                                | Hard           | EXCEPT           |
| **Views**                                     |                                                                                          |                |                  |
| 87                                            | Create a view showing employee name and department                                       | Easy           | VIEW             |
| 88                                            | Create a view for high-salary employees (>80k)                                           | Medium         | VIEW             |
| **Stored Queries / Logic**                    |                                                                                          |                |                  |
| 89                                            | Write a query to get nth highest salary                                                  | Hard           | Subquery         |
| 90                                            | Find duplicate employee names                                                            | Medium         | GROUP BY         |
| **Real Interview-Style Problems**             |                                                                                          |                |                  |
| 91                                            | Find employees whose salary is greater than their manager’s salary                       | Hard           | Self JOIN        |
| 92                                            | Find managers with more than 3 reportees                                                 | Hard           | Self JOIN        |
| 93                                            | Retrieve the department where total salary is maximum                                    | Hard           | GROUP BY         |
| 94                                            | Find employees who never changed departments                                             | Medium         | Subquery         |
| 95                                            | Find project with maximum number of employees                                            | Medium         | GROUP BY         |
| **Performance & Optimization**                |                                                                                          |                |                  |
| 96                                            | Identify slow query and suggest index                                                    | Hard           | Optimization     |
| 97                                            | Explain difference between WHERE vs HAVING using example                                 | Easy           | Concepts         |
| **Schema & Constraints (Interview Favorite)** |                                                                                          |                |                  |
| 98                                            | Add ON DELETE CASCADE to Employee_Project table                                          | Medium         | Constraints      |
| 99                                            | Difference between DELETE, TRUNCATE, DROP (with examples)                                | Easy           | Concepts         |
| 100                                           | Design a scalable schema for employee-project tracking                                   | Hard           | System Design    |
