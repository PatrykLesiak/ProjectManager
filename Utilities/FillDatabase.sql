DROP TABLE Users_to_Technologies;
DROP TABLE Users_to_Modules;
DROP TABLE Technologies_to_Modules;
DROP TABLE Technologies;
DROP TABLE Modules;
DROP TABLE Projects;
DROP TABLE Users;

CREATE TABLE Users
(
UserID int PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
LastName varchar(255),
FirstName varchar(255),
Email varchar(255) NOT NULL,
Password varchar(256) NOT NULL,
AvatarLink varchar(255),
ReadmeLink varchar(255)
);

CREATE TABLE Projects
(
ProjectID int PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
Title varchar(255),
Description varchar(1024),
LeaderID int NOT NULL,
RreadmeLink varchar(255),
ContactAndLinks varchar(1024),
PictureLink varchar(255),
Recruting boolean DEFAULT True,
FOREIGN KEY (LeaderID) REFERENCES Users(UserID)
);

CREATE TABLE Technologies
(
TechnologyID int PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
Name varchar(255)
);

CREATE TABLE Modules
(
ModuleID int PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
ProjectID int NOT NULL,
Title varchar(255),
Description varchar(1024),
ReadmeLink varchar(255),
FOREIGN KEY (ProjectID) REFERENCES Projects(ProjectID)
);

CREATE TABLE Technologies_to_Modules
(
Id int PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
TechnologyID int NOT NULL,
ModuleID int NOT NULL,
FOREIGN KEY (TechnologyID) REFERENCES Technologies(TechnologyID),
FOREIGN KEY (ModuleID) REFERENCES Modules(ModuleID)
);

CREATE TABLE Users_to_Technologies
(
Id int PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
UserID int NOT NULL,
TechnologyID int NOT NULL,
FOREIGN KEY (UserID) REFERENCES Users(UserID),
FOREIGN KEY (TechnologyID) REFERENCES Technologies(TechnologyID)
);

CREATE TABLE Users_to_Modules
(
Id int PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
UserID int NOT NULL,
ModuleID int NOT NULL,
FOREIGN KEY (UserID) REFERENCES Users(UserID),
FOREIGN KEY (ModuleID) REFERENCES Modules(ModuleID)
);

INSERT INTO Users (LastName, FirstName, Email, Password) VALUES ('Majewski', 'Maciej', 'majewski.maciej@maciej.pl', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855');
INSERT INTO Users (LastName, FirstName, Email, Password) VALUES ('Lesiak', 'Patryk', 'patryk.lesiak@patryk.pl', 'e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855');

INSERT INTO Projects(Title, Description, LeaderID) VALUES('Test project1', 'More description about project', 1);
INSERT INTO Projects(Title, Description, LeaderID) VALUES('Test project2', 'More description about project', 1);
INSERT INTO Projects(Title, Description, LeaderID) VALUES('Test project3', 'More description about project', 1);

INSERT INTO Technologies(Name) VALUES('Java');
INSERT INTO Technologies(Name) VALUES('JavaEE');
INSERT INTO Technologies(Name) VALUES('C');
INSERT INTO Technologies(Name) VALUES('C++');
INSERT INTO Technologies(Name) VALUES('C#');
INSERT INTO Technologies(Name) VALUES('Python');
INSERT INTO Technologies(Name) VALUES('Ruby');
INSERT INTO Technologies(Name) VALUES('SQL');

INSERT INTO Modules(ProjectID, Title, Description, ReadmeLink) VALUES (1, 'Baza danych SQL', 'Zrobic baze danych', 'http://www.google.pl');

INSERT INTO Users_to_Technologies(UserID, TechnologyID) VALUES (1, 2);
INSERT INTO Users_to_Technologies (UserID, TechnologyID) VALUES (2, 1);

INSERT INTO Technologies_to_Modules(TechnologyID, ModuleID) VALUES(8, 1);

INSERT INTO Users_to_Modules(UserID, ModuleID) VALUES(1,1);
