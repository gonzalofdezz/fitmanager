CREATE TABLE gyms (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    plan VARCHAR(20) NOT NULL,
    created_at DATETIME NOT NULL
);

CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(190) NOT NULL,
    password_hash VARCHAR(100) NOT NULL,
    enabled BOOLEAN NOT NULL,
    created_at DATETIME NOT NULL,
    CONSTRAINT uk_users_email UNIQUE (email)
);

CREATE TABLE user_gyms (
    user_id BIGINT NOT NULL,
    gym_id BIGINT NOT NULL,
    role VARCHAR(20) NOT NULL,
    PRIMARY KEY (user_id, gym_id),
    CONSTRAINT fk_user_gyms_user
        FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_user_gyms_gym
        FOREIGN KEY (gym_id) REFERENCES gyms(id)
        ON DELETE CASCADE
);

CREATE TABLE rooms (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    gym_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    capacity INT NOT NULL,
    CONSTRAINT fk_rooms_gym
        FOREIGN KEY (gym_id) REFERENCES gyms(id)
        ON DELETE RESTRICT,
    CONSTRAINT uk_rooms_gym_name UNIQUE (gym_id, name)
);

CREATE TABLE class_types (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    gym_id BIGINT NOT NULL,
    name VARCHAR(80) NOT NULL,
    duration_min INT NOT NULL,
    default_capacity INT NOT NULL,
    CONSTRAINT fk_class_types_gym
        FOREIGN KEY (gym_id) REFERENCES gyms(id)
        ON DELETE RESTRICT,
    CONSTRAINT uk_class_types_gym_name UNIQUE (gym_id, name)
);

CREATE TABLE class_sessions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    gym_id BIGINT NOT NULL,
    class_type_id BIGINT NOT NULL,
    room_id BIGINT NOT NULL,
    trainer_id BIGINT NOT NULL,
    start_time DATETIME NOT NULL,
    end_time DATETIME NOT NULL,
    capacity INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_class_sessions_gym
        FOREIGN KEY (gym_id) REFERENCES gyms(id)
        ON DELETE RESTRICT,
    CONSTRAINT fk_class_sessions_class_type
        FOREIGN KEY (class_type_id) REFERENCES class_types(id)
        ON DELETE RESTRICT,
    CONSTRAINT fk_class_sessions_room
        FOREIGN KEY (room_id) REFERENCES rooms(id)
        ON DELETE RESTRICT,
    CONSTRAINT fk_class_sessions_trainer
        FOREIGN KEY (trainer_id) REFERENCES users(id)
        ON DELETE RESTRICT
);

CREATE TABLE reservations (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    gym_id BIGINT NOT NULL,
    class_session_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at DATETIME NOT NULL,
    CONSTRAINT fk_reservations_gym
        FOREIGN KEY (gym_id) REFERENCES gyms(id)
        ON DELETE RESTRICT,
    CONSTRAINT fk_reservations_class_session
        FOREIGN KEY (class_session_id) REFERENCES class_sessions(id)
        ON DELETE RESTRICT,
    CONSTRAINT fk_reservations_member
        FOREIGN KEY (member_id) REFERENCES users(id)
        ON DELETE RESTRICT,
    CONSTRAINT uk_reservations_class_session_member UNIQUE (class_session_id, member_id)
);

CREATE TABLE memberships (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    gym_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,
    plan VARCHAR(50) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_memberships_gym
        FOREIGN KEY (gym_id) REFERENCES gyms(id)
        ON DELETE RESTRICT,
    CONSTRAINT fk_memberships_member
        FOREIGN KEY (member_id) REFERENCES users(id)
        ON DELETE RESTRICT
);

CREATE TABLE invoices (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    gym_id BIGINT NOT NULL,
    member_id BIGINT NOT NULL,
    number VARCHAR(30) NOT NULL,
    issue_date DATE NOT NULL,
    status VARCHAR(20) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_invoices_gym
        FOREIGN KEY (gym_id) REFERENCES gyms(id)
        ON DELETE RESTRICT,
    CONSTRAINT fk_invoices_member
        FOREIGN KEY (member_id) REFERENCES users(id)
        ON DELETE RESTRICT,
    CONSTRAINT uk_invoices_number UNIQUE (number)
);

CREATE TABLE payments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    invoice_id BIGINT NOT NULL,
    provider VARCHAR(30) NOT NULL,
    transaction_id VARCHAR(80) NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    paid_at DATETIME NOT NULL,
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_payments_invoice
        FOREIGN KEY (invoice_id) REFERENCES invoices(id)
        ON DELETE RESTRICT,
    CONSTRAINT uk_payments_transaction_id UNIQUE (transaction_id)
);