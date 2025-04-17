# Medication Adherence Tracking System

## Team Members
- Kennedy Ogden
- Mona Benabderrazak
- Jacob Turner
- Hrishita Sehrawat
- Anika Makarla

## Project Overview
The Medication Adherence Tracking System is a web-based application designed to help healthcare providers monitor and manage patient medication adherence. The system allows for tracking patient medication schedules, recording adherence data, and viewing adherence history.

## Features
- Patient Management
  - Add new patients to the system
  - View patient information
  - Track multiple patients simultaneously

- Medication Adherence Recording
  - Record medication intake details
  - Track dosage information
  - Monitor adherence patterns
  - View adherence history

## Technical Stack
### Backend
- Java 8
- Spring Boot 2.7.5
- MySQL Database
- Maven for dependency management

### Frontend
- HTML5
- CSS3
- JavaScript (Vanilla)
- RESTful API integration



### Key Dependencies
- Spring Boot Starter Web
- MySQL Connector



## Setup and Installation
1. Clone the repository
2. Ensure you have Java 8 or higher installed
3. Install MySQL and create a database
4. Configure database connection in application properties
5. Run `mvn clean install` to build the project
6. Start the application using `mvn spring-boot:run`

## API Endpoints
- `GET /api/patients` - Retrieve all patients
- `POST /api/patients` - Add a new patient
- `GET /api/adherence` - Get adherence records
- `POST /api/adherence` - Add new adherence record

## User Interface
The application provides three main interfaces:
1. Home Page (index.html)
   - Navigation to all main features
   - Overview of the system

2. Adherence Recording (adherence.html)
   - Form to add new adherence records
   - Patient selection
   - Medication and dosage input

3. Adherence View (view-adherence.html)
   - Historical view of adherence records


## Future Enhancements
- User authentication and authorization
- Real-time notifications


## License
This project is licensed under the MIT License.
