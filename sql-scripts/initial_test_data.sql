USE `javasbcrudmvc01_bd`;

--
-- Data test for `clients`
--

INSERT INTO tb_client (email, name, phone) VALUES
('client1@example.com', 'John Doe', '123-456-7890'),
('client2@example.com', 'Jane Smith', '987-654-3210'),
('client3@example.com', 'David Johnson', '555-555-5555'),
('client4@example.com', 'Sarah Wilson', '888-888-8888'),
('client5@example.com', 'Michael Brown', '777-777-7777'),
('client6@example.com', 'Emily Davis', '999-999-9999'),
('client7@example.com', 'William Miller', '111-111-1111'),
('client8@example.com', 'Olivia Lee', '222-222-2222'),
('client9@example.com', 'James Taylor', '333-333-3333'),
('client10@example.com', 'Emma White', '444-444-4444');

--
-- Data test for `employees`
--

INSERT INTO tb_employee (first_name, last_name, email, salary, hire_date) VALUES 
('John', 'Smith', 'john.smith@email.com', 60000, '2023-09-16'),
('Jane', 'Doe', 'jane.doe@email.com', 55000, '2023-09-17'),
('Michael', 'Johnson', 'michael.johnson@email.com', 65000, '2023-09-18'), 
('Sarah', 'Williams', 'sarah.williams@email.com', 58000, '2023-09-19'),
('David', 'Lee', 'david.lee@email.com', 70000, '2023-09-20'),
('Kevin', 'Brown', 'kevin.brown@email.com', 58000, '2023-09-22'),
('Lisa', 'Wilson', 'lisa.wilson@email.com', 67000, '2023-09-23'),
('Daniel', 'Taylor', 'daniel.taylor@email.com', 63000, '2023-09-24');

--
-- Data test for `departments`
--

INSERT INTO tb_department (name, description, established_date) VALUES 
('HR', 'Human Resources Department', '2023-09-16'),
('Finance', 'Finance and Accounting Department', '2023-09-17'),
('IT', 'Information Technology Department', '2023-09-18'),
('Marketing', 'Marketing and Advertising Department', '2023-09-19'),
('Sales', 'Sales and Business Development Department', '2023-09-20'),
('Customer Service', 'Customer Support Department', '2023-09-21'),
('Research and Development', 'Innovation and Product Development', '2023-09-22'),
('Legal', 'Legal Affairs Department', '2023-09-23'),
('Production', 'Manufacturing and Production Department', '2023-09-24'),
('Quality Control', 'Quality Assurance and Control Department', '2023-09-25');

--
-- Data test for `task_groups`
--

INSERT INTO tb_task_group (description) VALUES
('Project Planning'),
('Requirement Analysis'),
('Design and Development'),
('Quality Assurance'),
('Deployment and Testing'),
('Documentation'),
('Project Review'),
('Client Communication'),
('Resource Allocation'),
('Risk Management'),
('Team Coordination'),
('Vendor Management'),
('Stakeholder Meetings'),
('Progress Reporting'),
('User Training'),
('Change Management'),
('Cost Estimation'),
('Scheduling'),
('Scope Management'),
('Issue Resolution'),
('Task Prioritization'),
('Milestone Tracking'),
('Status Reporting'),
('Budget Management'),
('Procurement Management'),
('Resource Hiring'),
('Contract Management'),
('Integration Management'),
('Communication Planning'),
('Conflict Resolution'),
('Client Feedback'),
('Technical Support'),
('User Acceptance Testing'),
('Data Analysis'),
('Infrastructure Setup'),
('Security Assessment'),
('Performance Monitoring'),
('Feedback Collection'),
('Market Research'),
('Product Launch'),
('Marketing and Promotion'),
('Market Analysis'),
('Legal Compliance'),
('Regulatory Approval'),
('Resource Planning'),
('Knowledge Transfer'),
('Training and Development'),
('Innovation Management');
