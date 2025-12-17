--Basic SQL Queries

--| 1. Retrieve all employees and their department names.                                           
SELECT  E.first_name, D.name FROM employees AS E
INNER JOIN departments AS D
ON E.dept_id = D.id

-- 2. List all employees who have a salary greater than $60,000.

SELECT first_name, salary FROM employees AS E
WHERE E.salary > 60000 

-- 3. Get all departments with their respective IDs.
SELECT d.id ,d.name FROM departments as d

-- 4.Retrieve employee names whose first name starts with "A".

SELECT e.first_name ,e.last_name FROM employees AS e
WHERE e.first_name LIKE 'a%'

-- 5. List the top 5 highest-paid employees.

SELECT CONCAT(e.first_name, ' ', e.last_name  )as full_name, e.salary
FROM employees as e
ORDER BY salary DESC
LIMIT 5

-- 6. Retrieve employees and the projects they are working on

SELECT CONCAT(e.first_name, ' ', e.last_name  )as full_name, e.salary, P.name AS PROJECT 
FROM employees as e 
INNER JOIN employee_project AS EP
ON EP.emp_id = e.id
INNER JOIN projects AS P
ON P.id = `EP`.project_id



-- 7. Get the names of employees who belong to the "HR" department
SELECT CONCAT(e.first_name, ' ', e.last_name  )as full_name,D.name FROM employees AS e
INNER JOIN departments AS D
ON  e.dept_id = D.id
WHERE D.name   IN ("HR")


-- 8 . Retrieve the employees who are not assigned to any project. 
SELECT CONCAT(e.first_name, ' ', e.last_name  )as full_name FROM employees AS e
LEFT JOIN employee_project AS ep
on ep.emp_id = e.id
where ep.emp_id is NULL
 
-- 9. Find employees working on more than one project
SELECT CONCAT(e.first_name, ' ', e.last_name  )as full_name ,COUNT(ep.emp_id) AS COUNT 
FROM employees AS e
JOIN  employee_project AS ep 
ON e.id = ep.emp_id
GROUP BY e.id
HAVING COUNT(ep.emp_id) > 1

--10. Get employees who are in the "Marketing" department but not in any project.
SELECT CONCAT(e.first_name, ' ', e.last_name  )as full_name
from employees as e
LEFT JOIN departments as d
on e.dept_id = d.id
LEFT JOIN employee_project AS ep
ON e.id = ep.emp_id
WHERE d.name = "Marketing" AND
  ep.project_id IS NULL