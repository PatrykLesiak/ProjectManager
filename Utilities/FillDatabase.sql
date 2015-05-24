DROP TABLE Technologies_to_Projects;
DROP TABLE Projects_to_Users;
DROP TABLE Users_to_Technologies;
DROP TABLE Users;
DROP TABLE Projects;
DROP TABLE Technologies;

CREATE TABLE Users
(
UserID int PRIMARY KEY NOT NULL AUTO_INCREMENT,
LastName varchar(255),
FirstName varchar(255),
Email varchar(255) NOT NULL,
Password varchar(256) NOT NULL
);

CREATE TABLE Projects
(
ProjectID int PRIMARY KEY,
Title varchar(255),
Description varchar(1024)
);

CREATE TABLE Technologies
(
TechnologyID int PRIMARY KEY,
Name varchar(255)
);

CREATE TABLE Technologies_to_Projects
(
TechnologyID int NOT NULL,
ProjectID int NOT NULL,
FOREIGN KEY (TechnologyID) REFERENCES Technologies(TechnologyID),
FOREIGN KEY (ProjectID) REFERENCES Projects(ProjectID)
);

CREATE TABLE Projects_to_Users
(
ProjectID int NOT NULL,
UserID int NOT NULL,
FOREIGN KEY (ProjectID) REFERENCES Projects(ProjectID),
FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

CREATE TABLE Users_to_Technologies
(
UserID int NOT NULL,
TechnologyID int NOT NULL,
FOREIGN KEY (UserID) REFERENCES Users(UserID),
FOREIGN KEY (TechnologyID) REFERENCES Technologies(TechnologyID)
);


INSERT INTO Users (LastName, FirstName, Email, Password) VALUES ('Majewski', 'Maciej', 'majewski.maciej@maciej.pl', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855');
INSERT INTO Users (LastName, FirstName, Email, Password) VALUES ('Lesiak', 'Patryk', 'patryk.lesiak@patryk.pl', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855');

INSERT INTO Projects VALUES(1, 'Test project', 'More description about project');

INSERT INTO Technologies VALUES(1, 'Java');
INSERT INTO Technologies VALUES(2, 'JavaEE');
INSERT INTO Technologies VALUES(3, 'C');
INSERT INTO Technologies VALUES(4, 'C++');
INSERT INTO Technologies VALUES(5, 'C#');
INSERT INTO Technologies VALUES(6, 'Python');
INSERT INTO Technologies VALUES(7, 'Ruby');

INSERT INTO Users_to_Technologies VALUES (1, 6);
INSERT INTO Users_to_Technologies VALUES (2, 4);

Insert INTO Technologies_to_Projects VALUES(6, 1);

INSERT INTO Projects_to_Users VALUES(1, 1);