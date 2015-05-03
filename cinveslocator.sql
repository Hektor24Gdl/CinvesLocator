SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `cinveslocator` ;
CREATE SCHEMA IF NOT EXISTS `cinveslocator` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci ;
USE `cinveslocator` ;

-- -----------------------------------------------------
-- Table `cinveslocator`.`type`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cinveslocator`.`type` (
  `idtype` INT NOT NULL AUTO_INCREMENT ,
  `type` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`idtype`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinveslocator`.`agent`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cinveslocator`.`agent` (
  `idagente` INT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NOT NULL ,
  `device` VARCHAR(45) NULL ,
  `last_login` VARCHAR(45) NULL ,
  `type_idtype` INT NOT NULL ,
  PRIMARY KEY (`idagente`) ,
  INDEX `fk_agent_type1_idx` (`type_idtype` ASC) ,
  CONSTRAINT `fk_agent_type1`
    FOREIGN KEY (`type_idtype` )
    REFERENCES `cinveslocator`.`type` (`idtype` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinveslocator`.`location`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `cinveslocator`.`location` (
  `idlocation` INT NOT NULL AUTO_INCREMENT ,
  `timestamp` VARCHAR(45) NULL ,
  `coordinates` VARCHAR(45) NOT NULL ,
  `agente_idagente` INT NOT NULL ,
  PRIMARY KEY (`idlocation`) ,
  INDEX `fk_location_agente_idx` (`agente_idagente` ASC) ,
  CONSTRAINT `fk_location_agente`
    FOREIGN KEY (`agente_idagente` )
    REFERENCES `cinveslocator`.`agent` (`idagente` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `cinveslocator`.`type`
-- -----------------------------------------------------
START TRANSACTION;
USE `cinveslocator`;
INSERT INTO `cinveslocator`.`type` (`idtype`, `type`) VALUES (1, 'Persona');
INSERT INTO `cinveslocator`.`type` (`idtype`, `type`) VALUES (2, 'Impresora');
INSERT INTO `cinveslocator`.`type` (`idtype`, `type`) VALUES (3, 'Laboratorio');

COMMIT;

-- -----------------------------------------------------
-- Data for table `cinveslocator`.`agent`
-- -----------------------------------------------------
START TRANSACTION;
USE `cinveslocator`;
INSERT INTO `cinveslocator`.`agent` (`idagente`, `name`, `device`, `last_login`, `type_idtype`) VALUES (1, 'HECTOR', 'mobile', '2015-03-13', 1);
INSERT INTO `cinveslocator`.`agent` (`idagente`, `name`, `device`, `last_login`, `type_idtype`) VALUES (2, 'Celeste', 'mobile', '2015-05-03 12:07:00', 1);
INSERT INTO `cinveslocator`.`agent` (`idagente`, `name`, `device`, `last_login`, `type_idtype`) VALUES (3, 'Andres', 'mobile', '2015-05-03 12:07:00', 1);
INSERT INTO `cinveslocator`.`agent` (`idagente`, `name`, `device`, `last_login`, `type_idtype`) VALUES (4, 'Gibran', 'mobile', '2015-05-03 12:07:00', 1);
INSERT INTO `cinveslocator`.`agent` (`idagente`, `name`, `device`, `last_login`, `type_idtype`) VALUES (5, 'Impresora1', 'mobile', '2015-05-03 12:07:00', 2);
INSERT INTO `cinveslocator`.`agent` (`idagente`, `name`, `device`, `last_login`, `type_idtype`) VALUES (6, 'Impresora2', 'mobile', '2015-05-03 12:07:00', 2);
INSERT INTO `cinveslocator`.`agent` (`idagente`, `name`, `device`, `last_login`, `type_idtype`) VALUES (7, 'Laboratorio Compu', 'mobile', '2015-05-03 12:07:00', 3);

COMMIT;

-- -----------------------------------------------------
-- Data for table `cinveslocator`.`location`
-- -----------------------------------------------------
START TRANSACTION;
USE `cinveslocator`;
INSERT INTO `cinveslocator`.`location` (`idlocation`, `timestamp`, `coordinates`, `agente_idagente`) VALUES (1, '2015-28-04 11:00:00', '120 200 125', 1);
INSERT INTO `cinveslocator`.`location` (`idlocation`, `timestamp`, `coordinates`, `agente_idagente`) VALUES (2, '2015-28-04 11:00:00', '1,50,50', 2);
INSERT INTO `cinveslocator`.`location` (`idlocation`, `timestamp`, `coordinates`, `agente_idagente`) VALUES (3, '2015-28-04 11:00:00', '1,800,800', 3);
INSERT INTO `cinveslocator`.`location` (`idlocation`, `timestamp`, `coordinates`, `agente_idagente`) VALUES (4, '2015-28-04 11:00:00', '1,100,100', 4);
INSERT INTO `cinveslocator`.`location` (`idlocation`, `timestamp`, `coordinates`, `agente_idagente`) VALUES (5, '2015-28-04 11:00:00', '1,1000,1000', 5);
INSERT INTO `cinveslocator`.`location` (`idlocation`, `timestamp`, `coordinates`, `agente_idagente`) VALUES (6, '2015-28-04 11:00:00', '1,450,1000', 6);
INSERT INTO `cinveslocator`.`location` (`idlocation`, `timestamp`, `coordinates`, `agente_idagente`) VALUES (7, '2015-28-04 11:00:00', '1,800,1000', 7);

COMMIT;
