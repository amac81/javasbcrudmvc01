use javasbcrudmvc01_bd;

-- Insert employee data into tb_employee without the "id" column
INSERT INTO tb_employee (first_name, last_name, email, salary, hire_date, department)
VALUES
    ('John', 'Doe', 'john.doe@example.com', 60000.00, '2020-05-15', 'HR'),
    ('Jane', 'Smith', 'jane.smith@example.com', 75000.00, '2019-08-22', 'Finance'),
    ('Michael', 'Johnson', 'michael.johnson@example.com', 55000.00, '2021-03-10', 'IT'),
    ('Emily', 'Davis', 'emily.davis@example.com', 80000.00, '2018-11-05', 'Marketing'),
    ('David', 'Wilson', 'david.wilson@example.com', 70000.00, '2022-02-18', 'Sales'),
    ('Sarah', 'Brown', 'sarah.brown@example.com', 62000.00, '2020-09-30', 'Customer Service');
    ('Robert', 'Anderson', 'robert.anderson@example.com', 62000.00, '2022-04-15', 'IT'),
    ('Jennifer', 'Martinez', 'jennifer.martinez@example.com', 68000.00, '2019-12-03', 'Finance'),
    ('William', 'Lee', 'william.lee@example.com', 57000.00, '2020-10-25', 'Sales'),
    ('Mary', 'Garcia', 'mary.garcia@example.com', 72000.00, '2021-07-08', 'Marketing'),
    ('James', 'Smith', 'james.smith@example.com', 66000.00, '2020-02-14', 'HR'),
    ('Linda', 'Jones', 'linda.jones@example.com', 59000.00, '2023-01-19', 'Customer Service'),
    ('Michael', 'Wilson', 'michael.wilson@example.com', 70000.00, '2018-06-27', 'IT'),
    ('Karen', 'Thompson', 'karen.thompson@example.com', 74000.00, '2019-04-12', 'Finance'),
    ('Richard', 'Brown', 'richard.brown@example.com', 61000.00, '2021-11-30', 'Sales'),
    ('Elizabeth', 'Hernandez', 'elizabeth.hernandez@example.com', 78000.00, '2020-08-17', 'Marketing');
