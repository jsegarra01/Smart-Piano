CREATE DATABASE piano_OOPD;

DROP TABLE IF EXISTS SongPlaylists CASCADE;
DROP TABLE IF EXISTS SongStatisticsGeneral CASCADE;
DROP TABLE IF EXISTS SongStatisticsHourly CASCADE;
DROP TABLE IF EXISTS Playlist CASCADE;
DROP TABLE IF EXISTS Song CASCADE;
DROP TABLE IF EXISTS User CASCADE;

CREATE TABLE User(
    username VARCHAR(255) PRIMARY KEY,
    email Varchar(255),
    password Varchar(255)
);

CREATE TABLE Song(
    songId SERIAL PRIMARY KEY,
    songName Varchar(255),
    authorsName Varchar(255),
    duration FLOAT,
    recordingDate DATE,
    publicBoolean BOOLEAN,
    songFile VARCHAR(255),
    username VARCHAR(255),
    FOREIGN KEY (username) REFERENCES User(username)
);

CREATE TABLE SongStatisticsGeneral(
    songId BIGINT UNSIGNED PRIMARY KEY,
    timesPlayed INT,
    minutesPlayed INT,
    numberDifferentUsers INT,
    FOREIGN KEY (songId) REFERENCES Song(songId)
);

CREATE TABLE SongStatisticsHourly(
    hour INT PRIMARY KEY,
    songId BIGINT UNSIGNED,
    timesPlayed INT,
    minutesListened INT,
    FOREIGN KEY (songId) REFERENCES Song(songId)
);

CREATE TABLE Playlist(
    playlistId SERIAL PRIMARY KEY,
    playlistName VARCHAR(255),
    username VARCHAR(255),
    FOREIGN KEY (username) REFERENCES User(username)
);

CREATE TABLE SongPlaylists(
    songId BIGINT UNSIGNED,
    playlistId BIGINT UNSIGNED PRIMARY KEY,
    FOREIGN KEY (songId) REFERENCES Song(songId),
    FOREIGN KEY (playlistId) REFERENCES Playlist(playlistId)
);
#INSERT USERS
INSERT INTO User VALUES ('blayaiai','alex.blay@students.salle.url.edu','viscaelbarca');
INSERT INTO User VALUES ('MEGAKI295','josep.segarra@students.salle.url.edu','admin');
INSERT INTO User VALUES ('sergiENP','sergi.vives@students.salle.url.edu','password');
INSERT INTO User VALUES ('styopartist','stepan.batllori@students.salle.url.edu', 'madrepatriaRussia');
INSERT INTO User VALUES ('lauranuez','laura.nuez@students.salle.url.edu', 'vivaespanya');
SELECT * FROM User;

