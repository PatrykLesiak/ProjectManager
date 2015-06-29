DROP VIEW v_user_role;
DROP VIEW v_Modules_to_Project;
DROP TABLE InvitesToProjects;
DROP TABLE AsksForCollaboration;
DROP TABLE Users_to_Technologies;
DROP TABLE Users_to_Modules;
DROP TABLE Technologies_to_Modules;
DROP TABLE Technologies;
DROP TABLE Modules;
DROP TABLE Projects;
DROP TABLE USERS_GROUPS;
DROP TABLE Users;
DROP TABLE Groups;

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
Recruting boolean DEFAULT True,
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

CREATE TABLE Groups
(
group_id int PRIMARY KEY NOT NULL,
group_name varchar(20) NOT NULL
);

CREATE TABLE USERS_GROUPS
(
Id int PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
user_id int NOT NULL,
group_id int NOT NULL, 
FOREIGN KEY (group_id) REFERENCES Groups (group_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (user_id) REFERENCES Users (UserID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE InvitesToProjects(
Id int PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
UserID int NOT NULL,
ModuleID int NOT NULL,
FOREIGN KEY (ModuleID) REFERENCES Modules (ModuleID) ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (UserID) REFERENCES Users (UserID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE AsksForCollaboration(
Id int PRIMARY KEY NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
UserID int NOT NULL,
ModuleID int NOT NULL,
FOREIGN KEY (ModuleID) REFERENCES Modules (ModuleID) ON DELETE NO ACTION ON UPDATE NO ACTION,
FOREIGN KEY (UserID) REFERENCES Users (UserID) ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE VIEW v_user_role AS
SELECT  u.Email, u.Password, g.group_name
 FROM USERS_GROUPS ug
 INNER JOIN Users u ON u.UserID = ug.user_id
 INNER JOIN Groups g ON g.group_id =  ug.group_id; 

CREATE VIEW v_Modules_to_Project AS
 SELECT project.ProjectID, project.Title AS ProjectTitle, module.ModuleID, module.Title AS ModuleTitle 
 FROM Projects project, Modules module 
 WHERE project.PROJECTID = module.ProjectID; 

INSERT INTO Users (LastName, FirstName, Email, Password, Readmelink, AvatarLink) VALUES 
('Majewski', 'Maciej', 'majewski.maciej@maciej.pl',
 '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',
'', 'http://www.adtechnology.co.uk/images/UGM-default-user.png');

INSERT INTO Users (LastName, FirstName, Email, Password, Readmelink, AvatarLink) VALUES 
('Lesiak', 'Patryk', 'patryk.lesiak@patryk.pl',
 '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',
'', 'https://cdn0.iconfinder.com/data/icons/iVista2/256/User.png');

INSERT INTO Projects(Title, Description, LeaderID, PictureLink, RreadmeLink) VALUES('Open source lightbulb',
 'We are making open source lightbulb ! Join us and say "no more" to lightbulb scam', 1,
'http://ritterlumber.net/wp-content/uploads/2013/12/incandescent-lightbulb.jpg',
'http://wklej.org/id/1746849/txt/');
INSERT INTO Projects(Title, Description, LeaderID, PictureLink, RreadmeLink) VALUES('Jarvis AI',
'An AI from Iron-Man. We are making future "butler AI"', 1,
'http://media.comicbook.com/wp-content/uploads/2011/11/jarvis-avengers.jpg',
'http://wklej.org/id/1746851/txt/');
INSERT INTO Projects(Title, Description, LeaderID, PictureLink, RreadmeLink) VALUES('Pandas',
'pandas is an open source, BSD-licensed library providing high-performance, easy-to-use data structures and data analysis tools for the Python programming language.', 1,
'http://assets.worldwildlife.org/photos/144/images/original/Giant_Panda_Hero_image_(c)_Michel_Gunther_WWF_Canon.jpg?1345515244',
'https://raw.githubusercontent.com/pydata/pandas/master/README.md');

INSERT INTO Technologies(Name) VALUES('Java');
INSERT INTO Technologies(Name) VALUES('JavaEE');
INSERT INTO Technologies(Name) VALUES('C');
INSERT INTO Technologies(Name) VALUES('C++');
INSERT INTO Technologies(Name) VALUES('C#');
INSERT INTO Technologies(Name) VALUES('Python');
INSERT INTO Technologies(Name) VALUES('Ruby');
INSERT INTO Technologies(Name) VALUES('SQL');

INSERT INTO Modules(ProjectID, Title, Description, ReadmeLink) VALUES (1, 'Baza danych SQL', 'Zrobic baze danych', '');
INSERT INTO Modules(ProjectID, Title, Description, ReadmeLink, Recruting) VALUES (1, 'Frontending', 'Make frontend ui', '', false);

INSERT INTO Modules(ProjectID, Title, Description, ReadmeLink) VALUES (2, 'Making backend', 'Make backend', '');
INSERT INTO Modules(ProjectID, Title, Description, ReadmeLink, Recruting) VALUES (2, 'Frontend', 'Make frontend ui', '', false);

INSERT INTO Modules(ProjectID, Title, Description, ReadmeLink) VALUES (3, 'Create api', 'Make api', '');
INSERT INTO Modules(ProjectID, Title, Description, ReadmeLink, Recruting) VALUES (3, 'C++ inserts', 'Make inserts', '', false);

INSERT INTO Users_to_Technologies(UserID, TechnologyID) VALUES (1, 2);
INSERT INTO Users_to_Technologies(UserID, TechnologyID) VALUES (1, 3);
INSERT INTO Users_to_Technologies(UserID, TechnologyID) VALUES (1, 4);
INSERT INTO Users_to_Technologies (UserID, TechnologyID) VALUES (2, 1);
INSERT INTO Users_to_Technologies (UserID, TechnologyID) VALUES (2, 6);
INSERT INTO Users_to_Technologies (UserID, TechnologyID) VALUES (2, 7);

INSERT INTO Technologies_to_Modules(TechnologyID, ModuleID) VALUES(8, 1);
INSERT INTO Technologies_to_Modules(TechnologyID, ModuleID) VALUES(2, 1);
INSERT INTO Technologies_to_Modules(TechnologyID, ModuleID) VALUES(3, 1);

INSERT INTO Technologies_to_Modules(TechnologyID, ModuleID) VALUES(7, 2);

INSERT INTO Technologies_to_Modules(TechnologyID, ModuleID) VALUES(2, 3);
INSERT INTO Technologies_to_Modules(TechnologyID, ModuleID) VALUES(5, 3);

INSERT INTO Technologies_to_Modules(TechnologyID, ModuleID) VALUES(6, 4);
INSERT INTO Technologies_to_Modules(TechnologyID, ModuleID) VALUES(1, 4);

INSERT INTO Technologies_to_Modules(TechnologyID, ModuleID) VALUES(8, 5);
INSERT INTO Technologies_to_Modules(TechnologyID, ModuleID) VALUES(2, 5);

INSERT INTO Technologies_to_Modules(TechnologyID, ModuleID) VALUES(3, 6);
INSERT INTO Technologies_to_Modules(TechnologyID, ModuleID) VALUES(4, 6);

INSERT INTO Users_to_Modules(UserID, ModuleID) VALUES(1,1);
INSERT INTO Users_to_Modules(UserID, ModuleID) VALUES(1,3);
INSERT INTO Users_to_Modules(UserID, ModuleID) VALUES(2,2);
INSERT INTO Users_to_Modules(UserID, ModuleID) VALUES(2,1);
INSERT INTO Users_to_Modules(UserID, ModuleID) VALUES(1,4);
INSERT INTO Users_to_Modules(UserID, ModuleID) VALUES(1,5);
INSERT INTO Users_to_Modules(UserID, ModuleID) VALUES(2,6);
INSERT INTO Users_to_Modules(UserID, ModuleID) VALUES(2,3);

INSERT INTO Groups(group_id,group_name) VALUES (1,'loggedUser');
INSERT INTO USERS_GROUPS(user_id, group_id) VALUES (1, 1),
												   (2, 1);
								
