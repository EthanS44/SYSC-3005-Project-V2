INSERT INTO Members (first_name, last_name, phone_number, email, birthday, join_date, password)
VALUES
    ('Ethan', 'Stewart', '6130673576', 'ethanstewart@example.com', '2003-07-30', '2024-04-05', 'ethan'),
    ('Liam', 'Stewart', '6131458239', 'liamstewart@example.com', '2006-12-07', '2024-04-06', 'liam');


INSERT INTO Trainers (first_name, last_name, phone, email, password)
VALUES
    ('Susan', 'May', '6134560892', 'susanmay@example.com', 'susan'),
    ('Jack', 'Black', '6132562764', 'jackblack@example.com', 'jack');


INSERT INTO Staff (first_name, last_name, phone, email, password)
VALUES
    ('John', 'Doe', '6138942854', 'johndoe@example.com', 'john'),
    ('Bill', 'Smith', '6134760957', 'billsmith@example.com', 'bill');


INSERT INTO Health_metrics (member_id, weight_kg, height_cm, rest_heart_bpm, bmi)
VALUES
    (1, 70, 175, 70, 19),
    (2, 80, 165, 67, 25);


INSERT INTO Classes (trainer_id, class_date, start_time, end_time, is_full, member1_id, member2_id, member3_id, member4_id)
VALUES
    (1, '2024-04-15', '10:00:00', '11:00:00', FALSE, 1, 2, NULL, NULL),
    (2, '2024-04-16', '12:00:00', '01:00:00', FALSE, NULL, NULL, NULL, NULL);


INSERT INTO Goals (member_id, goal)
VALUES
    (1, 'Gain 5kg of muscle'),
    (2, 'Lose 10kg');


INSERT INTO Achievements (member_id, achievement)
VALUES
    (1, 'I benched 180 pounds'),
    (2, 'I ran a marathon');


INSERT INTO Bills (member_id, staff_id, date, dollar_amount, is_paid, is_processed)
VALUES
    (1, 1, '2024-04-11', 95, TRUE, TRUE),
    (2, 2, '2024-04-10', 60, FALSE, FALSE);


INSERT INTO Routines (member_id, routine, day_of_the_week)
VALUES
    (1, 'Treadmill Run 5km', 'Monday'),
    (1, 'Rowing 10km', 'Monday'),
    (1, 'Chest and Back', 'Wednesday');


INSERT INTO Equipment (equipment_type, last_maintained)
VALUES
    ('Treadmill', '2023-03-02'),
    ('Elliptical', '2021-09-15');


INSERT INTO Sessions (trainer_id, member_id, session_date, start_time, end_time, is_booked)
VALUES
    (1, 1, '2024-04-13', '09:00:00', '10:00:00', TRUE),
    (2, NULL, '2024-04-14', '05:30:00', '07:30:00', FALSE);


INSERT INTO Rooms (member_id, trainer_id, start_time, end_time, is_booked)
VALUES
    (NULL, NULL, '11:00:00', '12:00:00', FALSE),
    (NULL, 2, '2:15:00', '3:45:00', TRUE);