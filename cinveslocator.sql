SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema cinveslocator
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cinveslocator` ;
CREATE SCHEMA IF NOT EXISTS `cinveslocator` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci ;
USE `cinveslocator` ;

-- -----------------------------------------------------
-- Table `cinveslocator`.`type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinveslocator`.`type` (
  `idtype` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idtype`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinveslocator`.`agent`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinveslocator`.`agent` (
  `idagente` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `device` VARCHAR(45) NULL,
  `last_login` VARCHAR(45) NULL,
  `type_idtype` INT NOT NULL,
  PRIMARY KEY (`idagente`),
  INDEX `fk_agent_type1_idx` (`type_idtype` ASC),
  CONSTRAINT `fk_agent_type1`
    FOREIGN KEY (`type_idtype`)
    REFERENCES `cinveslocator`.`type` (`idtype`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cinveslocator`.`location`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `cinveslocator`.`location` (
  `idlocation` INT NOT NULL AUTO_INCREMENT,
  `timestamp` VARCHAR(45) NULL,
  `coordinates` VARCHAR(45) NOT NULL,
  `agente_idagente` INT NOT NULL,
  PRIMARY KEY (`idlocation`),
  INDEX `fk_location_agente_idx` (`agente_idagente` ASC),
  CONSTRAINT `fk_location_agente`
    FOREIGN KEY (`agente_idagente`)
    REFERENCES `cinveslocator`.`agent` (`idagente`)
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
INSERT INTO `cinveslocator`.`type` (`idtype`, `type`) VALUES (1, 'Agent');

COMMIT;


-- -----------------------------------------------------
-- Data for table `cinveslocator`.`agent`
-- -----------------------------------------------------
START TRANSACTION;
USE `cinveslocator`;
INSERT INTO `cinveslocator`.`agent` (`idagente`, `name`, `device`, `last_login`, `type_idtype`) VALUES (1, 'HECTOR', 'mobile', '2015-03-13', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `cinveslocator`.`location`
-- -----------------------------------------------------
START TRANSACTION;
USE `cinveslocator`;
INSERT INTO `cinveslocator`.`location` (`idlocation`, `timestamp`, `coordinates`, `agente_idagente`) VALUES (NULL, '2015-28-04 11:00:00', '120 200 125', 1);

COMMIT;

