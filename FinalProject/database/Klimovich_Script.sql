-- MySQL Script generated by MySQL Workbench
-- Sun Apr 18 13:40:55 2021
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema KlimovichProject
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema KlimovichProject
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `KlimovichProject` DEFAULT CHARACTER SET utf8 ;
USE `KlimovichProject` ;

-- -----------------------------------------------------
-- Table `KlimovichProject`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KlimovichProject`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(128) NOT NULL,
  `role` ENUM("ADMIN", "USER") NOT NULL,
  `status` ENUM("BLOCKED", "ENABLE", "NOT_CONFIRMED") NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `patronymic` VARCHAR(45) NOT NULL,
  `balance` DOUBLE NOT NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KlimovichProject`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KlimovichProject`.`orders` (
  `order_id` INT NOT NULL AUTO_INCREMENT,
  `creation_date` BIGINT(255) NOT NULL,
  `closing_date` BIGINT(255) NOT NULL,
  `order_status` ENUM("UNDER_CONSIDERATION", "DENIED", "PRODUCED") NOT NULL,
  `user_id_fk` INT NOT NULL,
  PRIMARY KEY (`order_id`),
  INDEX `fk_orders_users1_idx` (`user_id_fk` ASC) VISIBLE,
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`user_id_fk`)
    REFERENCES `KlimovichProject`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KlimovichProject`.`images`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KlimovichProject`.`images` (
  `image_id` INT NOT NULL AUTO_INCREMENT,
  `image_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`image_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KlimovichProject`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KlimovichProject`.`products` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(64) NOT NULL,
  `price` DOUBLE NOT NULL,
  `facture` VARCHAR(45) NOT NULL,
  `application_area` VARCHAR(120) NULL,
  `image_id_fk` INT NOT NULL,
  PRIMARY KEY (`product_id`),
  INDEX `fk_products_images1_idx` (`image_id_fk` ASC) VISIBLE,
  CONSTRAINT `fk_products_images1`
    FOREIGN KEY (`image_id_fk`)
    REFERENCES `KlimovichProject`.`images` (`image_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KlimovichProject`.`orderitems`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KlimovichProject`.`orderitems` (
  `orderitem_id` INT NOT NULL AUTO_INCREMENT,
  `product_id_fk` INT NOT NULL,
  `order_id_fk` INT NOT NULL,
  PRIMARY KEY (`orderitem_id`),
  INDEX `fk_order_item_products1_idx` (`product_id_fk` ASC) VISIBLE,
  INDEX `fk_order_item_orders1_idx` (`order_id_fk` ASC) VISIBLE,
  CONSTRAINT `fk_order_item_products1`
    FOREIGN KEY (`product_id_fk`)
    REFERENCES `KlimovichProject`.`products` (`product_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_order_item_orders1`
    FOREIGN KEY (`order_id_fk`)
    REFERENCES `KlimovichProject`.`orders` (`order_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `KlimovichProject`.`baskets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `KlimovichProject`.`baskets` (
  `basket_id` INT NOT NULL AUTO_INCREMENT,
  `user_id_fk` INT NOT NULL,
  `product_id_fk` INT NOT NULL,
  PRIMARY KEY (`basket_id`),
  INDEX `fk_basket_users1_idx` (`user_id_fk` ASC) VISIBLE,
  INDEX `fk_basket_products1_idx` (`product_id_fk` ASC) VISIBLE,
  CONSTRAINT `fk_basket_users1`
    FOREIGN KEY (`user_id_fk`)
    REFERENCES `KlimovichProject`.`users` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_basket_products1`
    FOREIGN KEY (`product_id_fk`)
    REFERENCES `KlimovichProject`.`products` (`product_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
