# nutrintake
Backend for Nutrintake Nutrient Tracking App
Nutrintake is a tracking app that allows users to track the foods they eat and their nutritional value; along with setting goals and limits for themselves
in regards to the nutrients they consume.
TODO: Include running instructions
Main will contain stable code only; features in progress should have their own branches.


## Frontend Implementation
The frontend is built using Thymeleaf within the Spring Boot application.

### Implemented
- Dashboard page displaying daily totals (calories, protein, sugar)
- Food creation form
- Food logging form (servings + date)
- totals calculation displayed in the UI
- Table showing logged food entries
- Navigation header
- CSS styling for layout and usability
- Form validation for required inputs

### Planned Frontend Enhancements
- Weekly totals display
- Nutrient goal
- limit notifications
- login page
- Chart visualizations

## Running the Application (Frontend Development)

### Requirements
- Java 17 (or compatible JDK)
- IntelliJ IDEA (recommended) or any IDE with Maven support

### Steps to Run

1. Clone the repository: https://github.com/PinataLad/nutrintake.git
   
3. Open the project in IntelliJ.

4. Locate the main Spring Boot application file:

5. Run the application using IntelliJ's Run button.

6. Open your browser and navigate to: http://localhost:8080/dashboard

## Application Pages Overview

### Dashboard (`/dashboard`)
The dashboard page allows users to:
- Create new food items
- Log servings of food for a selected date
- View calculated daily totals (calories, protein, sugar)
- See a table of foods logged for the day

### Foods (`/foods`)
The foods page allows users to:
- Create and define new food items
- View all saved food items
- Edit or delete food items (planned feature)
- Manage nutritional values per serving

### Goals (`/goals`) (Planned)
The goals page will allow users to:
- Set daily nutrient limits
- Set weekly nutrient limits
- View whether totals exceed limits
