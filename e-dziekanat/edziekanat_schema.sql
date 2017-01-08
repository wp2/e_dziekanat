-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Wersja serwera:               10.1.18-MariaDB - mariadb.org binary distribution
-- Serwer OS:                    Win64
-- HeidiSQL Wersja:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Zrzut struktury bazy danych edziekanat
CREATE DATABASE IF NOT EXISTS `edziekanat` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `edziekanat`;


-- Zrzut struktury tabela edziekanat.dydaktyk
CREATE TABLE IF NOT EXISTS `dydaktyk` (
  `Id` int(11) NOT NULL,
  `Imie` varchar(50) NOT NULL,
  `Nazwisko` varchar(50) NOT NULL,
  `Login` varchar(50) NOT NULL,
  `Haslo` varchar(50) NOT NULL,
  `Plec` varchar(1) NOT NULL,
  `DataUrodzenia` date NOT NULL,
  `Adres` varchar(50) NOT NULL,
  `Tytul` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Zrzut struktury tabela edziekanat.formakursu
CREATE TABLE IF NOT EXISTS `formakursu` (
  `Id` int(11) NOT NULL,
  `IdKurs` varchar(50) NOT NULL,
  `Nazwa` varchar(50) NOT NULL,
  `Typ` varchar(3) NOT NULL,
  `Waga` float NOT NULL,
  `LiczbaGodzin` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKIdKurs` (`IdKurs`),
  CONSTRAINT `FKIdKurs` FOREIGN KEY (`IdKurs`) REFERENCES `kurs` (`Nazwa`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Zrzut struktury tabela edziekanat.grupacw
CREATE TABLE IF NOT EXISTS `grupacw` (
  `Id` varchar(50) NOT NULL,
  `IdGrupaWyk` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `IdGrupaWyk` (`IdGrupaWyk`),
  CONSTRAINT `IdGrupaWyk` FOREIGN KEY (`IdGrupaWyk`) REFERENCES `grupawyk` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Zrzut struktury tabela edziekanat.grupalab
CREATE TABLE IF NOT EXISTS `grupalab` (
  `Id` varchar(50) NOT NULL,
  `Licznosc` int(11) NOT NULL,
  `IdGrupaCw` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKIdGrupaCw` (`IdGrupaCw`),
  CONSTRAINT `FKIdGrupaCw` FOREIGN KEY (`IdGrupaCw`) REFERENCES `grupacw` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Zrzut struktury tabela edziekanat.grupawyk
CREATE TABLE IF NOT EXISTS `grupawyk` (
  `Id` varchar(50) NOT NULL,
  `IdRocznik` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKIdRocznik` (`IdRocznik`),
  CONSTRAINT `FKIdRocznik` FOREIGN KEY (`IdRocznik`) REFERENCES `rocznik` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Zrzut struktury tabela edziekanat.kierunek
CREATE TABLE IF NOT EXISTS `kierunek` (
  `Id` int(11) NOT NULL,
  `Nazwa` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Zrzut struktury tabela edziekanat.kurs
CREATE TABLE IF NOT EXISTS `kurs` (
  `Nazwa` varchar(50) NOT NULL,
  `IdSemestr` int(11) NOT NULL,
  `ECTS` int(11) NOT NULL,
  PRIMARY KEY (`Nazwa`),
  KEY `FKIdSemestrKurs` (`IdSemestr`),
  CONSTRAINT `FKIdSemestrKurs` FOREIGN KEY (`IdSemestr`) REFERENCES `semestr` (`NumerSemestru`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Zrzut struktury tabela edziekanat.ocena
CREATE TABLE IF NOT EXISTS `ocena` (
  `Id` int(11) NOT NULL,
  `IdStudent` bigint(20) DEFAULT NULL,
  `IdProwadzacyForme` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKIdProwadzacyZajecia` (`IdProwadzacyForme`),
  KEY `FKIdStudentOcena` (`IdStudent`),
  CONSTRAINT `FKIdProwadzacyZajecia` FOREIGN KEY (`IdProwadzacyForme`) REFERENCES `prowadzacyforme` (`Id`),
  CONSTRAINT `FKIdStudentOcena` FOREIGN KEY (`IdStudent`) REFERENCES `student` (`Album`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Zrzut struktury tabela edziekanat.prowadzacyforme
CREATE TABLE IF NOT EXISTS `prowadzacyforme` (
  `Id` int(11) NOT NULL,
  `IdDydaktyk` int(11) NOT NULL,
  `IdForma` int(11) NOT NULL,
  `IdGrupaL` varchar(50) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKIdDydaktyk` (`IdDydaktyk`),
  KEY `FKIdForma` (`IdForma`),
  KEY `FKIdGrupaL` (`IdGrupaL`),
  CONSTRAINT `FKIdDydaktyk` FOREIGN KEY (`IdDydaktyk`) REFERENCES `dydaktyk` (`Id`),
  CONSTRAINT `FKIdForma` FOREIGN KEY (`IdForma`) REFERENCES `formakursu` (`Id`),
  CONSTRAINT `FKIdGrupaL` FOREIGN KEY (`IdGrupaL`) REFERENCES `grupalab` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Zrzut struktury tabela edziekanat.rocznik
CREATE TABLE IF NOT EXISTS `rocznik` (
  `Id` int(11) NOT NULL,
  `IdSemestr` int(11) NOT NULL,
  `Rocznik` varchar(50) NOT NULL,
  `DataPoczatkowa` date NOT NULL,
  `DataKoncowa` date NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKIdSemestr` (`IdSemestr`),
  CONSTRAINT `FKIdSemestr` FOREIGN KEY (`IdSemestr`) REFERENCES `semestr` (`NumerSemestru`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Zrzut struktury tabela edziekanat.semestr
CREATE TABLE IF NOT EXISTS `semestr` (
  `NumerSemestru` int(11) NOT NULL,
  `TypSemestru` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`NumerSemestru`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Zrzut struktury tabela edziekanat.specjalnosc
CREATE TABLE IF NOT EXISTS `specjalnosc` (
  `Id` int(11) NOT NULL,
  `Nazwa` varchar(50) NOT NULL,
  `IdKierunek` int(11) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `FKIdSKierunek` (`IdKierunek`),
  CONSTRAINT `FKIdSKierunek` FOREIGN KEY (`IdKierunek`) REFERENCES `kierunek` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.


-- Zrzut struktury tabela edziekanat.student
CREATE TABLE IF NOT EXISTS `student` (
  `Album` bigint(20) NOT NULL AUTO_INCREMENT,
  `Imie` varchar(50) NOT NULL,
  `Nazwisko` varchar(50) NOT NULL,
  `Login` varchar(50) NOT NULL,
  `Haslo` varchar(50) NOT NULL,
  `Plec` varchar(1) NOT NULL,
  `DataUrodzenia` date NOT NULL,
  `Adres` varchar(50) NOT NULL,
  `IdKierunek` int(11) NOT NULL,
  `IdGrupaLab` varchar(50) NOT NULL,
  PRIMARY KEY (`Album`),
  KEY `FKIdKierunek` (`IdKierunek`),
  KEY `FKIdGrupaLab` (`IdGrupaLab`),
  CONSTRAINT `FKIdGrupaLab` FOREIGN KEY (`IdGrupaLab`) REFERENCES `grupalab` (`Id`),
  CONSTRAINT `FKIdKierunek` FOREIGN KEY (`IdKierunek`) REFERENCES `kierunek` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Data exporting was unselected.
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
