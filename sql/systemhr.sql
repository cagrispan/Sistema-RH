-- --------------------------------------------------------
-- Servidor:                     localhost
-- Versão do servidor:           5.6.27-log - MySQL Community Server (GPL)
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              9.3.0.4984
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura do banco de dados para sistemarh
CREATE DATABASE IF NOT EXISTS `sistemarh` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sistemarh`;


-- Copiando estrutura para tabela sistemarh.company_system
CREATE TABLE IF NOT EXISTS `company_system` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistemarh.company_system: ~10 rows (aproximadamente)
/*!40000 ALTER TABLE `company_system` DISABLE KEYS */;
INSERT INTO `company_system` (`id`, `name`) VALUES
	(1, 'Financeiro'),
	(2, 'Jurídico'),
	(3, 'Executivo'),
	(4, 'Marketing'),
	(5, 'Compras'),
	(6, 'Vendas'),
	(7, 'Administrativo'),
	(8, 'Operacional'),
	(9, 'Recursos Humanos'),
	(10, 'Tecnologia da Informação');
/*!40000 ALTER TABLE `company_system` ENABLE KEYS */;


-- Copiando estrutura para tabela sistemarh.department
CREATE TABLE IF NOT EXISTS `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT '0',
  `idManager` int(10) DEFAULT '0',
  `idDirector` int(10) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistemarh.department: ~10 rows (aproximadamente)
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` (`id`, `name`, `idManager`, `idDirector`) VALUES
	(1, 'Financeiro', 0, 1),
	(2, 'Jurídico', 0, 1),
	(3, 'Executivo', 0, 1),
	(4, 'Marketing', 0, 0),
	(5, 'Compras', 0, 0),
	(6, 'Vendas', 0, 2),
	(7, 'Administrativo', 0, 2),
	(8, 'Operacional', 0, 2),
	(9, 'Recursos Humanos', 1, 0),
	(10, 'Tecnologia da Informação', 2, 0);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;


-- Copiando estrutura para tabela sistemarh.director
CREATE TABLE IF NOT EXISTS `director` (
  `idEmployee` int(10) unsigned NOT NULL,
  `idDirector` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idDirector`),
  KEY `fkEmployeeDirector` (`idEmployee`),
  CONSTRAINT `fkEmployeeDirector` FOREIGN KEY (`idEmployee`) REFERENCES `employee` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistemarh.director: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `director` DISABLE KEYS */;
INSERT INTO `director` (`idEmployee`, `idDirector`) VALUES
	(1, 1),
	(2, 2);
/*!40000 ALTER TABLE `director` ENABLE KEYS */;


-- Copiando estrutura para tabela sistemarh.employee
CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `rg` varchar(50) DEFAULT NULL,
  `cpf` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `idSalary` int(11) DEFAULT NULL,
  `idDepartment` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idDepartment` (`idDepartment`),
  KEY `idSalary` (`idSalary`),
  CONSTRAINT `idDepartment` FOREIGN KEY (`idDepartment`) REFERENCES `department` (`id`),
  CONSTRAINT `idSalary` FOREIGN KEY (`idSalary`) REFERENCES `salary` (`idSalary`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistemarh.employee: ~4 rows (aproximadamente)
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` (`id`, `name`, `surname`, `rg`, `cpf`, `phone`, `password`, `idSalary`, `idDepartment`) VALUES
	(1, 'Lucas', 'Kindinger', '4.032.894-40', '099.643.419-46', '(41) 99910215', '12345678', 1, 6),
	(2, 'Carlos', 'Grispan', '41.875.789-6', '807.032.036-22', '(41) 98337122', '12345678', 1, 1),
	(3, 'Alisson', 'Krul', '4.032.894-4', '740.729.695-24', '(41) 73622812', '12345678', 4, 1),
	(4, 'Bruno', 'Henrique', '91.122.534-1', '865.227.167-47', '(41) 12873212', '12345678', 4, 1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;


-- Copiando estrutura para tabela sistemarh.manager
CREATE TABLE IF NOT EXISTS `manager` (
  `idEmployee` int(10) unsigned NOT NULL,
  `idManager` int(11) NOT NULL AUTO_INCREMENT,
  `idDepartment` int(11) DEFAULT NULL,
  PRIMARY KEY (`idManager`),
  KEY `idEmployee` (`idEmployee`),
  CONSTRAINT `fkIdEmployee` FOREIGN KEY (`idEmployee`) REFERENCES `employee` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistemarh.manager: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` (`idEmployee`, `idManager`, `idDepartment`) VALUES
	(3, 1, 9),
	(4, 2, 10);
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;


-- Copiando estrutura para tabela sistemarh.office
CREATE TABLE IF NOT EXISTS `office` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistemarh.office: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `office` DISABLE KEYS */;
INSERT INTO `office` (`id`, `name`) VALUES
	(1, 'Diretor'),
	(2, 'Gerente'),
	(3, 'Analista'),
	(4, 'Programador'),
	(5, 'Auxiliar de Limpeza');
/*!40000 ALTER TABLE `office` ENABLE KEYS */;


-- Copiando estrutura para tabela sistemarh.permission
CREATE TABLE IF NOT EXISTS `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idEmployee` int(10) unsigned NOT NULL,
  `idCompanySystem` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fkPermEmployee` (`idEmployee`),
  KEY `fkPermSystem` (`idCompanySystem`),
  CONSTRAINT `fkPermEmployee` FOREIGN KEY (`idEmployee`) REFERENCES `employee` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fkPermSystem` FOREIGN KEY (`idCompanySystem`) REFERENCES `company_system` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistemarh.permission: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `permission` DISABLE KEYS */;
INSERT INTO `permission` (`id`, `idEmployee`, `idCompanySystem`) VALUES
	(1, 1, 1),
	(2, 2, 2),
	(3, 1, 2);
/*!40000 ALTER TABLE `permission` ENABLE KEYS */;


-- Copiando estrutura para tabela sistemarh.salary
CREATE TABLE IF NOT EXISTS `salary` (
  `idSalary` int(11) NOT NULL AUTO_INCREMENT,
  `idOffice` int(11) NOT NULL,
  `level` tinyint(4) NOT NULL DEFAULT '1',
  `salary` float DEFAULT NULL,
  PRIMARY KEY (`idSalary`),
  KEY `idOffice` (`idOffice`),
  CONSTRAINT `idOffice` FOREIGN KEY (`idOffice`) REFERENCES `office` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela sistemarh.salary: ~13 rows (aproximadamente)
/*!40000 ALTER TABLE `salary` DISABLE KEYS */;
INSERT INTO `salary` (`idSalary`, `idOffice`, `level`, `salary`) VALUES
	(1, 1, 3, 13000),
	(2, 1, 2, 12000),
	(3, 1, 1, 11000),
	(4, 2, 3, 10000),
	(5, 2, 2, 9000),
	(6, 2, 1, 8000),
	(7, 3, 3, 7000),
	(8, 3, 2, 6000),
	(9, 3, 1, 5000),
	(10, 4, 3, 4000),
	(11, 4, 2, 3000),
	(12, 4, 1, 2000),
	(13, 5, 1, 1000);
/*!40000 ALTER TABLE `salary` ENABLE KEYS */;


-- Copiando estrutura para trigger sistemarh.director_before_delete
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `director_before_delete` BEFORE DELETE ON `director` FOR EACH ROW BEGIN
	UPDATE department SET idDirector=0 WHERE idDirector=OLD.idDirector;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;


-- Copiando estrutura para trigger sistemarh.employee_before_delete
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `employee_before_delete` BEFORE DELETE ON `employee` FOR EACH ROW BEGIN
	/* Clear references to Director in Departments*/	
	UPDATE department	
	SET idDirector=0 
	
	WHERE 
		idDirector=
		(
			SELECT idDirector
				FROM Director
				WHERE
					idEmployee = OLD.id
		);
		
		
	/* Clear references to Manager in Departments*/	
	UPDATE department	
	SET idManager=0 
	
	WHERE 
		idManager=
		(
			SELECT idManager
				FROM Manager
				WHERE
					idEmployee = OLD.id
		);					
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;


-- Copiando estrutura para trigger sistemarh.employee_before_update
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `employee_before_update` BEFORE UPDATE ON `employee` FOR EACH ROW BEGIN
	if (OLD.idSalary<4 AND NEW.idSalary>3) then 
		delete from director 
		where idEmployee=OLD.id;
	ELSEIF (OLD.idSalary<7 AND NEW.idSalary>6) then
		DELETE from manager 
		where idEmployee=OLD.id;
	end if; 
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;


-- Copiando estrutura para trigger sistemarh.manager_before_delete
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `manager_before_delete` BEFORE DELETE ON `manager` FOR EACH ROW BEGIN
	/* Definir a coluna 'idManager' em  'deparment' como nulo*/
	
	UPDATE department SET idManager=0 WHERE idManager=OLD.idManager;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
