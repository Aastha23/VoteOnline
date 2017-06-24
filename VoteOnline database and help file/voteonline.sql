/*
SQLyog Enterprise - MySQL GUI v7.02 
MySQL - 5.0.67-community-nt : Database - voteonline
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`voteonline` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `voteonline`;

/*Table structure for table `candidates` */

DROP TABLE IF EXISTS `candidates`;

CREATE TABLE `candidates` (
  `OrganisationNo` int(10) NOT NULL,
  `AdmissionNo` varchar(10) NOT NULL,
  `Position` varchar(10) default NULL,
  `Name` varchar(30) default NULL,
  `Gender` varchar(6) default NULL,
  `DOB` date default NULL,
  `Email` varchar(30) default NULL,
  `Motto` varchar(60) default NULL,
  `Comment` longblob,
  `Password` varchar(30) default NULL,
  `Usertype` varchar(12) default NULL,
  PRIMARY KEY  (`AdmissionNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `candidates` */

insert  into `candidates`(`OrganisationNo`,`AdmissionNo`,`Position`,`Name`,`Gender`,`DOB`,`Email`,`Motto`,`Comment`,`Password`,`Usertype`) values (98765,'ID40','Head Boy','Akhil','Male','1998-08-11','akhil@gmail.com','Vote for the beset','One of the reasons I\'m running for Head Boy of the student is because I want to give back to the high school I love so much. I want to be that friendly face for new students coming in and a defender for students already here.','Akhil@2','candidates'),(98765,'ID43','Head Girl','Tanu','Female','1997-06-20','tanu@gmail.com','The change you need','I will introduce ideas for events, such as a weekly sock hop after high school basketball games and we\'ll work together to help the community through volunteer work and showing them just how dedicated the students at Jaipur School are.','Tanu@4','candidates'),(98765,'ID45','Head Girl','Aastha','Female','1997-03-23','aastha@gmail.com','Standing up,because of you, for You.','I will be your voice with the school administration and work to make this the best year Jaipur School has ever had.','Aastha@1','candidates'),(98765,'ID69','Head Boy','Mayank','Male','1996-04-12','mayank@gmail.com','Your Voice,Your Votes','I would appreciate your vote for me. If elected, I will continue to fight for the things that are important to students, no matter how big or small they might be. Let\'s work together to make our stamp on this world.','Mayank@7','candidates'),(79564,'MG94','President','Priya','Female','2000-02-10','priya@gmail.com','We don\'t build Hope,We build the Future.','Through my proposed solutions, I will turn the obstacles that we encounter into stepping stones – stepping stones that will create even more opportunities for all the girls at MGPS.','Priya@0','candidates'),(79564,'MG97','President','Tanvi','Female','2000-07-12','tanvi@gmail.com','Vote for me, and improve your School','I look forward to meeting you and learning about your views, suggestions and concerns.None of us is as smart as all of us. Together, we will deliver!','Tanvi@3','candidates');

/*Table structure for table `havevoted` */

DROP TABLE IF EXISTS `havevoted`;

CREATE TABLE `havevoted` (
  `OrganisationNo` int(10) default NULL,
  `Email` varchar(30) NOT NULL,
  PRIMARY KEY  (`Email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `havevoted` */

insert  into `havevoted`(`OrganisationNo`,`Email`) values (98765,'chirag@gmail.com'),(98765,'sejal@gmail.com'),(98765,'shyam@gmail.com');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `OrganisationNo` int(10) NOT NULL,
  `Email` varchar(30) default NULL,
  `Password` varchar(30) default NULL,
  `Usertype` varchar(12) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`OrganisationNo`,`Email`,`Password`,`Usertype`) values (79564,'mgps@gmail.com','mgps@20','organisation'),(98765,'jprschool@gmail.com','Jaipurschl','organisation'),(336745,'bitmesra@gmail.com','bitmesra@1','organisation'),(98765,'akhil@gmail.com','Akhil@2','candidates'),(98765,'mayank@gmail.com','Mayank@7','candidates'),(76564,'tanvi@gmail.com','Tanvi@3','candidates'),(98765,'chirag@gmail.com','Chirag@1','voters'),(98765,'ram@gmail.com','Ram@1','voters'),(98765,'shyam@gmail.com','Shyam@3','voters'),(98765,'karan@gmail.com','Karan@4','voters'),(98765,'arjun@gmail.com','Arjun@9','voters'),(98765,'pooja@gmail.com','Pooja@08','voters'),(98765,'sejal@gmail.com','Sejal@86','voters'),(98765,'pallavi@gmail.com','Pallavi@90','voters'),(79564,'palak@gmail.com','Palak@34','voters'),(79564,'manisha@gmail.com','Manisha@65','voters'),(79564,'nishu@gmail.com','Nishu@6','voters'),(79564,'arpita@gmail.com','Arpita@3','voters'),(79564,'aanchal@gmail.com','Aanchal@70','voters'),(79564,'yukti@gmail.com','Yukti@54','voters'),(79564,'shabana@gmail.com','Shabana@67','voters'),(79564,'priya@gmail.com','Priya@0','candidates'),(98765,'aastha@gmail.com','Aastha@1','candidates'),(98765,'tanu@gmail.com','Tanu@4','candidates'),(336745,'kusum@gmail.com','Kusum@8','voters');

/*Table structure for table `organisation` */

DROP TABLE IF EXISTS `organisation`;

CREATE TABLE `organisation` (
  `OrganisationNo` int(10) NOT NULL,
  `OrganisationName` varchar(30) default NULL,
  `Email` varchar(30) default NULL,
  `Address` varchar(70) default NULL,
  `Phone` int(9) default NULL,
  `Password` varchar(30) default NULL,
  `Usertype` varchar(12) default NULL,
  PRIMARY KEY  (`OrganisationNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `organisation` */

insert  into `organisation`(`OrganisationNo`,`OrganisationName`,`Email`,`Address`,`Phone`,`Password`,`Usertype`) values (79564,'MGPS','mgps@gmail.com','Vidhyadhar Nagar,Jaipur',302023,'mgps@20','organisation'),(98765,'Jaipur School','jprschool@gmail.com','Vidhyadhar Nagar,Jaipur',224536,'Jaipurschl','organisation'),(336745,'BIT','bitmesra@gmail.com','Malviya Nagar,Jaipur',343954,'bitmesra@1','organisation');

/*Table structure for table `schedule` */

DROP TABLE IF EXISTS `schedule`;

CREATE TABLE `schedule` (
  `OrganisationNo` int(10) NOT NULL,
  `Position` varchar(30) default NULL,
  `StartDate` date default NULL,
  `EndDate` date default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `schedule` */

insert  into `schedule`(`OrganisationNo`,`Position`,`StartDate`,`EndDate`) values (98765,'Head Boy','2017-06-24','2017-07-20'),(98765,'Head Girl','2017-06-24','2017-07-20'),(79564,'President','2017-07-10','2017-07-15'),(79564,'Vice-president','2017-07-10','2017-07-15');

/*Table structure for table `voters` */

DROP TABLE IF EXISTS `voters`;

CREATE TABLE `voters` (
  `OrganisationNo` int(10) default NULL,
  `AdmissionNo` varchar(10) NOT NULL,
  `Name` varchar(30) default NULL,
  `Gender` varchar(6) default NULL,
  `DOB` date default NULL,
  `Email` varchar(30) default NULL,
  `Password` varchar(30) default NULL,
  `Usertype` varchar(12) default NULL,
  PRIMARY KEY  (`AdmissionNo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `voters` */

insert  into `voters`(`OrganisationNo`,`AdmissionNo`,`Name`,`Gender`,`DOB`,`Email`,`Password`,`Usertype`) values (336745,'BIT30','Kusum','Female','1997-02-03','kusum@gmail.com','Kusum@8','voters'),(98765,'ID03','Sejal','Female','1997-09-10','sejal@gmail.com','Sejal@86','voters'),(98765,'ID10','Pallavi','Female','1997-02-01','pallavi@gmail.com','Pallavi@90','voters'),(98765,'ID20','Chirag','Male','2006-03-01','chirag@gmail.com','Chirag@1','voters'),(98765,'ID21','Ram','Male','1997-03-20','ram@gmail.com','Ram@1','voters'),(98765,'ID30','Shyam','Male','1998-07-11','shyam@gmail.com','Shyam@3','voters'),(98765,'ID34','Karan','Male','1996-03-12','karan@gmail.com','Karan@4','voters'),(98765,'ID37','Arjun','Male','1999-03-23','arjun@gmail.com','Arjun@9','voters'),(98765,'ID65','Pooja','Female','1997-08-15','pooja@gmail.com','Pooja@08','voters'),(79564,'MG09','Palak','Female','1997-02-01','palak@gmail.com','Palak@34','voters'),(79564,'MG100','Shabana','Female','2000-04-01','shabana@gmail.com','Shabana@67','voters'),(79564,'MG20','Manisha','Female','1997-11-20','manisha@gmail.com','Manisha@65','voters'),(79564,'MG23','Nishu','Female','1998-02-14','nishu@gmail.com','Nishu@6','voters'),(79564,'MG80','Yukti','Female','1995-03-20','yukti@gmail.com','Yukti@54','voters'),(79564,'MG90','Aanchal','Female','1996-03-14','aanchal@gmail.com','Aanchal@70','voters'),(79564,'MG95','Arpita','Female','1997-01-01','arpita@gmail.com','Arpita@3','voters');

/*Table structure for table `votes` */

DROP TABLE IF EXISTS `votes`;

CREATE TABLE `votes` (
  `OrganisationNo` int(10) default NULL,
  `Position` varchar(30) default NULL,
  `Name` varchar(30) NOT NULL,
  `Count` varchar(100) default NULL,
  PRIMARY KEY  (`Name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `votes` */

insert  into `votes`(`OrganisationNo`,`Position`,`Name`,`Count`) values (98765,'Head Girl','Aastha','2'),(98765,'Head Boy','Akhil','2'),(98765,'Head Boy','Mayank','1'),(98765,'Head Girl','Tanu','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
