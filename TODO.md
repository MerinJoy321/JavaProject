# TODO: Smart Campus Project Tasks

## Database Connectivity
- [ ] Add database connectivity to the project
  - [ ] Create infrastructure/persistence/config/DatabaseConfig.java for H2 connection
  - [ ] Update data models (Building, Floor, Destination) to include database IDs
  - [ ] Create DAO interfaces and implementations for CRUD operations
  - [ ] Add SQL scripts for schema creation and initial data
  - [ ] Update CampusDataService.java to use database instead of mock data
  - [ ] Test database connectivity and data loading

## Login/Sign Up and Role-Based Access
- [x] Create User model with roles (Student, Faculty, Admin).
- [x] Create LoginView.java for login page.
- [x] Create SignUpView.java for sign up page.
- [x] Modify SmartCampusApp.java to show login first, then main dashboard after authentication.
- [x] Update MainDashboard.java to accept User and display role-based content.
- [x] Add mock authentication logic (UserDAO or simple check).
- [x] Recompile the project.
- [x] Test the app by launching it.
