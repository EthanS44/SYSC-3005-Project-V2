CREATE TABLE Members (
    member_id       SERIAL      PRIMARY KEY,
    first_name      VARCHAR     NOT NULL,
    last_name       VARCHAR     NOT NULL,
    phone_number    VARCHAR     NOT NULL UNIQUE,
    email           VARCHAR     NOT NULL UNIQUE,
    birthday        DATE,
    join_date       DATE,
    password        VARCHAR     NOT NULL
);

CREATE TABLE Trainers (
    trainer_id      SERIAL      PRIMARY KEY,
    first_name      VARCHAR     NOT NULL,
    last_name       VARCHAR     NOT NULL,
    phone           VARCHAR     NOT NULL UNIQUE,
    email           VARCHAR     NOT NULL UNIQUE,
    password        VARCHAR     NOT NULL
);

CREATE TABLE Staff (
    staff_id        SERIAL      PRIMARY KEY,
    first_name      VARCHAR     NOT NULL,
    last_name       VARCHAR     NOT NULL,
    phone           VARCHAR     NOT NULL UNIQUE,
    email           VARCHAR     NOT NULL UNIQUE,
    password        VARCHAR     NOT NULL
);

CREATE TABLE Health_metrics (
    metric_id       SERIAL      PRIMARY KEY,
    member_id       INTEGER     NOT NULL,
    weight_kg       INTEGER,
    height_cm       INTEGER,
    rest_heart_bpm  INTEGER,
    bmi             INTEGER,
    FOREIGN KEY (member_id)
        REFERENCES Members (member_id)
);

CREATE TABLE Classes (
    class_id        SERIAL      PRIMARY KEY,
    trainer_id      INTEGER     NOT NULL,
    class_date      DATE        NOT NULL,
    start_time      TIME        NOT NULL,
    end_time        TIME        NOT NULL,
    is_full         BOOLEAN     NOT NULL,
    member1_id      INTEGER,
    member2_id      INTEGER,
    member3_id      INTEGER,
    member4_id      INTEGER,
    FOREIGN KEY (trainer_id)
        REFERENCES Trainers (trainer_id)
);

CREATE TABLE Goals (
    goal_id         SERIAL      PRIMARY KEY,
    member_id       INTEGER     NOT NULL,
    goal            TEXT,
    FOREIGN KEY (member_id)
        REFERENCES Members (member_id)
);

CREATE TABLE Achievements (
    achievement_id  SERIAL      PRIMARY KEY,
    member_id       INTEGER     NOT NULL,
    achievement     TEXT,
    FOREIGN KEY (member_id)
        REFERENCES Members (member_id)
);

CREATE TABLE Bills (
    bill_id         SERIAL      PRIMARY KEY,
    member_id       INTEGER     NOT NULL,
    staff_id        INTEGER     NOT NULL,
    date            DATE        NOT NULL,
    dollar_amount   INT         NOT NULL,
    is_paid         BOOLEAN     NOT NULL,
    is_processed    BOOLEAN     DEFAULT FALSE,
    FOREIGN KEY (member_id)
        REFERENCES Members (member_id),
    FOREIGN KEY (staff_id)
        REFERENCES Staff (staff_id)
);

CREATE TABLE Routines (
    routine_id      SERIAL      PRIMARY KEY,
    member_id       INTEGER     NOT NULL,
    routine         TEXT        NOT NULL,
    day_of_the_week VARCHAR,
    FOREIGN KEY (member_id)
        REFERENCES Members (member_id)
);

CREATE TABLE Equipment (
    equipment_id    SERIAL      PRIMARY KEY,
    equipment_type  VARCHAR     NOT NULL,
    last_maintained DATE        NOT NULL
);

CREATE TABLE Sessions (
    session_id      SERIAL      PRIMARY KEY,
    trainer_id      INTEGER     NOT NULL,
    member_id       INTEGER,
    session_date    DATE        NOT NULL,
    start_time      TIME        NOT NULL,
    end_time        TIME        NOT NULL,
    is_booked       BOOLEAN     NOT NULL,
    FOREIGN KEY (trainer_id)
        REFERENCES Trainers (trainer_id),
    FOREIGN KEY (member_id)
        REFERENCES Members (member_id)
);

CREATE TABLE Rooms (
    room_id         SERIAL      PRIMARY KEY,
    member_id       INTEGER,
    trainer_id      INTEGER,
    start_time      TIME        NOT NULL,
    end_time        TIME        NOT NULL,
    is_booked       BOOLEAN     DEFAULT FALSE,
    FOREIGN KEY (member_id)
        REFERENCES Members(member_id),
    FOREIGN KEY (trainer_id)
        REFERENCES Trainers(trainer_id)
);
